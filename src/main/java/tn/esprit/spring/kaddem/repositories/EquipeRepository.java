package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Integer> {
    List<Equipe> findByNiveauLikeOrNiveauLike(Niveau SENIOR, Niveau JUNIOR);





}
