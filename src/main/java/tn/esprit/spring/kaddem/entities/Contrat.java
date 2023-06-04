package tn.esprit.spring.kaddem.entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contrat implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer idContrat;
   // @Temporal(TemporalType.DATE)
    private LocalDate dateDebutContrat;
   // @Temporal(TemporalType.DATE)
    private LocalDate dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    private Etudiant etudiant;


}
