package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Contrat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {


    public List<Contrat>findByArchiveFalse();


    @Query("SELECT count(c) FROM Contrat c where ((c.archive=false) and  ((c.dateDebutContrat BETWEEN :sDate AND :eDate)) and (c.dateFinContrat BETWEEN :sDate AND :eDate))")
    public Integer getnbContratsValides(@Param("sDate") LocalDate startDate, @Param("eDate") LocalDate endDate);





  /*  @Query("SELECT c FROM Contrat c where ((c in (:listContart)) and (c.archive=false) and  ((c.dateDebutContrat BETWEEN :sDate AND :eDate)) and (c.dateFinContrat BETWEEN :sDate AND :eDate))")
   public List<Contrat> getContratsDeuxDates(@Param("listContart") List<Contrat> listContart, @Param("sDate") Date startDate, @Param("eDate") Date endDate);

*/

    @Query("SELECT c FROM Contrat c where ((c.idContrat =:idcontrat) and (c.archive=false) and  ((c.dateDebutContrat BETWEEN :sDate AND :eDate)) and (c.dateFinContrat BETWEEN :sDate AND :eDate))")
    public List<Contrat> getContratsDeuxDates(@Param("idcontrat") Integer idcontrat, @Param("sDate") LocalDate startDate, @Param("eDate") LocalDate endDate);



    @Query("select sum(c.montantContrat) from Contrat c  where ( c in :listContrats  and  datediff(:startDate,c.dateDebutContrat)<0 and datediff(:endDate,c.dateFinContrat)>0 and c.archive=false)")
    Integer getMontantContratEntreDeuxDate(@Param("listContrats") Set<Contrat> listContrats, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("select count(c) from Contrat c where ( datediff(:startDate,c.dateDebutContrat)<0 and datediff(:endDate,c.dateFinContrat)>0 and c.archive=false)")
    Integer nbContratsValides(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);



}
