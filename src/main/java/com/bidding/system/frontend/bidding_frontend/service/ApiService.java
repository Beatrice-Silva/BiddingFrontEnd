/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.bidding_frontend.service;

import com.bidding.system.frontend.bidding_frontend.model.AuthResponseDTO;
import com.bidding.system.frontend.bidding_frontend.model.EditalDTO;
import com.bidding.system.frontend.bidding_frontend.model.LanceDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserDTO;
import com.bidding.system.frontend.bidding_frontend.model.UserRequestDTO;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
/**
 *
 * @author BEATRICE
 */@Service
public class ApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    private final String BASE_URL = "http://localhost:9000";

    //logar (token e role)
    public AuthResponseDTO logar(UserRequestDTO credentials){
        return restTemplate.postForObject(BASE_URL + "/api/auth/logar", credentials, AuthResponseDTO.class);
    }
    
    //registrar user
    public void registrarUser(UserDTO user){
        restTemplate.postForObject(BASE_URL + "/api/auth/registrar", user, AuthResponseDTO.class);
    }
    
    //listar editais
    public List<EditalDTO> listarEditais(String token) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<EditalDTO>> response = restTemplate.exchange(
        BASE_URL + "/api/editais",
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<List<EditalDTO>>() {}
        );
        return response.getBody();

     }
    
    //criar edital
    public void criarEdital(EditalDTO edital, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<EditalDTO> entity = new HttpEntity<>(edital, headers);
        restTemplate.postForObject(BASE_URL + "/api/editais", entity, String.class);
    }

    //registrar lance 
    public void registrarLance(Long editalId, LanceDTO lance, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<LanceDTO> entity = new HttpEntity<>(lance, headers);
        restTemplate.postForObject(BASE_URL + "/api/editais/" + editalId + "/lances", entity, String.class);
    }
}
     