package com.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "/home/index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "/home/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "home/logout";
    }
}
