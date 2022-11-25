package com.feather.manage.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: bz-system
 * @package: com.feather.manage.api
 * @className: TestApi
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-25 23:13
 * @version: 1.0
  **/
@RequestMapping("/test")
@RestController
public class TestApi {

    @GetMapping("/hello")
    public String test(){
        return  "hello!";
    }
}
