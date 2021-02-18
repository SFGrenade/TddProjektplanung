package tdd.projektplanung;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Project {
	public static List<Project> allProjects = new LinkedList<Project>();
	
	protected List<EmployeeInProject> employees;
	protected String name;
	protected Date startDate;
	protected Date endDate;
	protected int hoursTotal;
	
	public Project(String name, Date startDate, Date endDate, int hoursTotal) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hoursTotal = hoursTotal;
		this.employees = new LinkedList<EmployeeInProject>();
		
		allProjects.add(this);
	}
	public Project() {
		this.name = "";
		this.startDate = new Date();
		this.endDate = new Date();
		this.hoursTotal = 0;
		this.employees = new LinkedList<EmployeeInProject>();
		
		allProjects.add(this);
	}
	
	protected void finalize() {
		allProjects.remove(this);
	}
	
	public List<Project> GetProjectsForEmployee(EmployeeInProject e) {
		List<Project> ret = new LinkedList<Project>();
		for (int i = 0; i < allProjects.size(); i++) {
			if (allProjects.get(i).getEmployees().contains(e)) {
				ret.add(allProjects.get(i));
			}
		}
		return ret;
	}
	
	public List<EmployeeInProject> GetFreeEmployees(Date start, Date end) {
		List<EmployeeInProject> ret = new LinkedList<EmployeeInProject>();
		for (int i = 0; i < EmployeeInProject.allEmployeesInProjects.size(); i++) {
			EmployeeInProject currE = EmployeeInProject.allEmployeesInProjects.get(i);
			List<Project> currEProjects = GetProjectsForEmployee(currE);
			if (currEProjects.size() == 0) {
				ret.add(currE);
				continue;
			}
			for (int j = 0; j < currEProjects.size(); j++) {
				Project currP = currEProjects.get(j);
				if (currP == this) {
					continue;
				}
				Date d1 = start;
				Date d2 = end;
				Date d3 = currP.getStartDate();
				Date d4 = currP.getEndDate();
				/* 
				 * this project
				 *      1==============2
				 * 3=======4        3=====4
				 * other projects
				 * 3======================4
				 * other projects
				 * 
				 */
				if (d1.before(d4) && d2.after(d4)) {
					// case 1
					continue;
				}
				else if (d1.before(d3) && d2.after(d3)) {
					// case 2
					continue;
				}
				else if (d1.after(d3) && d2.before(d4)) {
					// case 2
					continue;
				}
				ret.add(currE);
				break;
			}
		}
		
		return ret;
	}
	
	public void AddEmployee(EmployeeInProject t, boolean productOwner, boolean scrumMaster, boolean backendDeveloper, boolean frontendDeveloper, int hours) throws IllegalArgumentException {
		List<EmployeeInProject> free = GetFreeEmployees(this.startDate, this.endDate);
		if (!free.contains(t)) {
			throw new IllegalArgumentException("Employees can not be in multiple projects at the same time!");
		}
		
		t.productOwner = productOwner;
		t.scrumMaster = scrumMaster;
		t.backendDeveloper = backendDeveloper;
		t.frontendDeveloper = frontendDeveloper;
		t.hours = hours;
		this.employees.add(t);
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
	public List<EmployeeInProject> getEmployees() {
		return employees;
	}
}
