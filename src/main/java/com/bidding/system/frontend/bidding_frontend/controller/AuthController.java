/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.controller;

import com.bidding.system.frontend.bidding_frontend.model.AuthResponseDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserRequestDTO;
import com.bidding.system.frontend.bidding_frontend.service.ApiService;
import com.bidding.system.frontend.bidding_frontend.service.AuthRestClientService;
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
    private AuthRestClientService restService;
    
    @GetMapping("/")
    public String home(
            HttpSession session
    ){
        return "index";   
    }
    
    @GetMapping("/login")
    public String login(Model model){
        UserRequestDTO credentials = new UserRequestDTO();
        model.addAttribute("credenciais",credentials);
        return "login";
    }
    
    @PostMapping("/logar")
    public String logar(@ModelAttribute UserRequestDTO credentials, HttpSession session) {
            String token = restService.logar(credentials);

            System.out.println("token"+ token);
            session.setAttribute("token", token);
            
            return "redirect:/";
            
        }
   

    @GetMapping("/registrar")
    public String registrar(Model model) {

        UserDTO newUser = new UserDTO();
        model.addAttribute("user", newUser);
        return "registrar";
    }



    @PostMapping("/registrar")
    public String mandaRregistro(@ModelAttribute UserDTO user){
            restService.registrar(user);
            return "redirect:/login";
   
    }

    /*
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    */

}
    

