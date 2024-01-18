package ed.citizen.info.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ed.citizen.info.dao.CitizenPlanRepo;
import ed.citizen.info.entity.CitizenPlan;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	CitizenPlanRepo citizenPlanRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		citizenPlanRepo.deleteAll();
		
		CitizenPlan c1 = new CitizenPlan("Saubhagya", "patrasaubhagya146@gmail.com", "7538962146", "M",1234,"Cash","Approved",LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan c2 = new CitizenPlan("Ramesh", "ramesh@gmail.com", "8989890987", "M",3241,"Food","Denied",null, null);
		CitizenPlan c3 = new CitizenPlan("Padmalaya Ratha", "padma@gmail.com", "9876789876", "f",1111,"Food","Approved",LocalDate.now(), LocalDate.now().plusMonths(9));
		CitizenPlan c4 = new CitizenPlan("Saubhagya Patra", "saubhagyapatra2016@gmail.com", "7538962146", "M",9999,"Cash","Pending",null, null);
		CitizenPlan c5 = new CitizenPlan("Gautam", "gautam@gmail.com", "98989892929", "M",9990,"Cash","Approved",LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan c6 = new CitizenPlan("Virat", "virat@gmail.com", "9876678987", "M",7878,"Cash","Active",LocalDate.now(), LocalDate.now().plusMonths(6));
		
		List<CitizenPlan> arrayList =  Arrays.asList(c1, c2, c3, c4, c5, c6);
		citizenPlanRepo.saveAll(arrayList);
	}

}
