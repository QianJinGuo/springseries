package com.example.springbootjpa;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws JsonProcessingException {
        //1、从Redis中获得数据 数据的形式使用json字符串
        String userListJson = redisTemplate.boundValueOps("user.findAll").get();
        //2、判断Redis是否存在数据
        if (null == userListJson) {
            //3、不存在数据 从数据库查询
            List<User> all = userRepository.findAll();
            //4、将查询出的数据存储到redis缓存中
            //先将list集合转换成json格式字符串  使用jackhon进行转换
            ObjectMapper objectMapper = new ObjectMapper();
            userListJson = objectMapper.writeValueAsString(all);
            redisTemplate.boundValueOps("user.findAll").set(userListJson);
            log.info("----------从数据库中获得user的数据，并存储到redis缓存中-------------");
        } else {
            log.info("----------从redis缓存中获得user的数据-------------");
        }
        //4、将数据在控制台打印
        log.info(userListJson);
    }
}
