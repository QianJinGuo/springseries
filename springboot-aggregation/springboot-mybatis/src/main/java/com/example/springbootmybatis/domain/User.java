package com.example.springbootmybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private int id;

    private String username;

    private String name;

    private String password;

}
