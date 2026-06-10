package com.PC.Store.SistemaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controllerLogin {
    @GetMapping("/")
    public String login() {
        return "fragments/loginlayout";
    }
}
