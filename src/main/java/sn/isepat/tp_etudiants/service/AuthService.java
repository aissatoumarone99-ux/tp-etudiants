package sn.isepat.tp_etudiants.service;

import sn.isepat.tp_etudiants.dto.LoginRequest;
import sn.isepat.tp_etudiants.dto.RegisterRequest;
import sn.isepat.tp_etudiants.exception.AuthentificationException;
import sn.isepat.tp_etudiants.exception.EmailDejaExistantException;
import sn.isepat.tp_etudiants.model.Role;
import sn.isepat.tp_etudiants.model.Utilisateur;
import sn.isepat.tp_etudiants.repository.UtilisateurRepository;
import sn.isepat.tp_etudiants.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Utilisateur inscrire(RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new EmailDejaExistantException("Cet email existe déjà.");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole(Role.USER);

        return utilisateurRepository.save(utilisateur);
    }

    public String authentifier(LoginRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthentificationException("Email ou mot de passe incorrect."));

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new AuthentificationException("Email ou mot de passe incorrect.");
        }

        return jwtUtil.generateToken(utilisateur.getEmail());
    }
}