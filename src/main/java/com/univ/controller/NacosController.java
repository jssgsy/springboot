package com.univ.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.univ.config.NacosItemConfig;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author univ 2022/12/8 15:25
 */
@RestController
@RequestMapping("/nacos")
public class NacosController {

	@Resource
	private NacosItemConfig nacosItemConfig;

	/**
	 * 使用NacosInjected来注入ConfigService与NamingService
	 */
	@NacosInjected
	private ConfigService configService;

	@GetMapping("/config/get")
	public Map<String, ?> getConfig() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>() {{
			put("city", nacosItemConfig.getCity());
			put("name", nacosItemConfig.getName());
			put("age", nacosItemConfig.getAge());
		}};
		System.out.println(JSONObject.toJSONString(hashMap));
		return hashMap;
	}


}
