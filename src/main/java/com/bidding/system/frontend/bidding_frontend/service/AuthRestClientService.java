/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.service;

import com.bidding.system.frontend.bidding_frontend.model.EditalDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserRequestDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class AuthRestClientService {
    
    private final RestClient restClient;
    
    public AuthRestClientService(){
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/api")
                .build();
    }
    
    public String logar(UserRequestDTO user) {
        return restClient.post()
                .uri("/auth/logar")
                .body(user)
                .retrieve()
                .body(String.class);
    }
    
    public void registrar(UserDTO user){
        if(!user.getSenha().equals(user.getConfirmarsenha())){
             throw new ResponseStatusException(
                     HttpStatusCode.valueOf(400), 
                     "'Senha' e 'Confirmar senha' devem coincidir!" );
        }
        user.setRole("FORNECEDOR");
        String retorno = 
                restClient.post()
                .uri("/auth/registrar")
                .retrieve()
                .body(String.class);
    }
    
   
 
    public List<EditalDTO> listarEditais(String token){
        
        
        EditalDTO[] editais = restClient.get()
                .uri("/editais")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(EditalDTO[].class);
        
        return Arrays.asList(editais);
    }
    
}
