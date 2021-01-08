package fr.esupportail.esupstage.domain.jpa.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the Categorie database table.
 *
 */
@Entity
@Table(name = "Categorie")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer idCategorie;
    @Column(nullable = false)
    private Integer typeCategorie;
    // bi-directional many-to-one association to Critere
    @OneToMany(mappedBy = "categorie")
    private List<Critere> criteres;

    public Critere addCritere(Critere critere) {
        getCriteres().add(critere);
        critere.setCategorie(this);
        return critere;
    }

    public Critere removeCritere(Critere critere) {
        getCriteres().remove(critere);
        critere.setCategorie(null);
        return critere;
    }
}