package fr.esupportail.esupstage.domain.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esupportail.esupstage.domain.jpa.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

	public List<Etudiant> findEtudiantsByNomContainsOrPrenomContains(String nom, String prenom);

	public Etudiant findEtudiantByIdEtudiantAndCodeUniversite(Integer idEtudiant, String codeUniveriste);

	public Etudiant findEtudiantByIdentEtudiantAndCodeUniversite(String identEtudiant, String codeUniveriste);
}
