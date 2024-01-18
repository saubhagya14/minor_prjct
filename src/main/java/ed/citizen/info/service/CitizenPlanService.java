package ed.citizen.info.service;

import java.util.List;

import ed.citizen.info.binding.SearchCriteria;
import ed.citizen.info.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;

public interface CitizenPlanService {
	
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<CitizenPlan> getCitizens(SearchCriteria searchCriteria);
	public void generateExcel(HttpServletResponse httpServletResponse);
	public void generatePdf(HttpServletResponse httpServletResponse);
	
}
