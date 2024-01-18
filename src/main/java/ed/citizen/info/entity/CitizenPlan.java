package ed.citizen.info.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CitizenPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer citizenId;
	private String name;
	private String email;
	private String phNo;
	private String gender;
	private Integer ssn;
	private String planName;
	private String planStatus;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate endDate;

	public CitizenPlan() {
	}

	public CitizenPlan(String name, String email, String phNo, String gender, Integer ssn, String planName,
			String planStatus, LocalDate startDate, LocalDate endDate) {
		super();
		this.name = name;
		this.email = email;
		this.phNo = phNo;
		this.gender = gender;
		this.ssn = ssn;
		this.planName = planName;
		this.planStatus = planStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	

	public Integer getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(Integer citizenId) {
		this.citizenId = citizenId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
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
		return "CitizenPlan [citizenId=" + citizenId + ", name=" + name + ", email=" + email + ", phNo=" + phNo
				+ ", gender=" + gender + ", ssn=" + ssn + ", planName=" + planName + ", planStatus=" + planStatus
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	/*
	 * public LocalDate getCreatedDate() { return createdDate; }
	 * 
	 * public void setCreatedDate(LocalDate createdDate) { this.createdDate =
	 * createdDate; }
	 * 
	 * public LocalDate getUpdatedDate() { return updatedDate; }
	 * 
	 * public void setUpdatedDate(LocalDate updatedDate) { this.updatedDate =
	 * updatedDate; }
	 */
	
	

}
