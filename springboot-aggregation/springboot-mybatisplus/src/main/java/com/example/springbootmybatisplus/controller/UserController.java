/*
 * Copyright: 2020 www.chebada.com Inc. All rights reserved.
 * 注意：本内容仅限于车巴达公司内部传阅，禁止外泄以及用于其他的商业目的
 *
 * @Project: springboot-aggregation
 * @File: UserController
 * @Package: com.example.springbootmybatisplus.controller
 * @Date: 2020/12/18 17:00
 * @Author: by jinguo.qian
 *
 */
package com.example.springbootmybatisplus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinguo.qian
 * @title: UserController
 * @projectName springboot-aggregation
 * @description: 测试
 * @date 2020/12/18 17:00
 */
@RestController("/")
public class UserController {
    @RequestMapping("/index")
    public String index(){
        return "hello devtools again";
    }
}
    