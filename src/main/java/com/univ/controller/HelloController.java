package com.univ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author univ
 * @datetime 2018/12/3 8:08 PM
 * @description 入门级Controller
 */

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hotDeploy")
    public String hotDeploy() {
        // 这里还没创建视图解析器，先通过curl请求，然后改动这里的内容，观察热部署的效果
        System.out.println("hello, world");
        return null;
    }
}
