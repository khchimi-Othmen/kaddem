package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.interfaces.IEquipeService;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService {
	EquipeRepository equipeRepository;
	DetailEquipeRepository detailEquipeRepository;
	public List<Equipe> retrieveAllEquipes(){
	return  equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){

	/*	DetailEquipe detailEquipe = e.getDetailEquipe();
		detailEquipeRepository.save(detailEquipe);
		e.setDetailEquipe(detailEquipe);*/
		return  equipeRepository.save(e);
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId){
		return equipeRepository.findById(equipeId).get();
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes(){
		List<Equipe> equipes = equipeRepository.findByNiveauLikeOrNiveauLike(Niveau.SENIOR,Niveau.JUNIOR);
		for (Equipe equipe : equipes) {
		 AtomicReference<Integer> nbEtudiantsAvecContratsActifs= new AtomicReference<>(0);
			equipe.getEtudiants().forEach(
					etudiant -> {
						etudiant.getContrats().forEach(
								contrat -> {
									long difference_In_Years = ChronoUnit.YEARS.between(LocalDate.now(),contrat.getDateDebutContrat());
									if ((!contrat.getArchive()) && (difference_In_Years > 1)) {
										nbEtudiantsAvecContratsActifs.getAndSet(nbEtudiantsAvecContratsActifs.get() + 1);
									}
								}
						);
					}
			);

			if (nbEtudiantsAvecContratsActifs.get() >= 3) {
				if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
					equipe.setNiveau(Niveau.SENIOR);
					equipeRepository.save(equipe);
					break;
				}
				if (equipe.getNiveau().equals(Niveau.SENIOR)) {
					equipe.setNiveau(Niveau.EXPERT);
					equipeRepository.save(equipe);
					break;
				}
			}

				/*for (Etudiant etudiant : etudiants) {
					Set<Contrat> contrats = etudiant.getContrats();
					for (Contrat contrat : contrats) {
						Date dateSysteme = new Date();
						long difference_In_Time = contrat.getDateFinContrat().getTime() - dateSysteme.getTime();
						long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
						if ((contrat.getArchive() == false) && (difference_In_Years > 1)) {
							nbEtudiantsAvecContratsActifs++;
						}
					}
				}*/
				/*	if (nbEtudiantsAvecContratsActifs >= 3){
						if (equipe.getNiveau().equals(Niveau.JUNIOR)){
							equipe.setNiveau(Niveau.SENIOR);
							equipeRepository.save(equipe);
						}
						if (equipe.getNiveau().equals(Niveau.SENIOR)){
							equipe.setNiveau(Niveau.EXPERT);
							equipeRepository.save(equipe);
						}
				}*/
			}

		}
}
