package dev.mission.exec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

public class ListerProchainesMissionsParTJM implements Runnable{
	private MissionRepository repo;
	private Logger LOG = LoggerFactory.getLogger(Logger.class);
	
	public ListerProchainesMissionsParTJM(MissionRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void run() {
		BigDecimal big = BigDecimal.valueOf(100.90);
     	List<Mission> missions = this.repo.findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual(LocalDate.now(), big);
		
		for(Mission m : missions) {
			LOG.info(m.toString());
		}
	}
}
