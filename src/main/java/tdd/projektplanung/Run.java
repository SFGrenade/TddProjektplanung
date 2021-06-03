package tdd.projektplanung;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Run {
	public static void main(String[] args) {
		if (args[0].compareTo("e") == 0) {
			// Adding Employee
			Employee e = new Employee();
			try {
				e.setFirstName(args[1]);
				e.setLastName(args[2]);
				e.setEMail(args[3]);
				e.setTelephoneNumber(args[4]);
				e.setStreetName(args[5]);
				e.setStreetNumber(args[6]);
				e.setZipCode(args[7]);
				e.setCountry(args[8]);
			} catch (ArrayIndexOutOfBoundsException ex) {
				e.remove();
				System.out.println("Error parsing Employee data!");
				System.out.println("Example:");
				System.out.println("e \"firstname\" \"lastname\" \"email\" \"Telephone Number\" \"Street Name\" \"Street Number\" \"ZipCode\" \"Country Name\"");
			}
		}
		else if (args[0].compareTo("p") == 0) {
			// Adding Project
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String name = args[1];
				Date startDate = simpleDateFormat.parse(args[2]);
				Date endDate = simpleDateFormat.parse(args[3]);
				int hoursTotal = Integer.parseInt(args[4]);
				Project p = new Project(name, startDate, endDate, hoursTotal);
			} catch (Exception ex) {
				System.out.println("Error parsing Project data!");
				System.out.println("Example:");
				System.out.println("p \"Project Name\" \"2000-01-01\" \"2000-01-02\" \"1300\"");
			}
		}
		else if (args[0].compareTo("ep") == 0) {
			// Doing work with employees in projects
			try {
				if (args[1].compareTo("aep") == 0) {
					try {
						String projectName = args[2];
						String employeeFirstName = args[3];
						String employeeLastName = args[4];
						boolean productOwner = args[5].toLowerCase().compareTo("false") == 0;
						boolean scrumMaster = args[6].toLowerCase().compareTo("false") == 0;
						boolean backendDev = args[7].toLowerCase().compareTo("false") == 0;
						boolean frontendDev = args[8].toLowerCase().compareTo("false") == 0;
						int wantedHours = Integer.parseInt(args[9]);
						for (int i = 0; i < Project.allProjects.size(); i++) {
							if (Project.allProjects.get(i).getName().compareTo(projectName) == 0) {
								Project toAdd = Project.allProjects.get(i);
								toAdd.addEmployeeToProject(Employee.findEmployee(employeeFirstName, employeeLastName), productOwner, scrumMaster, backendDev, frontendDev, wantedHours);
								break;
							}
						}
					} catch (Exception e) {
						System.out.println("Error parsing data to add Employee to Project!");
						System.out.println("Example:");
						System.out.println("ep aep \"Project Name\" \"Employee First Name\" \"Employee Last Name\" (product owner [false]) (scrum master [false]) (backend dev [false]) (frontend dev [false]) \"1300\"");
					}
				}
				else if (args[1].compareTo("ath") == 0) {
					try {
						String projectName = args[2];
						String employeeFirstName = args[3];
						String employeeLastName = args[4];
						int workedHours = Integer.parseInt(args[5]);
						for (int i = 0; i < Project.allProjects.size(); i++) {
							if (Project.allProjects.get(i).getName().compareTo(projectName) == 0) {
								Project toAdd = Project.allProjects.get(i);
								toAdd.addTrackedHours(Employee.findEmployee(employeeFirstName, employeeLastName), workedHours);
								break;
							}
						}
					} catch (Exception e) {
						System.out.println("Error parsing data to add tracked hours to Employee in Project!");
						System.out.println("Example:");
						System.out.println("ep ath \"Project Name\" \"Employee First Name\" \"Employee Last Name\" \"7\"");
					}
				}
				else if (args[1].compareTo("gfe") == 0) {
					try {
						String projectName = args[2];
						for (int i = 0; i < Project.allProjects.size(); i++) {
							if (Project.allProjects.get(i).getName().compareTo(projectName) == 0) {
								Project toAdd = Project.allProjects.get(i);
								System.out.println("Free Employees:");
								for (Employee e : toAdd.GetFreeEmployees()) {
									System.out.println(String.format("Name: %s %s", e.getFirstName(), e.getLastName()));
								}
								break;
							}
						}
					} catch (Exception e) {
						System.out.println("Error parsing data to list free employees for project!");
						System.out.println("Example:");
						System.out.println("ep gfe \"Project Name\"");
					}
				}
			} catch (Exception ex) {
				System.out.println("Error parsing EmployeeProject data!");
				System.out.println("Example:");
				System.out.println("ep aep");
			}
		}
		else {
			System.out.println("Get Started with Employees and Projects:");
			System.out.println("e|p|ep");
		}
		
		// ToDo SAVE STANDINGS
		
		for (Employee e : Employee.allEmployees) {
			System.out.println(String.format("Name: %s %s", e.getFirstName(), e.getLastName()));
			System.out.println(String.format("Email: %s", e.getEMail()));
			System.out.println(String.format("Tel: %s", e.getTelephoneNumber()));
			System.out.println(String.format("Adress: %s %s, %s %s", e.getStreetName(), e.getStreetNumber(), e.getZipCode(), e.getCountry()));
			System.out.println();
		}
		for (Project p : Project.allProjects) {
			System.out.println("Project:");
			System.out.println(String.format("Name: %s", p.getName()));
			System.out.println(String.format("Time Window: %s - %s", p.getStartDate().toString(), p.getEndDate().toString()));
			System.out.println(String.format("Total Hours: %d", p.getHoursTotal()));
			System.out.println();
		}
	}
}
