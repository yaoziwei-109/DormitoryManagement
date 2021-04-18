package com.example.demo.mapper;

import com.example.demo.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface studentmapper {

    @Insert("INSERT INTO ${username}" +"(id,name,dormitory,clas,number,teacher_phone,parent_phone)"
            + "  VALUES (null,#{name},#{dormitory},#{clas},#{number},#{teacher_phone},#{parent_phone})")
    int addstudent(@Param("username") String username, String name, String dormitory, String clas,
                   String number, String teacher_phone, String parent_phone);

    @Select("SELECT * FROM ${username}")
    List<Student> findAll(@Param("username") String username);

    @Select("SELECT count(*) FROM ${username}")
    int countAll(@Param("username") String username);


    @Update("UPDATE ${username} SET dormitory=#{dormitory},clas=#{clas}," +
            "name=#{name},teacher_phone=#{teacher_phone},parent_phone=#{parent_phone} " +
            "WHERE number=#{number}" )
    int updateStudent(@Param("username") String username, String name,String dormitory, String clas,
                      String number, String teacher_phone, String parent_phone);

    @Select("SELECT * FROM ${username} WHERE name=#{name}")
    Student getStudentbyname(@Param("username") String username,String name);

    @Select("SELECT * FROM ${username} WHERE number=#{number}")
    Student getStudentbynumber(@Param("username") String username,String number);

    @Delete("Delete  FROM ${username} WHERE  number=#{number}")
    int deletestudent(@Param("username") String username,String number);

    @Select("SELECT COUNT(DISTINCT clas) FROM ${username}")
    int countAllClass(@Param("username") String username);

    @Select("SELECT COUNT(DISTINCT dormitory) FROM ${username}")
    int countAllDormitory(@Param("username") String username);
}
