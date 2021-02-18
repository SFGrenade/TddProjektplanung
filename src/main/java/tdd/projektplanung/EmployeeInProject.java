package tdd.projektplanung;

import java.util.LinkedList;
import java.util.List;

public class EmployeeInProject extends Employee {
	public static List<EmployeeInProject> allEmployeesInProjects = new LinkedList<EmployeeInProject>();
	
	protected boolean productOwner;
	protected boolean scrumMaster;
	protected boolean backendDeveloper;
	protected boolean frontendDeveloper;
	protected int hours;
	
	public EmployeeInProject(boolean productOwner, boolean scrumMaster, boolean backendDeveloper, boolean frontendDeveloper, int hours) {
		this.productOwner = productOwner;
		this.scrumMaster = scrumMaster;
		this.backendDeveloper = backendDeveloper;
		this.frontendDeveloper = frontendDeveloper;
		this.hours = hours;
		
		allEmployeesInProjects.add(this);
	}
	public EmployeeInProject() {
		allEmployeesInProjects.add(this);
	}
	
	protected void finalize() {
		allEmployeesInProjects.remove(this);
	}

	public boolean isProductOwner() {
		return productOwner;
	}
	public void setProductOwner(boolean productOwner) {
		this.productOwner = productOwner;
	}
	public boolean isScrumMaster() {
		return scrumMaster;
	}
	public void setScrumMaster(boolean scrumMaster) {
		this.scrumMaster = scrumMaster;
	}
	public boolean isBackendDeveloper() {
		return backendDeveloper;
	}
	public void setBackendDeveloper(boolean backendDeveloper) {
		this.backendDeveloper = backendDeveloper;
	}
	public boolean isFrontendDeveloper() {
		return frontendDeveloper;
	}
	public void setFrontendDeveloper(boolean frontendDeveloper) {
		this.frontendDeveloper = frontendDeveloper;
	}
}
