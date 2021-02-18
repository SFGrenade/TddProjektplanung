package tdd.projektplanung;

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
	protected String Country;
	protected String eMail;
	
	public Employee() {
		this.firstName = "";
		this.lastName = "";
		this.telephoneNumber = "";
		this.streetName = "";
		this.streetNumber = "";
		this.zipCode = "";
		this.Country = "";
		this.eMail = "";
		
		allEmployees.add(this);
	}
	
	protected void finalize() {
		allEmployees.remove(this);
	}
	
	public Employee findEmployee(String firstName, String lastName) {
		for (int i = 0; i < allEmployees.size(); i++) {
			if (allEmployees.get(i).getFirstName().equals(firstName) && allEmployees.get(i).getLastName().equals(lastName)) {
				return allEmployees.get(i);
			}
		}
		return null;
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
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
