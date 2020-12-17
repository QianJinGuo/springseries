package com.example.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootmybatisplus.entity.User;
import org.springframework.stereotype.Repository;

//在启动类加上了@MapperScan注解，这样在 DAO 接口上就不需要添加@Mapper注解了
@Repository
public interface UserMapper extends BaseMapper<User> {

}
