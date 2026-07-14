package sn.isepat.tp_etudiants.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sn.isepat.tp_etudiants.entity.Etudiant;
import sn.isepat.tp_etudiants.service.EtudiantService;

import java.util.List;


@RestController
@RequestMapping("/etudiants")
public class EtudiantController {


    private final EtudiantService service;


    public EtudiantController(EtudiantService service) {
        this.service = service;
    }



    // ============================
    // AJOUT ETUDIANT
    // ============================

    @Operation(
            summary = "Ajouter un étudiant",
            description = "Permet de créer un nouvel étudiant dans la base de données."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Étudiant créé avec succès"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Les données envoyées sont invalides"
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "Le matricule ou l'email existe déjà"
            )
    })
    @PostMapping
    public ResponseEntity<Etudiant> create(
            @Valid @RequestBody Etudiant etudiant) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(etudiant));
    }



    // ============================
    // LISTE DES ETUDIANTS
    // ============================

    @Operation(
            summary = "Afficher tous les étudiants",
            description = "Retourne la liste complète des étudiants triés par nom."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste récupérée avec succès"
    )
    @GetMapping
    public ResponseEntity<List<Etudiant>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }




    // ============================
    // RECHERCHE PAR ID
    // ============================

    @Operation(
            summary = "Rechercher un étudiant par ID",
            description = "Retourne un étudiant grâce à son identifiant."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Étudiant trouvé"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Étudiant introuvable"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }




    // ============================
    // RECHERCHE PAR MATRICULE BONUS
    // ============================

    @Operation(
            summary = "Rechercher un étudiant par matricule",
            description = "Permet de retrouver un étudiant grâce à son matricule."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Étudiant trouvé"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Aucun étudiant trouvé"
            )
    })
    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Etudiant> findByMatricule(
            @PathVariable String matricule) {

        return ResponseEntity.ok(
                service.findByMatricule(matricule)
        );
    }




    // ============================
    // SUPPRESSION
    // ============================

    @Operation(
            summary = "Supprimer un étudiant",
            description = "Supprime un étudiant existant."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "204",
                    description = "Suppression réussie"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Étudiant introuvable"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}