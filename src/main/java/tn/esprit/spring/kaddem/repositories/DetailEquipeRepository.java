package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.DetailEquipe;

import java.util.Date;
import java.util.List;

@Repository
public interface DetailEquipeRepository extends JpaRepository<DetailEquipe, Integer> {



}
