package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.interfaces.IEtudiantService;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {
	@Autowired
	IEtudiantService etudiantService;

	// http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
		return listEtudiants;
	}


	// http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
	@GetMapping("/retrieve-etudiant/{xyz}")
	public Etudiant retrieveEtudiant(@PathVariable("xyz") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}



	// http://localhost:8089/Kaddem/etudiant/add-etudiant
	@PostMapping("/add-etudiant")
	public Etudiant addEtudiant(@RequestBody Etudiant e) {
		Etudiant etudiant = etudiantService.addEtudiant(e);
		return etudiant;
	}

	// http://localhost:8089/Kaddem/etudiant/remove-etudiant/1
	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/update-etudiant
	@PutMapping("/update-etudiant")
	public Etudiant updateEtudiant(@RequestBody Etudiant e) {
		Etudiant etudiant= etudiantService.updateEtudiant(e);

		return etudiant;
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId,
											  @PathVariable("departementId")Integer departementId){
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    }
//addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe)
    /* Ajouter un étudiant tout en lui affectant un contrat et une équipe */
    @PostMapping("/add-assign-Etudiant/{idContrat}/{idEquipe}")
    @ResponseBody
    public Etudiant addEtudiantWithEquipeAndContract(@RequestBody Etudiant e,
													 @PathVariable("idContrat") Integer idContrat,
													 @PathVariable("idEquipe") Integer idEquipe) {
        Etudiant etudiant = etudiantService.addAndAssignEtudiantToEquipeAndContract(e,idContrat,idEquipe);
        return etudiant;
    }

	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {

		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

	@GetMapping("/findByEquipesNiveau/{niveau}")
	List<Etudiant> findByEquipesNiveau(@PathVariable("niveau") Niveau n){
		return etudiantService.findByEquipesNiveau(n);
	}
	@GetMapping("/findByEquipesNiveauSenior")
	List<Etudiant> findByEquipesNiveauSenior(){
		return etudiantService.findByEquipesNiveauSenior();
	}


}


