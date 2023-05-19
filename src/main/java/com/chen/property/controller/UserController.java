package com.chen.property.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chen.property.pojo.*;
import com.chen.property.service.*;
import com.chen.property.utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private PayService payService;
    @Resource
    private RepairService repairService;
    @Resource
    private UserService userService;
    @Resource
    private EstateService estateService;
    @Resource
    private AccendantService accendantService;
    @Resource
    private StandardService standardService;

    // ----------------------用户首页回显的内容-------------------------------
    @GetMapping("/getHomeInfo")
    @ResponseBody
    public Map<String, String> handlerName(HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        Map<String, String> map = new HashMap<>();

        map.put("changeInfo", (String) session.getAttribute("changeInfo"));
        map.put("name", info.getName());
        map.put("sex", info.getSex());
        map.put("phone", info.getPhone());
        QueryWrapper<Repair> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("state", "待维修").eq("account", info.getAccount());
        map.put("sum1", ""+repairService.count(wrapper1));
        QueryWrapper<Pay> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("state", "待缴费").eq("account", info.getAccount());
        map.put("sum2", ""+payService.count(wrapper2));
        return map;
    }

    @GetMapping("/getEstateInfo")
    @ResponseBody
    public List<Estate> handlerEstate(HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        QueryWrapper<Estate> wrapper = new QueryWrapper<>();
        wrapper.eq("account", info.getAccount());
        return estateService.list(wrapper);
    }

    @GetMapping("/getName")
    @ResponseBody
    public Map<String, String> handlerGeName(HttpSession session){
        Info info = (Info)session.getAttribute("Info");
        Map<String, String> map = new HashMap<>();
        map.put("name", info.getName());
        return map;
    }

    // ----------------------管理员首页-修改自己的个人信息-------------------------------
    @PostMapping("/changeHead")
    public String handler11(@RequestPart MultipartFile head, HttpSession session){
        // 更新头像的同时要更新session和数据库
        Info info = (Info)session.getAttribute("Info");
        String fileName = head.getOriginalFilename();
        fileName = "upload/" + info.getAccount() + fileName.substring(fileName.lastIndexOf('.'));
        try {
            head.transferTo(new File(session.getServletContext().getRealPath(fileName)));
        } catch (IOException e) {
            session.setAttribute("changeInfo", "头像更新失败");
            return "redirect:/user/UserHome.html";
        }
        info.setPhoto(fileName);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("photo", fileName).eq("account", info.getAccount());
        userService.update(wrapper);
        return "redirect:/user/UserHome.html";
    }

    @PostMapping("/changeInfo")
    public String handlerChange(@RequestParam String phone, @RequestParam String password1, @RequestParam String password2, HttpSession session){
        // 更新密码与电话号
        Info info = (Info)session.getAttribute("Info");
        if(password1==null || password2==null || phone == null){
            session.setAttribute("changeInfo", "信息填写错误");
            return "redirect:/user/UserHome.html";
        }

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("phone", phone).set("password", MD5.encrypt(password2))
                .eq("password", MD5.encrypt(password1)).eq("account", info.getAccount());
        if(!userService.update(wrapper)){
            session.setAttribute("changeInfo", "信息填写错误");
            return "redirect:/user/UserHome.html";
        }
        info.setPhone(phone);
        session.setAttribute("Info", info);
        return "redirect:/user/UserHome.html";
    }


    // ----------------------用户Pay页面回显的内容 以及缴费模拟请求-------------------------------
    @GetMapping("/getStandardInfo")
    @ResponseBody
    public List<Standard> handlerAcc(HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        QueryWrapper<Standard> wrapper = new QueryWrapper<>();
        return standardService.list();
    }

    @GetMapping("/getPayInfo")
    @ResponseBody
    public List<Pay> handlerPay(HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        QueryWrapper<Pay> wrapper = new QueryWrapper<>();
        wrapper.eq("account", info.getAccount());
        return payService.list(wrapper);
    }

    @GetMapping("/PayFor")
    public void handlerPay222(@RequestParam int id){
        UpdateWrapper<Pay> wrapper = new UpdateWrapper<>();
        wrapper.set("state", "已缴费").eq("id", id);
        payService.update(wrapper);
    }

    // ----------------------用户Repair页面回显的内容 以及删除模拟请求 以及增加或修改操作-------------------------------
    @GetMapping("/getAccendantdInfo")
    @ResponseBody
    public List<Accendant> handlerRe1(HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        return accendantService.list();
    }

    @GetMapping("/getRepairInfo")
    @ResponseBody
    public List<Repair> handlerRe2(HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        QueryWrapper<Repair> wrapper = new QueryWrapper<>();
        wrapper.eq("account", info.getAccount());
        return repairService.list(wrapper);
    }

    @GetMapping("/RepairDe")
    public void handlerRe3(@RequestParam int id){
        UpdateWrapper<Pay> wrapper = new UpdateWrapper<>();
        wrapper.set("state", "已缴费").eq("id", id);
        payService.update(wrapper);
    }

    @PostMapping("/updateRepair")
    public String handlerRe4(@RequestParam String id, @RequestParam String address, @RequestParam String info, HttpSession session){
        Info info2 = (Info) session.getAttribute("Info");
        Repair repair = new Repair();
        repair.setAddress(address);
        repair.setInfo(info);
        if(!"".equals(id))
        repair.setId(Integer.parseInt(id));
        repair.setAccount(info2.getAccount());
        repairService.saveOrUpdate(repair);
        return "redirect:/user/UserRepair.html";
    }

}
