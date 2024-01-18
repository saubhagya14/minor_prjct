package ed.citizen.info.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ed.citizen.info.binding.SearchCriteria;
import ed.citizen.info.entity.CitizenPlan;
import ed.citizen.info.service.CitizenPlanService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CitizenPlanController {

	@Autowired
	CitizenPlanService citizenPlanService;
	

	@GetMapping("/")
	public String index(Model model) {
		getPlanNameAndPlanStatus(model);
		return "index";
	}
	
	public void getPlanNameAndPlanStatus(Model model) {
		model.addAttribute("search", new SearchCriteria());
		model.addAttribute("planNameList", citizenPlanService.getPlanNames());
		model.addAttribute("planStatusList", citizenPlanService.getPlanStatus());
	}

	@PostMapping("/filter-data")
	public String search(@ModelAttribute("search") SearchCriteria searchCriteria, Model model) {
		List<CitizenPlan> citizenPlans = citizenPlanService.getCitizens(searchCriteria);
		model.addAttribute("citizenList", citizenPlans);
		getPlanNameAndPlanStatus(model);
		return "index";
	}
	
	@GetMapping("/excel")
	public void downloadExcel( HttpServletResponse response) {

		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=myReport.xls";
		
		response.addHeader(headerKey, headerValue);
		
		citizenPlanService.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void downloadPdf(HttpServletResponse response) {
		
		response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=myReport.pdf";
		
		response.addHeader(headerKey, headerValue);
		
		citizenPlanService.generatePdf(response);
	}
}
