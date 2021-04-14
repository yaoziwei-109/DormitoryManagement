package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
    private Integer id;
    private String name;
    private String dormitory;
    private String clas;
    private String number;
    private String teacher_phone;
    private String parent_phone;
}
