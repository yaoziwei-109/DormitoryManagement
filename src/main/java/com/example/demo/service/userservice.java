package com.example.demo.service;


import com.example.demo.bean.User;
import com.example.demo.mapper.usermapper;
import com.example.demo.tool.tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class userservice {

    @Autowired
    usermapper usermapper;


    public User getuserbyid(Long id){
        return usermapper.getuser(id);
    }

    public User getUserByName(String name){
        return  usermapper.GetUserByName(name);
    }

    public int addUser(User user){
        try {
            if(usermapper.createTableForUser(tool.getTableNameFromUsername(user.getName()))==-1){
                return -2;
            }else {
                return usermapper.addUser(user.getName(),user.getPassword());
            }
        }catch (Exception e){
            System.out.println(e.getMessage()+"");
            return -1;
        }
    }

    public int updateUser(User user){
        try {
            int id=getUserByName(user.getName()).getId();
            return usermapper.updateUser(user.getName(),user.getPassword(),id);
        }catch (Exception e){
            return -1;
        }
    }

    public int createTableForUser(String Username){
        try {
            int i=0;
            i=usermapper.createTableForUser(Username);
            System.out.println(i);
            return i;
        }catch (Exception e){
            System.out.println(e.getMessage()+"");
            return -1;

        }
    }
}
