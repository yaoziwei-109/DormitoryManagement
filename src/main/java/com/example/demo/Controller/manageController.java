package com.example.demo.Controller;

import com.example.demo.bean.Student;
import com.example.demo.service.studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class manageController {

    @Autowired
    com.example.demo.service.studentservice studentservice;

    @PostMapping("/user/insert")
    @ResponseBody
    public int insert(@RequestBody Student student,
                         HttpSession session) {

        return studentservice.addstudent((String) session.getAttribute("username"),student);

    }
    @PostMapping("/user/delete")
    @ResponseBody
    public int delete(@RequestBody Student student,
                      HttpSession session){


        return  studentservice.deletestudent((String) session.getAttribute("username"),student);

    }


    @PostMapping("/user/update")
    @ResponseBody
    public int update(@RequestBody Student student,
                      HttpSession session)
    {
       return  studentservice.updatestudent((String) session.getAttribute("username"),student);

    }

    @PostMapping("/user/getUsernameByNumber")
    @ResponseBody
    public String getUsernameByNumber(@RequestBody Student student,
                      HttpSession session)
    {
        Student re= studentservice.getStudentbynumber((String) session.getAttribute("username"),student.getNumber());
        if(re!=null)
            return re.getName();
        else
            return  null;

    }


}
