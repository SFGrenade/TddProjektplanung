package tdd.projektplanung;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Project {
	public static List<Project> allProjects = new LinkedList<Project>();
	
	protected String name;
	protected Date startDate;
	protected Date endDate;
	protected int hoursTotal;
	protected Map<Employee, EmployeeInProject> assignedEmployees;
	
	public Project(String name, Date startDate, Date endDate, int hoursTotal) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hoursTotal = hoursTotal;
		this.assignedEmployees = new HashMap<Employee, EmployeeInProject>();
		
		allProjects.add(this);
	}
	public Project() {
		this.name = "";
		this.startDate = new Date();
		this.endDate = new Date();
		this.hoursTotal = 0;
		this.assignedEmployees = new HashMap<Employee, EmployeeInProject>();
		
		allProjects.add(this);
	}
	
	protected void finalize() {
		this.remove();
	}
	
	public void remove() {
		allProjects.remove(this);
	}
	
	public void addEmployeeToProject(Employee employee, boolean productOwner, boolean scrumMaster, boolean backendDeveloper, boolean frontendDeveloper, int wantedHours) throws IllegalArgumentException {
		if (this.GetFreeEmployees().contains(employee))
			this.assignedEmployees.put(employee, new EmployeeInProject(productOwner, scrumMaster, backendDeveloper, frontendDeveloper, wantedHours));
		else
			throw new IllegalArgumentException("Employees can not be in multiple projects at the same time!");
	}
	
	public List<Employee> GetFreeEmployees() {
		List<Employee> ret = new LinkedList<Employee>();
		
		for (int i = 0; i < Employee.allEmployees.size(); i++) {
			Employee currEmployee = Employee.allEmployees.get(i);
			ret.add(currEmployee);
			for (int j = 0; j < Project.allProjects.size(); j++) {
				Project currProject = Project.allProjects.get(j);
				if (currProject == this) continue;
				// check if projects overlap
				if (this.startDate.before(currProject.endDate) && this.endDate.after(currProject.endDate)) {
					// case 1
					if (currProject.assignedEmployees.containsKey(currEmployee)) {
						ret.remove(currEmployee);
					}
				}
				else if (this.startDate.before(currProject.startDate) && this.endDate.after(currProject.startDate)) {
					// case 2
					if (currProject.assignedEmployees.containsKey(currEmployee)) {
						ret.remove(currEmployee);
					}
				}
				else if (this.startDate.after(currProject.startDate) && this.endDate.before(currProject.endDate)) {
					// case 3
					if (currProject.assignedEmployees.containsKey(currEmployee)) {
						ret.remove(currEmployee);
					}
				}
			}
		}
		
		return ret;
	}
	
	public void addTrackedHours(Employee employee, int hours) {
		this.assignedEmployees.get(employee).addTrackedHours(hours);
	}
	
	public Map<Employee, Integer> getEmployeeTrackedHours() {
		Map<Employee, Integer> ret = new HashMap<Employee, Integer>();
		
		for (Employee e : this.assignedEmployees.keySet()) {
			ret.put(e, this.assignedEmployees.get(e).trackedHours);
		}
		
		return ret;
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
	public Map<Employee, EmployeeInProject> getAssignedEmployees() {
		// Read only 😎
		return new HashMap<Employee, EmployeeInProject>(assignedEmployees);
	}
}
