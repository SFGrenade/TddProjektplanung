package tdd.projektplanung;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	public static List<Employee> allEmployees = new LinkedList<Employee>();
	
	protected String firstName;
	protected String lastName;
	protected String telephoneNumber;
	protected String streetName;
	protected String streetNumber;
	protected String zipCode;
	protected String country;
	protected String eMail;

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephoneNumber = "";
		this.streetName = "";
		this.streetNumber = "";
		this.zipCode = "";
		this.country = "";
		this.eMail = "";
		
		allEmployees.add(this);
	}
	
	public Employee() {
		this.firstName = "";
		this.lastName = "";
		this.telephoneNumber = "";
		this.streetName = "";
		this.streetNumber = "";
		this.zipCode = "";
		this.country = "";
		this.eMail = "";
		
		allEmployees.add(this);
	}
	
	protected void finalize() {
		this.remove();
	}
	
	public void remove() {
		allEmployees.remove(this);
	}
	
	public static Employee findEmployee(String firstName, String lastName) {
		for (int i = 0; i < allEmployees.size(); i++) {
			if (allEmployees.get(i).getFirstName().equals(firstName) && allEmployees.get(i).getLastName().equals(lastName)) {
				return allEmployees.get(i);
			}
		}
		return null;
	}
	
	public List<Project> getProjectsInTimespan(Date startDate, Date endDate) {
		List<Project> ret = new LinkedList<Project>();
		for (int i = 0; i < Project.allProjects.size(); i++) {
			Project currProject = Project.allProjects.get(i);
			// check if projects overlap
			if (startDate.before(currProject.endDate) && endDate.after(currProject.endDate)) {
				// case 1
				if (!ret.contains(currProject) && currProject.getAssignedEmployees().containsKey(this))
					ret.add(currProject);
			}
			else if (startDate.before(currProject.startDate) && endDate.after(currProject.startDate)) {
				// case 2
				if (!ret.contains(currProject) && currProject.getAssignedEmployees().containsKey(this))
					ret.add(currProject);
			}
			else if (startDate.after(currProject.startDate) && endDate.before(currProject.endDate)) {
				// case 3
				if (!ret.contains(currProject) && currProject.getAssignedEmployees().containsKey(this))
					ret.add(currProject);
			}
		}
		return ret;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
}
