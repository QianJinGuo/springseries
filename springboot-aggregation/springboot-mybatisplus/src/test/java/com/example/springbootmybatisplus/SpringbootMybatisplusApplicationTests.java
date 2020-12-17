package com.example.springbootmybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybatisplus.entity.User;
import com.example.springbootmybatisplus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
class SpringbootMybatisplusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    //查询user表所有数据
    @Test
    void contextLoads() {
        List<User> users=userMapper.selectList(null);
        log.info(users.toString());
    }

    @Test
    public void addUser(){
        User user=User.builder().name("zhangsan").age(20).email("zs@qq.com").build();
        int row = userMapper.insert(user);
        log.info(String.format("有%d条数据添加成功",row));
    }

    @Test
    public void updateUser(){
        User user=User.builder().id(2L).age(30).build();
        int row = userMapper.updateById(user);
        log.info(String.format("有%d条数据修改成功",row));
    }

    @Test
    public void addUser2(){
        User user=User.builder().name("wangwu").age(21).email("ww@qq.com").createTime(new Date()).updateTime(new Date()).build();
        int row = userMapper.insert(user);
        log.info(String.format("有%d条数据添加成功",row));
    }

    @Test
    public void addUser3(){
        User user=User.builder().name("zhaoliu").age(21).email("zl@qq.com").build();
        int row = userMapper.insert(user);
        log.info(String.format("有%d条数据添加成功",row));
    }
    @Test
    public void updateUser2(){
        User user=User.builder().id(2L).age(23).build();
        int row = userMapper.updateById(user);
        log.info(String.format("有%d条数据修改成功",row));
    }

    @Test
    public void addUser4(){
        User user=User.builder().name("zhouqi").age(28).email("zq@qq.com").build();
        int row = userMapper.insert(user);
        log.info(String.format("有%d条数据添加成功",row));
    }

    //测试乐观锁  先查后改
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1338167649661714434L);

        //进行修改
        user.setAge(33);
        int row = userMapper.updateById(user);
        log.info(String.format("有%d条数据修改成功",row));

    }

    //测试乐观锁  先查后改
    @Test
    public void testOptimisticLocker2(){
        //根据id查询数据
        User user = userMapper.selectById(1338167649661714434L);
        //进行修改
        user.setAge(36);
        //模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        user.setVersion(user.getVersion()-1);
        int row = userMapper.updateById(user);
        log.info(String.format("有%d条数据修改成功",row));
    }

    //多个id批量查询
    @Test
    public void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L,2L,3L));
        log.info(users.toString());
    }

    @Test
    public void testSelectDemo2(){
        HashMap<String,Object> map=new HashMap<>();
        //map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需要填写user_id
        map.put("name","zhangsan");
        map.put("age","20");
        List<User> users=userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPage(){
        //创建page对象
        //传入两个参数：当前页和每页显示记录数
        Page<User> page = new Page<>(1,3);
        //调用mp发的分页查询方法
        //调用mp分页查询中，底层封装把分页所有数据封装到page对象中
        userMapper.selectPage(page,null);

        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据 List集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext()); //是否有下一页
        System.out.println(page.hasPrevious()); //是否有上一页
    }

    //删除操作 物理删除
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }

    //批量操作
    @Test
    public void testBatchDeleteById(){
        int result = userMapper.deleteBatchIds(Arrays.asList(2L,3L));
        System.out.println(result);
    }

    //带条件删除
    @Test
    public void testDeleteDemo(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","wangwu");
        map.put("update_time","2020-12-14 00:06:17");
        int result=userMapper.deleteByMap(map);
        System.out.println(result);
    }
}
