package fr.esupportail.esupstage.domain.jpa;

import fr.esupportail.esupstage.AbstractTest;
import fr.esupportail.esupstage.domain.jpa.entities.*;
import fr.esupportail.esupstage.domain.jpa.repositories.ConfidentialiteRepository;
import fr.esupportail.esupstage.domain.jpa.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Rollback
@Transactional
class ContactRepositoryTest extends AbstractTest {

		private final EntityManager entityManager;

		private final ContactRepository contactRepository;
		private int contactId;

		@Autowired ContactRepositoryTest(final EntityManager entityManager,
				final ContactRepository contactRepository) {
				super();
				this.entityManager = entityManager;
				this.contactRepository = contactRepository;
		}

		@BeforeEach
		void prepare() {

				final NiveauCentre niveauCentre = new NiveauCentre();
				niveauCentre.setLibelleNiveauCentre("libel");
				niveauCentre.setTemEnServNiveauCentre("A");

				entityManager.persist(niveauCentre);

				final Confidentialite confidentialite = new Confidentialite();
				confidentialite.setCodeConfidentialite("A");
				confidentialite.setLibelleConfidentialite("libel");
				confidentialite.setTemEnServConfid("A");
				entityManager.persist(confidentialite);


				final CentreGestion centreGestion = new CentreGestion();
				centreGestion.setAutorisationEtudiantCreationConvention(true);
				centreGestion.setCodeUniversite("codeuniv");
				centreGestion.setDateCreation(new Date(0));
				centreGestion.setIdModeValidationStage(1);
				centreGestion.setLoginCreation("login");
				centreGestion.setConfidentialite(confidentialite);
				centreGestion.setNiveauCentre(niveauCentre);
				entityManager.persist(centreGestion);

				final Pays pays = new Pays();
				pays.setActual(1);
				pays.setLib("lib");
				pays.setTemEnServPays("A");
				pays.setCog(1);
				entityManager.persist(pays);

				final Effectif effectif = new Effectif();
				effectif.setLibelleEffectif("libel");
				effectif.setTemEnServEffectif("A");
				entityManager.persist(effectif);

				final TypeStructure typeStructure = new TypeStructure();
				typeStructure.setLibelleTypeStructure("libel");
				typeStructure.setTemEnServTypeStructure("A");
				entityManager.persist(typeStructure);

				final Structure structure = new Structure();
				structure.setDateCreation(new Date());
				structure.setEstValidee(1);
				structure.setLoginCreation("login");
				structure.setRaisonSociale("raison");
				structure.setVoie("voie");
				structure.setEffectif(effectif);
				structure.setPay(pays);
				structure.setTypeStructure(typeStructure);
				entityManager.persist(structure);


				Service service = new Service();
				service.setCodePostal("17000");
				service.setNom("name");
				service.setTelephone("0147847584");
				service.setPay(pays);
				service.setVoie("road");
				service.setStructure(structure);
				entityManager.persist(service);

				final Contact contact = new Contact();
				contact.setDateCreation(new Date(0));
				contact.setFonction("function");
				contact.setLoginCreation("login");
				contact.setNom("name");
				contact.setPrenom("firstname");
				contact.setCentreGestion(centreGestion);
				contact.setService(service);
				entityManager.persist(contact);

				this.contactId = contact.getIdContact();
				entityManager.flush();
		}


		@Test
		@DisplayName("findById – Nominal test case")
		void findById() {
				final Optional<Contact> result = contactRepository.findById(this.contactId);
				assertTrue(result.isPresent(), "We should have found our Contact");

				final Contact contact = result.get();
				assertEquals(new Date(0),contact.getDateCreation());
				assertEquals("function",contact.getFonction());
				assertEquals("login",contact.getLoginCreation());
				assertEquals("name",contact.getNom());
				assertEquals("firstname",contact.getPrenom());
				assertEquals("codeuniv",contact.getCentreGestion().getCodeUniversite());
				assertEquals("17000",contact.getService().getCodePostal());
		}


}
