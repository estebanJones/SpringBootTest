package dev.mission.exec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

@Controller
@Profile("lister")
public class ListerProchainesMissions implements Runnable {
	private MissionRepository repo;
	private Logger LOG = LoggerFactory.getLogger(Logger.class);
	
	public ListerProchainesMissions(MissionRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void run() {
		BigDecimal big = BigDecimal.valueOf(100.90);
     	List<Mission> missions = this.repo.findByDateDebutGreaterThanEqual(LocalDate.now());
		
		for(Mission m : missions) {
			LOG.info(m.toString());
		}
	}
	
}
