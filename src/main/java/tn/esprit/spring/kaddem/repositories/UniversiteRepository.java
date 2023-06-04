package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Integer> {


}
