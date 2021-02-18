package tdd.projektplanung;

import java.util.Date;
import java.util.List;

public class Project {
	//protected List<EmployeeInProject> employees;
	protected String name;
	protected Date startDate;
	protected Date endDate;
	protected int hoursTotal;
	
	public Project(String name, Date startDate, Date endDate, int hoursTotal) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hoursTotal = hoursTotal;
	}
	public Project() {
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getHoursTotal() {
		return hoursTotal;
	}
	public void setHoursTotal(int hoursTotal) {
		this.hoursTotal = hoursTotal;
	}
	public String getName() {
		return name;
	}
	public Date getStartDate() {
		return startDate;
	}
}
