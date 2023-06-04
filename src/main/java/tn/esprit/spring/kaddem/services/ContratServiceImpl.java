package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.interfaces.IContratService;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.sql.Array;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

//@SessionScope
//@RequestScope
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
@Slf4j
//@AllArgsConstructor
//@Primary

public class ContratServiceImpl implements IContratService {
    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    UniversiteRepository universiteRepository;

    public List<Contrat> retrieveAllContrats() {

        return contratRepository.findAll();
    }

    @Override
    public List<Contrat> retrieveAllContratffs() {
        return null;
    }

    public Contrat updateContrat(Contrat ce) {

        return contratRepository.save(ce);
    }

    public Contrat addContrat(Contrat ce) {

        return contratRepository.save(ce);
    }

    public Contrat retrieveContrat(Integer idContrat) {

        return contratRepository.findById(idContrat).orElse(null);
    }

    public void removeContrat(Integer idContrat) {
        contratRepository.deleteById(idContrat);
    }


    public Contrat affectContratToEtudiant(Integer idContrat, String nomE, String prenomE) {
        Etudiant e = etudiantRepository.findByNomELikeAndPrenomELike(nomE, prenomE);
        Contrat c = contratRepository.findById(idContrat).get();

        int nbContratssActifs = 0;
        if (!e.getContrats().isEmpty()) { //contrats.size()!=0
            for (Contrat contrat : e.getContrats()) {
                if (((contrat.getArchive()) != null) && (!(contrat.getArchive()))) { //contrat.getArchive())==false
                    nbContratssActifs++;
                }
            }
        }
        if (nbContratssActifs <= 4) {
            c.setEtudiant(e);
            contratRepository.save(c);
        }


        //	Set<Contrat> contrats= e.getContrats();

        return c;
    }

    public float getMontantContratEntreDeuxDate(int idUniv, LocalDate startDate, LocalDate endDate) {
        Universite u = universiteRepository.findById(idUniv).orElse(null);

        AtomicReference<Float> MontantContratEntreDeuxDate = new AtomicReference<>();
        u.getDepartements().forEach(
                departement -> {
                    departement.getEtudiants().forEach(
                            etudiant -> etudiant.getContrats().forEach(contrat -> {
                                if (contrat.getMontantContrat() != null) {
                                    contratRepository.getContratsDeuxDates(contrat.getIdContrat(), startDate, endDate);
                                    MontantContratEntreDeuxDate.getAndSet(MontantContratEntreDeuxDate.get() + contrat.getMontantContrat());
                                }

                            }));
                }
        );

        return MontantContratEntreDeuxDate.get();
    }

    public float getMontantContratEntreDeuxDate2(int idUniv, LocalDate startDate, LocalDate endDate) {
        Integer montant = 0;
        Universite u = universiteRepository.findById(idUniv).orElse(null);
        Set<Departement> deps = u.getDepartements();
        for (Departement d : deps) {
            Set<Etudiant> etudiants = d.getEtudiants();

            for (Etudiant e : etudiants) {
                Set<Contrat> contrats = e.getContrats();

                montant = montant + contratRepository.getMontantContratEntreDeuxDate(contrats, startDate, endDate);

            }
        }

        return montant;
    }

    public Integer nbContratsValides(LocalDate startDate, LocalDate endDate) {
        return contratRepository.getnbContratsValides(startDate, endDate);
    }
    //@Scheduled(cron="*/5 * * * * *")//(cron="0 0 13 * * *")
    public void retrieveAndUpdateStatusContrat() {
        List<Contrat> contrats = contratRepository.findByArchiveFalse();

        List<Contrat> contrats15j = new ArrayList<>();

        for (Contrat contrat : contrats) {
            long difference_In_Days = ChronoUnit.DAYS.between(LocalDate.now(), contrat.getDateFinContrat());
            if (difference_In_Days <= 15) {
                contrats15j.add(contrat);
                //System.out.println(difference_In_Days);
                log.info(" Contrat : " + contrats15j);
            }
            if (difference_In_Days == 0) {
                contrat.setArchive(true);
                contratRepository.save(contrat);
            }
        }
    }


}
