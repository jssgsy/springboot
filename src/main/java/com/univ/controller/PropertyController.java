package com.univ.controller;

import com.univ.property.UnivProperty;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author univ
 * @datetime 2018/12/3 8:08 PM
 * @description 入门级Controller
 */

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Resource
    private UnivProperty univProperty;

    @GetMapping("/starter/test")
    @ResponseBody
    public String starter(){
        System.out.println(univProperty);
        return "ok";
    }
}
