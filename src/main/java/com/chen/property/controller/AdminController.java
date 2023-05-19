package com.chen.property.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chen.property.pojo.*;
import com.chen.property.service.*;
import com.chen.property.utils.MD5;
import com.chen.property.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private PayService payService;
    @Resource
    private RepairService repairService;
    @Resource
    private AdminService adminService;
    @Resource
    private AccendantService accendantService;
    @Resource
    private StandardService standardService;


    // ----------------------管理员首页回显的内容-------------------------------
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
        wrapper1.eq("state", "待维修");
        map.put("sum1", ""+repairService.count(wrapper1));
        QueryWrapper<Pay> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("state", "待缴费");
        map.put("sum2", ""+payService.count(wrapper2));
        return map;
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
            return "redirect:/admin/AdminHome.html";
        }
        info.setPhoto(fileName);
        UpdateWrapper<Admin> wrapper = new UpdateWrapper<>();
        wrapper.set("photo", fileName).eq("account", info.getAccount());
        adminService.update(wrapper);
        return "redirect:/admin/AdminHome.html";
    }

    @PostMapping("/changeInfo")
    public String handlerChange(@RequestParam String phone, @RequestParam String password1, @RequestParam String password2, HttpSession session){
        // 更新密码与电话号
        Info info = (Info)session.getAttribute("Info");
        if(password1==null || password2==null || phone == null){
            session.setAttribute("changeInfo", "信息填写错误");
            return "redirect:/admin/AdminHome.html";
        }

        UpdateWrapper<Admin> wrapper = new UpdateWrapper<>();
        wrapper.set("phone", phone).set("password", MD5.encrypt(password2))
                .eq("password", MD5.encrypt(password1)).eq("account", info.getAccount());
        if(!adminService.update(wrapper)){
            session.setAttribute("changeInfo", "信息填写错误");
            return "redirect:/admin/AdminHome.html";
        }
        info.setPhone(phone);
        session.setAttribute("Info", info);
        return "redirect:/admin/AdminHome.html";
    }

    // ----------------------管理员Repair页面增删改查-------------------------------
    @GetMapping("/getAccendantdInfo")
    @ResponseBody
    public List<Accendant> handlerRe1(){
        return accendantService.list();
    }

    @GetMapping("/getRepairInfo")
    @ResponseBody
    public List<Repair> handlerRe2(){
        return repairService.list();
    }

    @GetMapping("/RepairDe")
    public void handlerRe3(@RequestParam int id){
        UpdateWrapper<Pay> wrapper = new UpdateWrapper<>();
        wrapper.set("state", "已缴费").eq("id", id);
        payService.update(wrapper);
    }

    @PostMapping("/addPeo")
    public String handlerRe4(@RequestParam String id, @RequestParam String name, @RequestParam String phone, @RequestParam String type, HttpSession session){
        Info info = (Info) session.getAttribute("Info");
        Accendant accendant = new Accendant();
        if(!"".equals(id))
            accendant.setId(Integer.parseInt(id));
        accendant.setName(name);
        accendant.setPhone(phone);
        accendant.setType(type);
        accendantService.saveOrUpdate(accendant);
        return "redirect:/admin/AdminRepair.html";
    }

    @PostMapping("/repairOver")
    public String handlerRe5(@RequestParam String id, @RequestParam String name, HttpSession session){
        UpdateWrapper<Repair> wrapper = new UpdateWrapper<>();
        if("".equals(Integer.parseInt(id))) return "redirect:/user/UserRepair.html";
        wrapper.set("name", name).set("state", "已维修").eq("id", id);
        repairService.update(wrapper);
        return "redirect:/admin/AdminRepair.html";
    }

    // ----------------------用户Pay页面增删改查-------------------------------
    @GetMapping("/getStandardInfo")
    @ResponseBody
    public List<Standard> handlerAcc(HttpSession session){
        return standardService.list();
    }

    @GetMapping("/getPayInfo")
    @ResponseBody
    public List<Pay> handlerPay(HttpSession session){
        return payService.list();
    }



    @PostMapping("/updateSta")
    public String handlerStaUp(@RequestParam String id, @RequestParam String name, @RequestParam String unit
            , @RequestParam String univalence){
        Standard standard = new Standard();
        if (!"".equals(id))
            standard.setId(Integer.parseInt(id));
        standard.setName(name);
        standard.setUnit(unit);
        standard.setUnivalence(Float.parseFloat(univalence));
        standardService.saveOrUpdate(standard);
        return "redirect:/admin/AdminPay.html";
    }

    @PostMapping("/updatePay")
    public String handlerPayUp(@RequestParam String id, @RequestParam String account, @RequestParam String name, @RequestParam String price){
        Pay pay = new Pay();
        if(!"".equals(id))
            pay.setId(Integer.parseInt(id));

        if("".equals(account)) return  "redirect:/admin/AdminPay.html";
        pay.setAccount(account);
        pay.setName(name);
        if(!"".equals(price))
            pay.setPrice(Float.parseFloat(price));
        else  return "redirect:/admin/AdminPay.html";
        payService.saveOrUpdate(pay);
        return "redirect:/admin/AdminPay.html";
    }


    // ----------------------用户Pay页面增删改查-------------------------------
}
