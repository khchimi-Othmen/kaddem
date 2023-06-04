package tn.esprit.spring.kaddem.interfaces;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;
import java.util.Set;

public interface IUniversiteService {
   public List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite  u);

    Universite updateUniversite (Universite  u);

    Universite retrieveUniversite (Integer idUniversite);

    public  void deleteUniversite(Integer idUniversite);
//Diapo 14
    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement);

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite);


}
