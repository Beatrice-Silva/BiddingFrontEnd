/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.controller;

import com.bidding.system.frontend.bidding_frontend.model.EditalDTO;
import com.bidding.system.frontend.bidding_frontend.service.AuthRestClientService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tools.jackson.databind.ObjectMapper;

/**
 *
 * @author Aluno
 */
@Controller
@RequestMapping("/editais")
public class EditalController {
    
    @Autowired AuthRestClientService service;
    
    //regras de negocio devem ser aplicadas =  teste
    @GetMapping("/list")
    public String listar(
            @RequestParam(required = false, defaultValue = "false") boolean urgente,
            HttpSession session, 
            Model model,
            RedirectAttributes redirectAttributes
    ){
        Object token = session.getAttribute("token");        
        if(token == null){
            return "redirect:/login";
        }
        
        try{
            //urgente backend
            List<EditalDTO> editais = restService.buscarEditais(urgente)
            model.addAttribute("editais", editais);
                    
            return "editais";
        }catch (Exception e){
        redirectAttributes.addFlashAttribute("erroServidor", "Erro ao carregar editais");
        return "redirect:/";
        }
      
    }
    
    @GetMapping("/{id}/lances")
    public String listarPorId(
            @PathVariable Long id,
            HttpSession session
    ){
        Object token = session.getAttribute("token");
        
        if(token == null){
            return "redirect:/login";
        }
        return "/lances";
    }
    
    
    
    
    
    



}
  

