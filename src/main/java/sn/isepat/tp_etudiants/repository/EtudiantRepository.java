package sn.isepat.tp_etudiants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isepat.tp_etudiants.entity.Etudiant;

import java.util.List;
import java.util.Optional;


public interface EtudiantRepository 
        extends JpaRepository<Etudiant, Long> {


    Optional<Etudiant> findByMatricule(String matricule);


    List<Etudiant> findAllByOrderByNomAsc();


    boolean existsByMatricule(String matricule);


    boolean existsByEmail(String email);

}