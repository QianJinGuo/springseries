/*
 * Copyright: 2020 www.chebada.com Inc. All rights reserved.
 * 注意：本内容仅限于车巴达公司内部传阅，禁止外泄以及用于其他的商业目的
 *
 * @Project: springboot-aggregation
 * @File: QueryWrapperTest
 * @Package: com.example.springbootmybatisplus.mapper
 * @Date: 2020/12/18 17:05
 * @Author: by jinguo.qian
 *
 */
package com.example.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.springbootmybatisplus.entity.User;
import com.example.springbootmybatisplus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jinguo.qian
 * @title: QueryWrapperTest
 * @projectName springboot-aggregation
 * @description: 测试方法
 * @date 2020/12/18 17:05
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class QueryWrapperTest {
    @Autowired
    private UserMapper userMapper;

    //ge、gt、le、lt、isNull、isNotNull
    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //这边对应的是数据表里的字段
        queryWrapper.isNull("name").ge("age", 30).isNotNull("create_time");
        int result = userMapper.delete(queryWrapper);
        log.info("delete return count = " + result);
    }

    //eq、ne
    //seletOne返回的是一条实体记录，当出现多条时会报错
    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    //between、notBetween
    //包含大小边界
    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    //allEq
    @Test
    public void testSelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        //map也是对应数据库中的列
        map.put("id", 2);
        map.put("name", "Jack");
        map.put("age", 20);
        map.put("create_time", LocalDateTime.now());
        queryWrapper.allEq(map);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //like、notLike、likeLeft、likeRight
    //selectMaps返回Map集合列表
    @Test
    public void testMap() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.notLike("name", "e").likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回
        maps.forEach(System.out::println);
    }

    /**
     * @Description: in、notIn、inSql、notinSql、exists、notExists
     * in、notIn：
     * notIn("age",{1,2,3})--->age not in (1,2,3)
     * notIn("age", 1, 2, 3)--->age not in (1,2,3)
     * inSql、notinSql：可以实现子查询
     * 例: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)
     * 例: inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3)
     * @Param:
     * @Return:
     * @Author: jinguo.qian
     * @Date: 2020/12/18 17:38
     */

    @Test
    public void testSelectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.in("id", 1, 2, 3);
        queryWrapper.inSql("id", "select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        //返回值是Object列表
        objects.forEach(System.out::println);
    }

    //or、and
    //注意：这里使用的是 UpdateWrapper
    //不调用or则默认为使用 and 连
    @Test
    public void testUpdate1() {
        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or()
                .between("age", 20, 30);
        int result = userMapper.update(user, userUpdateWrapper);
        System.out.println(result);
    }

    //嵌套or、嵌套and
    //这里使用了lambda表达式，or中的表达式最后翻译成sql时会被加上圆括号
    @Test
    public void testUpdate2() {
        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or(i -> i.eq("name", "李白").ne("age", 20));
        int result = userMapper.update(user, userUpdateWrapper);
        System.out.println(result);
    }

    //orderBy、orderByDesc、orderByAsc
    @Test
    public void testSelectListOrderBy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //last
    //直接拼接到 sql 的最后
    //注意：只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
    @Test
    public void testSelectListLast() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //指定要查询的列
    @Test
    public void testSelectListColumn() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //set、setSql
    @Test
    public void testUpdateSet() {
        //修改值
        User user = new User();
        user.setAge(99);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .set("name", "老李头")//除了可以查询还可以使用set设置修改的字段
                .setSql(" email = '123@qq.com'");//可以有子查询
        int result = userMapper.update(user, userUpdateWrapper);
    }
}
    