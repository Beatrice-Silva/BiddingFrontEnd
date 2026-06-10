/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Aluno
 */
@Controller
@RequestMapping("/editais")
public class EditalController {
    
    //regras de negocio devem ser aplicadas =  teste
    @GetMapping("/list")
    public String listar(
            HttpSession session
    ){
        Object token = session.getAttribute("token");
        
        if(token == null){
            return "redirect:/login";
        }
        
        return "editais";
    }
    
}
