package com.PC.Store.SistemaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class inicio {

    @GetMapping("/")
    public String home() {

        return "Modulos/index";
    }

    @GetMapping("/productos")
    public String productos(){

        return "Modulos/productos";
    }

    @GetMapping("/nosotros")
    public String nosotros(){

        return "Modulos/nosotros";
    }

    @GetMapping("/contacto")
    public String contacto(){

        return "Modulos/contacto";
    }


}
