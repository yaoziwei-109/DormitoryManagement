package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class pagescontroller {
    @Autowired
    com.example.demo.service.studentservice studentservice;

    @GetMapping(value={"/cards"})
    public  String cards(){

        return "cards";
    }

    @GetMapping(value={"/tables"})
    public  String tables(Model model,HttpSession session){
        model.addAttribute("username",session.getAttribute("username"));
        model.addAttribute("ls",studentservice.findAll((String)session.getAttribute("username")));
        return "tables";
    }

    @RequestMapping(value={"/manage"})
    public  String manage(Model model,HttpSession session){
        model.addAttribute("username",session.getAttribute("username"));
        return "manage";
    }

    @RequestMapping(value={"/main"})
    public  String main(Model model,HttpSession session){
        String username= (String) session.getAttribute("username");
        model.addAttribute("username",username);
        model.addAttribute("countall",studentservice.countall(username));
        model.addAttribute("countallclass",studentservice.countallclass(username));
        model.addAttribute("countAllDormitory",studentservice.countAllDormitory(username));
        return "index";
    }
}
