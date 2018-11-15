package org.junfalu.springboot.springbootlearn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lujunfa
 * @Date: 2018/11/15 09:32
 * @Description:
 */

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index(){
        return "hello";
    }
}
