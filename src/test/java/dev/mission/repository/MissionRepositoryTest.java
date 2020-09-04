package dev.mission.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import dev.mission.entite.Mission;

@DataJpaTest
@ActiveProfiles({"insert", "lister"})
public class MissionRepositoryTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(Logger.class);
	private Mission mission;
	
	@Autowired 
	private MissionRepository missionRepository;
	@Autowired 
	private TestEntityManager entityManager;

	
	@BeforeEach
	public void init() {
		this.mission = new Mission();
		this.mission.setDateDebut(LocalDate.parse("2030-01-01"));
		this.mission.setDateFin(LocalDate.parse("2031-01-01"));
		this.mission.setLibelle("tartelette");
		this.mission.setTauxJournalier(BigDecimal.valueOf(112.12));
	}
	
	@Test
	public void findByDateDebutGreaterThanEqual() {
		//persist
		this.entityManager.persist(this.mission);
		
		List<Mission> missions = this.missionRepository.findByDateDebutGreaterThanEqual(LocalDate.now());
		LocalDate dateDeMission = null;
		
		for(Mission m : missions) {
			dateDeMission = m.getDateDebut();
			assertThat(dateDeMission).isAfter(LocalDate.now());
		}
	}
	
	@Test 
	void findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual(){
		//persist
		this.entityManager.persist(this.mission);
		List<Mission> missions = this.missionRepository.findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual(LocalDate.now(), this.mission.getTauxJournalier());
		int size = missions.size();
		

		assertThat(missions.size()).isEqualTo(1);
	}
	
}
