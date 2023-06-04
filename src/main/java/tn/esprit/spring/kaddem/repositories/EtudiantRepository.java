package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;

import java.util.List;
import java.util.Set;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {

@Query("Select e From Etudiant e where e.nomE= :nomE and e.prenomE= :prenomE")
    public Etudiant getETu(@Param("nomE") String nomE, @Param("prenomE") String prenomE);

    public Etudiant findByNomELikeAndPrenomELike(String nom, String prenom);


    List<Etudiant> findByDepartementIdDepart(Integer idDepat);

    List<Etudiant> findByEquipesNiveau(Niveau niveau);
    List<Etudiant> findByEquipesNiveauLike(Niveau SENIOR);

}
