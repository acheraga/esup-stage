package fr.esupportail.esupstage.domain.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the StatutJuridique database table.
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "StatutJuridique")
@NamedQuery(name = "StatutJuridique.findAll", query = "SELECT s FROM StatutJuridique s")
public class StatutJuridique implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer idStatutJuridique;

	@Column(nullable = false, length = 100)
	private String libelleStatutJuridique;

	private boolean modifiable;

	@Column(nullable = false, length = 1)
	private String temEnServStatut;

	@ManyToOne
	@JoinColumn(name = "idTypeStructure", nullable = false)
	private TypeStructure typeStructure;

	@OneToMany(mappedBy = "statutJuridique")
	private List<Structure> structures;

	public Structure addStructure(Structure structure) {
		getStructures().add(structure);
		structure.setStatutJuridique(this);
		return structure;
	}

	public Structure removeStructure(Structure structure) {
		getStructures().remove(structure);
		structure.setStatutJuridique(null);
		return structure;
	}

}