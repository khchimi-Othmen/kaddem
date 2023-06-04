package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailEquipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;

    @JsonIgnore
    @OneToOne(mappedBy="detailEquipe")
    @ToString.Exclude
    private Equipe equipe;

}
