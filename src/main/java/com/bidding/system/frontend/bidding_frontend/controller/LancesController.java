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
@RequestMapping("/api")
public class LancesController {
    
    @GetMapping("/lances/meus-lances")
    public String meuslances(
            HttpSession session
    ){
        Object token = session.getAttribute("token");
        
        if(token == null){
            return "redirect:/lances";
        }
        return "editais";
    }
    
 
}
