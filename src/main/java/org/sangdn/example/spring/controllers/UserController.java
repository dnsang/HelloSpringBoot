package org.sangdn.example.spring.controllers;

import org.sangdn.example.spring.UserNotFoundException;
import org.sangdn.example.spring.domain.User;
import org.sangdn.example.spring.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sangdn on 10/10/15.
 */
@Controller
//using @RestController for combine @Controller & @ResponseBody
public class UserController {
    @Autowired
    IService service;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public User sayHello(@RequestParam(value = "id",defaultValue = "1") int id){
        User u =service.getUser(id);
        if(u==null){
            throw new UserNotFoundException(id);
        }
        return u;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map addNewUser(User user){
        boolean result=service.addUser(user);
        Map m = new HashMap();
        m.put("result",result);
        return m;
    }
    //remember only using Method=Delete if we toggle @Controller to @RestContrller.
    //if not, spring will don't now DELETE method -> 405 exception
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("result",false);
        mav.addObject("message","Unsupported Method, couldn't delete " + id);
        return mav;
    }
}
