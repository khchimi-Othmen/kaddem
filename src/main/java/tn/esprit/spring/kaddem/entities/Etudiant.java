package tn.esprit.spring.kaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    @Enumerated(EnumType.STRING)
    private Option op;
    @OneToMany(mappedBy="etudiant", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Contrat> Contrats ;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Departement departement;

    @ManyToMany(mappedBy="etudiants")
    @JsonIgnore
    @ToString.Exclude
  //  private Set<Equipe> equipes ;
    private List<Equipe> equipes ;

}
