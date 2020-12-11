package fr.esupportail.esupstage.domain.jpa.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TempsTravail database table.
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TempsTravail")
@NamedQuery(name = "TempsTravail.findAll", query = "SELECT t FROM TempsTravail t")
public class TempsTravail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer idTempsTravail;
    @Column(nullable = false, length = 20)
    private String codeCtrl;
    @Column(nullable = false, length = 200)
    private String libelleTempsTravail;
    private boolean modifiable;
    @Column(nullable = false, length = 1)
    private String temEnServTempsTravail;
    // bi-directional many-to-one association to Convention
    @OneToMany(mappedBy = "tempsTravail")
    private List<Convention> conventions;
    // bi-directional many-to-one association to Offre
    @OneToMany(mappedBy = "tempsTravail")
    private List<Offre> offres;


    public Convention addConvention(Convention convention) {
        getConventions().add(convention);
        convention.setTempsTravail(this);
        return convention;
    }

    public Convention removeConvention(Convention convention) {
        getConventions().remove(convention);
        convention.setTempsTravail(null);
        return convention;
    }

    public Offre addOffre(Offre offre) {
        getOffres().add(offre);
        offre.setTempsTravail(this);
        return offre;
    }

    public Offre removeOffre(Offre offre) {
        getOffres().remove(offre);
        offre.setTempsTravail(null);
        return offre;
    }
}