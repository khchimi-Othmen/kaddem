package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Universite implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Departement> departements;

}
