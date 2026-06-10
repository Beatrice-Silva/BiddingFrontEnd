/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import tools.jackson.databind.ObjectMapper;

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
    
    @GetMapping("{id}/lances")
    public String listarPorId(
            HttpSession session
    ){
        
        if(id editais =id lance){
            return "redirect:/lance";
        }
        
        return "redirect:/lances";
    }
    
    @GetMapping("?urgente=true")
    public String listaurgante(
            HttpSession session
            
    ){
        
        Object token = session.getAttribute("token");
        if(token == null){
            return "redirect:/login";
        }
        
        
        try{
        restService.();
                
        }catch(HttpStatusCodeException ex)
        
            String mensagemErroDoBackEnd = new ObjectMapper()
                    .readTree(
                    ex.getResponseBodyAsString()
                    ).get("message").asString();
            redirectAttributes.addFlashAttribute(
            "erroServidor",
                    mensagemErroDoBackEnd
                    );
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("erroServidor", e.getMessage());
            return "redirect:/editais"
        }
        
        return "lances";
    }
  
    
    
    
}
