package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Equipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    //@ManyToMany(mappedBy="equipes")
    @ManyToMany(cascade =CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Etudiant> etudiants ;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private DetailEquipe detailEquipe;

}
