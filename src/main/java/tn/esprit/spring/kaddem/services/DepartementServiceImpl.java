package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.interfaces.IDepartementService;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Date;
import java.util.List;

@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService {
	@Autowired
	DepartementRepository departementRepository;
	public List<Departement> retrieveAllDepartements(){
		long startDate = new Date().getTime();
        log.info("start date");
		log.info("debut methode retrieveAllDepartements");
		List<Departement> listdep=  departementRepository.findAll();
		long endDate = new Date().getTime();
		long executionTime = endDate - startDate;
		log.info("executionTime: " + executionTime);
		return listdep;
	}

	public Departement addDepartement (Departement d){
		log.info("debut methode addDepartement");
		return departementRepository.save(d);
	}

	public   Departement updateDepartement (Departement d){
		log.info("debut methode updateDepartement");
		return departementRepository.save(d);
	}

	public  Departement retrieveDepartement (Integer idDepart){
		log.info("debut methode retrieveDepartement");
		return departementRepository.findById(idDepart).get();
	}
	public  void deleteDepartement(Integer idDepartement){

		log.info("debut methode deleteDepartement");
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}



}
