package fr.esupportail.esupstage.domain.jpa.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the ModeValidationStage database table.
 *
 */
@Entity
@Table(name = "ModeValidationStage")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "ModeValidationStage.findAll", query = "SELECT m FROM ModeValidationStage m")
public class ModeValidationStage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer idModeValidationStage;
    @Column(nullable = false, length = 150)
    private String libelleModeValidationStage;
    private boolean modifiable;
    @Column(nullable = false, length = 1)
    private String temEnServModeValid;
    // bi-directional many-to-one association to Convention
    @OneToMany(mappedBy = "modeValidationStage")
    private List<Convention> conventions;

    public Convention addConvention(Convention convention) {
        getConventions().add(convention);
        convention.setModeValidationStage(this);
        return convention;
    }

    public Convention removeConvention(Convention convention) {
        getConventions().remove(convention);
        convention.setModeValidationStage(null);
        return convention;
    }
}