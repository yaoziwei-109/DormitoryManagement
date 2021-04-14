package com.example.demo.Controller;

import com.example.demo.bean.User;
import com.example.demo.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    com.example.demo.service.userservice userservice;

    @PostMapping("/user/register")
    public  String register(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("repeatPassword") String repeatPassword,
                            HttpSession session,
                            Model model)
    {
        if(repeatPassword.equals(password)){
            if(userservice.getUserByName(username)==null){
                if(userservice.addUser(new User(username,password))!=-1
                        && userservice.addUser(new User(username,password))!=-2){
                    session.setAttribute("msg","注册成功");
                    return "redirect:/login";
                }else if(userservice.addUser(new User(username,password))==-2) {
                    model.addAttribute("msg","注册失败,给用户创建表格到数据库失败");
                    return "register";
                }else {
                    model.addAttribute("msg","注册失败,插入用户信息到数据库失败");
                    return "register";
                }
            }
            model.addAttribute("msg","注册失败,有重复名字");
            return "register";
        }else {
            model.addAttribute("msg","注册失败，密码没有正确重复输入");
            return "register";
        }

    }

    @GetMapping("/register")
    public  String returnregister(){
        return "register";
    }



    @GetMapping("/forgotpassword")
    public  String returnforgotpassword(){
        return "forgot-password";
    }

    @PostMapping("/user/reset")
    public  String reset(@RequestParam("username") String username,
                         @RequestParam("password")  String password,
                           HttpSession session,
                           Model model)
    {
        if(password.isEmpty()){
            model.addAttribute("msg","修改失败,密码为空");
            return "forgot-password";
        }else {
            if(userservice.getUserByName(username)!=null){
                if(userservice.updateUser(new User(username,password))!=-1){
                    session.setAttribute("msg","修改成功");
                    return "redirect:/login";
                }else {
                    model.addAttribute("msg","修改失败，操作数据库失败");
                    return "forgot-password";
                }
            }else {
                model.addAttribute("msg","修改失败,查无用户");
                return "forgot-password";
            }
        }

    }
}
