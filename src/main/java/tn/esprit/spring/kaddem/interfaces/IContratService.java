package tn.esprit.spring.kaddem.interfaces;

import tn.esprit.spring.kaddem.entities.Contrat;
import java.time.LocalDate;
import java.util.List;

public interface IContratService {
    public List<Contrat> retrieveAllContrats();
    public List<Contrat> retrieveAllContratffs();

    public Contrat updateContrat (Contrat  ce);

    public  Contrat addContrat (Contrat ce);

    public Contrat retrieveContrat (Integer  idContrat);

    public  void removeContrat(Integer idContrat);

    public Contrat affectContratToEtudiant (Integer idContrat, String nomE, String prenomE);

        public 	Integer nbContratsValides(LocalDate startDate, LocalDate endDate);


    public float getMontantContratEntreDeuxDate(int idUniv, LocalDate startDate, LocalDate endDate);


    public void retrieveAndUpdateStatusContrat();
}

