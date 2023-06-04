package tn.esprit.spring.kaddem.interfaces;

import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;

import java.util.List;

public interface IEtudiantService {
    public List<Etudiant> retrieveAllEtudiants();

    public Etudiant addEtudiant (Etudiant e);

    public Etudiant updateEtudiant (Etudiant e);

    public Etudiant retrieveEtudiant(Integer  idEtudiant);
    List<Etudiant>findByEquipesNiveau(Niveau niveau);
    List<Etudiant>findByEquipesNiveauSenior();
    public void removeEtudiant(Integer idEtudiant);

    //Diapo 13
    public void assignEtudiantToDepartement (Integer etudiantId, Integer departementId);
    //Diapo 15
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe);

    public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement);
}
