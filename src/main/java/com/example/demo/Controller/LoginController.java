package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class LoginController {

    @Autowired
    com.example.demo.service.userservice userservice;

    @PostMapping("/user/login")
    public String submit(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletRequest request,
                         HttpSession session
    )
    {

        if(userservice.getUserByName(username)!=null
                && userservice.getUserByName(username).getPassword().equals(password)){
            session.setAttribute("username",username);
            return "redirect:/main";
        }
        else {
            session.setAttribute("msg","账号或密码错误");
            return "redirect:/login";
        }
    }

    @GetMapping(value={"/"})
    public  String login(HttpSession session){
        if(session.getAttribute("username")!=null){
            return "redirect:/main";
        }else {
            //第一次访问时 清理缓存
            Enumeration em = session.getAttributeNames();
            while(em.hasMoreElements()){
                session.removeAttribute(em.nextElement().toString());
            }
            return "login";
        }
    }

    @GetMapping(value={"/login"})
    public  String login2(Model model, HttpServletRequest request, HttpSession session){
        if(session.getAttribute("msg")!=null){
            model.addAttribute("msg",request.getSession().getAttribute("msg"));
        }
        Enumeration em = session.getAttributeNames();
        while(em.hasMoreElements()){
            session.removeAttribute(em.nextElement().toString());
        }
        return "login";
    }

}
