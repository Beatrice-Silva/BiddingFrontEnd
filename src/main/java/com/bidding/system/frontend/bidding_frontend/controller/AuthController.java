/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.controller;

import com.bidding.system.frontend.bidding_frontend.model.AuthResponseDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserRequestDTO;
import com.bidding.system.frontend.bidding_frontend.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



/**
 *
 * @author BEATRICE
 */
@Controller
public class AuthController {
    
    @Autowired
    private ApiService apiService;
    
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("userRequest", new UserRequestDTO());
        return "login";
    }
    
    @PostMapping("/login")
    public String logar(@ModelAttribute UserRequestDTO dto, HttpSession session) {
        try {
            // Agora recebemos o DTO completo com token e role
            AuthResponseDTO authResponse = apiService.logar(dto);
            
            // Salvamos ambos na HttpSession para uso posterior
            session.setAttribute("token", authResponse.getToken());
            session.setAttribute("role", authResponse.getRole());
            
            return "redirect:/editais";
        } catch (Exception e) {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute UserDTO user) {
        try {
            apiService.registrarUser(user);
            return "redirect:/login?success=true";
        } catch (Exception e) {
            return "redirect:/register?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Limpa a sessão do usuário completamente
        return "redirect:/login";
    }
}
    

