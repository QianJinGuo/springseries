package com.example.springbootmybatis;

import com.example.springbootmybatis.domain.User;
import com.example.springbootmybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
@Slf4j
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Before
    public void init(){

    }
    @Test
    public void test(){
        List<User> users=userMapper.queryUserList();
        log.info(users.toString());
    }
}
