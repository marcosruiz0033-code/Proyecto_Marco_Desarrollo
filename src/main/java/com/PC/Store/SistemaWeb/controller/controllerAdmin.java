package com.PC.Store.SistemaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class controllerAdmin {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "Admin/dashboard";
    }

    @GetMapping("/productos")
    public String AdminProductos(){

        return "Admin/AdminProductos";
    }

    @GetMapping("/pedidos")
    public String AdminPedidos(){

        return "Admin/AdminPedidos";
    }

    @GetMapping("/usuarios")
    public String AdminUsuarios(){

        return "Admin/AdminUsuarios";
    }
}
