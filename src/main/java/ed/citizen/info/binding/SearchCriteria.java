package ed.citizen.info.binding;

import java.time.LocalDate;

public class SearchCriteria {
	
private String planName;
private String planStatus;
private String gender;
private LocalDate startDate;
private LocalDate endDate;

public SearchCriteria() {}

public SearchCriteria(String planName, String planStatus, String gender, LocalDate startDate, LocalDate endDate) {
	super();
	this.planName = planName;
	this.planStatus = planStatus;
	this.gender = gender;
	this.startDate = startDate;
	this.endDate = endDate;
}
public String getPlanName() {
	return planName;
}
public void setPlanName(String planName) {
	this.planName = planName;
}
public String getPlanStatus() {
	return planStatus;
}
public void setPlanStatus(String planStatus) {
	this.planStatus = planStatus;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public LocalDate getStartDate() {
	return startDate;
}
public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
}
public LocalDate getEndDate() {
	return endDate;
}
public void setEndDate(LocalDate endDate) {
	this.endDate = endDate;
}
@Override
public String toString() {
	return "SearchCriteria [planName=" + planName + ", planStatus=" + planStatus + ", gender=" + gender + ", startDate="
			+ startDate + ", endDate=" + endDate + "]";
}



}
