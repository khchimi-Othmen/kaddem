package tn.esprit.spring.kaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.interfaces.IContratService;

import javax.annotation.Resources;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
//@Tag(name = "Contrat")
@RestController
@Slf4j
//@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {
	@Autowired
	//@Qualifier("contratServiceImpl")
	IContratService contratService;
	
	// http://localhost:8089/Kaddem/contrat/retrieve-all-contrats
	//@Operation(description = "Afficher les contrats")

	@GetMapping("/retrieve-all-contrats")
	public List<Contrat> getContrats() {
		List<Contrat> listContrats = contratService.retrieveAllContrats();
		return listContrats;
	}
	// http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
	@GetMapping("/retrieve-contrat/{contrat-id}")
	public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
		return contratService.retrieveContrat(contratId);
	}

	// http://localhost:8089/Kaddem/econtrat/add-contrat
	@PostMapping("/add-contrat")
	public Contrat addContrat(@RequestBody Contrat c) {
		Contrat contrat = contratService.addContrat(c);
		return contrat;
	}

	// http://localhost:8089/Kaddem/contrat/remove-contrat/1
	@DeleteMapping("/remove-contrat/{contrat-id}")
	public void removeContrat(@PathVariable("contrat-id") Integer contratId) {

		contratService.removeContrat(contratId);
	}

	// http://localhost:8089/Kaddem/contrat/update-contrat
	@PutMapping("/update-contrat")
	public Contrat updateContrat(@RequestBody Contrat c) {
		Contrat contrat= contratService.updateContrat(c);
		return contrat;
	}


	@PutMapping(value = "/assignContratToEtudiant/{idContrat}/{nomE}/{prenomE}")
	public Contrat assignContratToEtudiant (Integer idContrat, String nomE, String prenomE){
	//	Contrat c= contratService.affectContratToEtudiant()
		return 	(contratService.affectContratToEtudiant(idContrat, nomE, prenomE));
	}



	//public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

	@GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}/{idUniv}")
	@ResponseBody
	public float calculContratEntreDeuxDate(@PathVariable(name = "idUniv") int idUniv,
											@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	@PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		return contratService.getMontantContratEntreDeuxDate(idUniv,startDate, endDate);
	}


	//The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
	@GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
	public Integer getnbContratsValides(@PathVariable(name = "startDate")
											@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
											LocalDate s,
										@PathVariable(name = "endDate")
										@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
										LocalDate endDate) {

		return contratService.nbContratsValides(s, endDate);
	}

	//Only no-arg methods may be annotated with @Scheduled
	@Scheduled(cron="*/5 * * * * *")//(cron="0 0 13 * * *")
	@PutMapping(value = "/majStatusContrat")
	public void majStatusContrat (){
		//return 	(contratService.affectContratToEtudiant(ce, nomE, prenomE));
		contratService.retrieveAndUpdateStatusContrat();

	}
	//@Scheduled(cron="* * * * * *")
	//@Scheduled(cron = "5 * * * * *") //23:41:05
	//@Scheduled(cron = "*/5 * * * * *")
	//@Scheduled(cron = "0/5 * * * * *")
	//@Scheduled(cron = "0 0/30 11 * * *")
	//@Scheduled(cron = "0 0 8 ? 4 ?") or (cron = "0 0 8 * 4 *")
	//@Scheduled(cron = « 0 0 9 14 2 SUN,TUE")
	@GetMapping(value = "/SpringScheduler")
	public void SpringScheduler (){
		log.info("hello");

	}
}


