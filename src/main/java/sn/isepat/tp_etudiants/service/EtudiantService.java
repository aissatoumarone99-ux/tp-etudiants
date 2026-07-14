package sn.isepat.tp_etudiants.service;


import org.springframework.stereotype.Service;

import sn.isepat.tp_etudiants.entity.Etudiant;
import sn.isepat.tp_etudiants.exception.EtudiantIntrouvableException;
import sn.isepat.tp_etudiants.repository.EtudiantRepository;
import sn.isepat.tp_etudiants.exception.DuplicateException;

import java.util.List;


@Service
public class EtudiantService {


    private final EtudiantRepository etudiantRepository;


    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }



    // Ajouter un étudiant
    public Etudiant create(Etudiant etudiant) {


        if(etudiantRepository.existsByMatricule(etudiant.getMatricule())) {

             throw new DuplicateException(
        "L'email existe déjà"
            );
        }


        if(etudiantRepository.existsByEmail(etudiant.getEmail())) {

            throw new RuntimeException(
                "L'email existe déjà"
            );
        }


        return etudiantRepository.save(etudiant);
    }





    // Liste des étudiants
    public List<Etudiant> findAll() {

        return etudiantRepository.findAllByOrderByNomAsc();
    }





    // Recherche par ID
    public Etudiant findById(Long id) {


        return etudiantRepository.findById(id)
                .orElseThrow(
                    () -> new EtudiantIntrouvableException(
                        "Aucun étudiant trouvé avec l'id : " + id
                    )
                );
    }





    // Recherche par matricule (Bonus)
    public Etudiant findByMatricule(String matricule) {


        return etudiantRepository.findByMatricule(matricule)
                .orElseThrow(
                    () -> new EtudiantIntrouvableException(
                        "Aucun étudiant trouvé avec le matricule : " + matricule
                    )
                );
    }





    // Suppression
    public void delete(Long id) {


        Etudiant etudiant = findById(id);

        etudiantRepository.delete(etudiant);
    }

}