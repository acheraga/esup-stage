package fr.esupportail.esupstage.domain.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esupportail.esupstage.domain.jpa.entities.PstagedataUser;

public interface PstagedataUserRepository extends JpaRepository<PstagedataUser, String> {
}
