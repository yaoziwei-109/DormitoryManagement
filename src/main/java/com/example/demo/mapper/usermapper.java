package com.example.demo.mapper;



import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;



public interface usermapper {

     User getuser(Long id);


     @Select(" SELECT * FROM mydb WHERE name = #{name}")
     User GetUserByName(String name);

     @Insert("INSERT INTO mydb(id,name,password)  VALUES (null,#{username},#{password})")
     int addUser(String username,String password);

     @Update("UPDATE mydb SET name=#{username},password=#{password} WHERE id=#{id}")
     int updateUser(String username,String password,int id);

     int createTableForUser(@Param("username")String username);
}
