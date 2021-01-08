package fr.esupportail.esupstage.domain.jpa.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the NatureTravail database table.
 *
 */
@Entity
@Table(name = "NatureTravail")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "NatureTravail.findAll", query = "SELECT n FROM NatureTravail n")
public class NatureTravail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer idNatureTravail;
    @Column(nullable = false, length = 150)
    private String libelleNatureTravail;
    @Column(nullable = false, length = 1)
    private String temEnServNatTrav;
    // bi-directional many-to-one association to Convention
    @OneToMany(mappedBy = "natureTravail")
    private List<Convention> conventions;

    public Convention addConvention(Convention convention) {
        getConventions().add(convention);
        convention.setNatureTravail(this);
        return convention;
    }

    public Convention removeConvention(Convention convention) {
        getConventions().remove(convention);
        convention.setNatureTravail(null);
        return convention;
    }
}