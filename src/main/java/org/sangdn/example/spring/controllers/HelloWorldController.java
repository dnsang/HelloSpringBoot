package org.sangdn.example.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sangdn on 10/10/15.
 */
@Controller
public class HelloWorldController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(){

    }
}
