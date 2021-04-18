package com.example.demo.service;


import com.example.demo.bean.Student;
import com.example.demo.bean.User;
import com.example.demo.mapper.studentmapper;
import com.example.demo.tool.tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class studentservice {

    @Autowired
    com.example.demo.mapper.studentmapper studentmapper;

    public int addstudent(String username,Student student){
        try {
            String tableName = tool.getTableNameFromUsername(username);
            if(studentmapper.getStudentbynumber(tableName,student.getNumber())!=null){
                return -2;  //通过学号可以查到学生，表中已经有学生
            }
           return studentmapper.addstudent(tableName,student.getName(),student.getDormitory(),student.getClas(),
                                     student.getNumber(),student.getTeacher_phone(),student.getParent_phone());

        }catch (Exception e){
            return -1;
        }
    }

    public int updatestudent(String username,Student student){
        try {
            String tableName = tool.getTableNameFromUsername(username);

            if(studentmapper.getStudentbynumber(tableName,student.getNumber())==null) {
                return  -2; //查无学生
            }
            return studentmapper.updateStudent(tableName,student.getName(),student.getDormitory(),student.getClas(),
                    student.getNumber(),student.getTeacher_phone(),student.getParent_phone());
        }catch (Exception e){
            return -1;
        }
    }

    public int deletestudent(String username,Student student){
        try {
            String tableName = tool.getTableNameFromUsername(username);
            int test= studentmapper.deletestudent(tableName,student.getNumber());
            System.out.println(test);
            return test;
        }catch (Exception e){
            System.out.println("错误为"+e.getMessage());
            return -1;
        }
    }


    public Student getStudentbyname(String username,String name){
        try {
            return studentmapper.getStudentbyname(tool.getTableNameFromUsername(username), name);
        }catch (Exception e){
            return null;
        }
    }
    public Student getStudentbynumber(String username,String number){
        try {
            return studentmapper.getStudentbynumber(tool.getTableNameFromUsername(username), number);
        }catch (Exception e){
            return null;
        }
    }


    public List<Student> findAll(String username){
        List<Student> ls=new ArrayList<Student>();
        ls= studentmapper.findAll(tool.getTableNameFromUsername(username));
        return  ls;
    }

    public  int countall(String username){
        return studentmapper.countAll(tool.getTableNameFromUsername(username));
    }

    public  int countallclass(String username){
        return studentmapper.countAllClass(tool.getTableNameFromUsername(username));
    }

    public  int countAllDormitory(String username){
        return studentmapper.countAllDormitory(tool.getTableNameFromUsername(username));
    }
}
