package ed.citizen.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ed.citizen.info.entity.CitizenPlan;

@Repository
public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {
	
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlanNames();
	
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();

	
}
