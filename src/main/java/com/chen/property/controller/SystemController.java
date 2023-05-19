package com.chen.property.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.property.pojo.Admin;
import com.chen.property.pojo.Info;
import com.chen.property.pojo.LoginForm;
import com.chen.property.pojo.User;
import com.chen.property.service.AdminService;
import com.chen.property.service.UserService;
import com.chen.property.utils.MD5;
import com.chen.property.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RequestMapping("/system")
@Controller
public class SystemController {
    // ---------------------------------登录功能实现---------------------------------------------
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String handler1(LoginForm login, HttpSession session){
        // 验证验证码
        String verifyCode = (String)session.getAttribute("VerifyCode");
        if(verifyCode==null || !verifyCode.equals(login.getVerifyCode())) {
            session.setAttribute("indexInfo", "验证码错误");
            return "redirect:/index.html";
        }
        // 账号密码正确则存一个用户或管理员的Info
        switch (login.getType()){
            case "1": {
                QueryWrapper<Admin> wrapper = new QueryWrapper<>();
                wrapper.eq("account", login.getAccount())//密码要转成密文
                        .eq("password", MD5.encrypt(login.getPassword()));
                Admin admin = adminService.getOne(wrapper);
                if(admin==null){
                    session.setAttribute("indexInfo", "账号密码错误");
                    return "redirect:/index.html";
                }
                session.setAttribute("Info", new Info(admin.getAccount(), "1", admin.getPhoto(), admin.getName(),
                        admin.getSex(), admin.getPhone()));
                return "redirect:/admin/AdminHome.html";
            }
            case "2": {
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.eq("account", login.getAccount())//密码要转成密文
                        .eq("password", MD5.encrypt(login.getPassword()));
                User user = userService.getOne(wrapper);
                if(user==null){
                    session.setAttribute("indexInfo", "账号密码错误");
                    return "redirect:/index.html";
                }
                session.setAttribute("Info", new Info(user.getAccount(), "2", user.getPhoto(), user.getName(),
                        user.getSex(), user.getPhone()));
                return "redirect:/user/UserHome.html";
            }
            default: {
                session.setAttribute("indexInfo", "类型错误");
                return "redirect:/index.html";
            }
        }
    }

    // ---------------------------------登录页错误信息回显---------------------------------------------

    @GetMapping("/loginFirst")
    @ResponseBody
    public Result handlerLoginInfo(HttpSession session){
        String info = (String) session.getAttribute("indexInfo");
        if(info == null)info = "无";
        session.removeAttribute("indexInfo");
        return Result.ok().msg(info);
    }

}
