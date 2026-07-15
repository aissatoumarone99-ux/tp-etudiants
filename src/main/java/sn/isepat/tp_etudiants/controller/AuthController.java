package sn.isepat.tp_etudiants.controller;

import sn.isepat.tp_etudiants.dto.AuthResponse;
import sn.isepat.tp_etudiants.dto.LoginRequest;
import sn.isepat.tp_etudiants.dto.RegisterRequest;
import sn.isepat.tp_etudiants.model.Utilisateur;
import sn.isepat.tp_etudiants.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Inscription et connexion des utilisateurs")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        Utilisateur utilisateur = authService.inscrire(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.authentifier(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}