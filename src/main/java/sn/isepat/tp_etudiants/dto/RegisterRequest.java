package sn.isepat.tp_etudiants.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nom;
    private String email;
    private String motDePasse;
}