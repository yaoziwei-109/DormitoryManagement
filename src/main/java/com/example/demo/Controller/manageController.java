package com.example.demo.Controller;

import com.example.demo.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @PostMapping ("/user/getUsernameByNumber")
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
    @PostMapping("/user/getUserByNumber")
    @ResponseBody
    public Student getUserByNumber(@RequestBody Student student, HttpSession session){
        System.out.println(student.getNumber());
        Student o=studentservice.getStudentbynumber((String) session.getAttribute("usernamewwww"),student.getNumber());
        System.out.println(o);
        return   o;
    }

}
