package com.univ.controller;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author univ 2022/9/2 9:09 上午
 */
@RestController
@RequestMapping("/db")
public class DbController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping("/connect")
    public List<Map<String, Object>> t1() {
        String sql = "select * from test";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(JSONObject.toJSONString(list));
        return list;
    }

}
