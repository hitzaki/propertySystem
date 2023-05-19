package com.chen.property.controller;

import com.chen.property.pojo.Info;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/img")
public class ImgController {
    // ***********获取验证码图片
    @Resource
    private DefaultKaptcha kaptcha;

    @GetMapping("/verify")
    public void verifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        //响应立即过期
        response.setDateHeader("Expires", 0);
        //不缓存任何图片数据
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");
        //生成验证码字符文本
        String verifyCode = kaptcha.createText();
        session.setAttribute("VerifyCode", verifyCode);
        BufferedImage image = kaptcha.createImage(verifyCode);//创建验证图片
        ServletOutputStream stream = response.getOutputStream();
        ImageIO.write(image, "png", stream);
        stream.flush();
        stream.close();
    }

    // ***********获取头像图片

    @GetMapping("/photo")
    public void userPhoto(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/png");
        Info info =  (Info)session.getAttribute("Info");
        response.setContentType("image/"+info.getPhoto().substring(info.getPhoto().lastIndexOf('.')+1));
        FileInputStream in = new FileInputStream(session.getServletContext().getRealPath(info.getPhoto()));
        BufferedImage image = ImageIO.read(in);
        OutputStream stream = response.getOutputStream();
        ImageIO.write(image, "png", stream);
        stream.flush();
        stream.close();
    }
}
