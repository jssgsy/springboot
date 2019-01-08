package com.univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.univ.entity.ConfigurationPropertiesDemo;

/**
 * @author univ
 * @datetime 2018/12/3 8:08 PM
 * @description 入门级Controller
 */

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private ConfigurationPropertiesDemo configurationPropertiesDemo;

    @RequestMapping("/hotDeploy")
    @ResponseBody
    public String hotDeploy() {
        // 这里还没创建视图解析器，先通过curl请求，然后改动这里的内容，观察热部署的效果
        System.out.println("hello, world");
        return "ok";
    }

    /**
     * @ConfigurationProperties的使用
     * @return
     */
    @GetMapping("/configurationProperties")
    @ResponseBody
    public ConfigurationPropertiesDemo configuration() {
        // 千万不要使用new的形式，否则当然获取不到配置的信息，ConfigurationPropertiesDemo的实例已经在bean中了！
        /*ConfigurationPropertiesDemo configurationPropertiesDemo = new ConfigurationPropertiesDemo();
        System.out.println(configurationPropertiesDemo.getName());*/

        return configurationPropertiesDemo;
    }
}
