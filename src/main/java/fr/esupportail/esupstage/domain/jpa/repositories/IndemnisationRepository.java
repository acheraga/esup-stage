package fr.esupportail.esupstage.domain.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esupportail.esupstage.domain.jpa.entities.Indemnisation;

public interface IndemnisationRepository extends JpaRepository<Indemnisation, Integer> {
}
