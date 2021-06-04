package tdd.projektplanung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Run {
	public static String[] makeCliOfString(String line) {
		List<String> ret = new LinkedList<String>();
		
		boolean inQuotes = false;
		String currString = "";
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (!inQuotes && (c == ' ')) {
				ret.add(currString);
				currString = "";
			}
			else if (c == '"') {
				inQuotes = !inQuotes;
			}
			else {
				currString = String.format("%s%c", currString, c);
			}
		}
		ret.add(currString);
		
		String[] finalRet = new String[ret.size()];
		for (int i = 0; i < finalRet.length; i++) {
			finalRet[i] = ret.get(i);
		}
		
		return finalRet;
	}
	
	public static void computeLine(String[] args) {
		if (args.length > 0 && args[0].compareTo("e") == 0) {
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
				System.out.println(
						"e \"firstname\" \"lastname\" \"email\" \"Telephone Number\" \"Street Name\" \"Street Number\" \"ZipCode\" \"Country Name\"");
			}
		} else if (args.length > 0 && args[0].compareTo("p") == 0) {
			// Adding Project
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String name = args[1];
				Date startDate = simpleDateFormat.parse(args[2]);
				Date endDate = simpleDateFormat.parse(args[3]);
				int hoursTotal = Integer.parseInt(args[4]);
				Project p = new Project(name, startDate, endDate, hoursTotal);
			} catch (Exception ex) {
				System.out.println(String.format("Error parsing Project data! '%s'", String.join(" ", args)));
				System.out.println("Example:");
				System.out.println("p \"Project Name\" \"2000-01-01\" \"2000-01-02\" \"1300\"");
			}
		} else if (args.length > 0 && args[0].compareTo("ep") == 0) {
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
								toAdd.addEmployeeToProject(Employee.findEmployee(employeeFirstName, employeeLastName),
										productOwner, scrumMaster, backendDev, frontendDev, wantedHours);
								break;
							}
						}
					} catch (Exception e) {
						System.out.println("Error parsing data to add Employee to Project!");
						System.out.println("Example:");
						System.out.println(
								"ep aep \"Project Name\" \"Employee First Name\" \"Employee Last Name\" (product owner [false]) (scrum master [false]) (backend dev [false]) (frontend dev [false]) \"1300\"");
					}
				} else if (args[1].compareTo("ath") == 0) {
					try {
						String projectName = args[2];
						String employeeFirstName = args[3];
						String employeeLastName = args[4];
						int workedHours = Integer.parseInt(args[5]);
						for (int i = 0; i < Project.allProjects.size(); i++) {
							if (Project.allProjects.get(i).getName().compareTo(projectName) == 0) {
								Project toAdd = Project.allProjects.get(i);
								toAdd.addTrackedHours(Employee.findEmployee(employeeFirstName, employeeLastName),
										workedHours);
								break;
							}
						}
					} catch (Exception e) {
						System.out.println("Error parsing data to add tracked hours to Employee in Project!");
						System.out.println("Example:");
						System.out.println(
								"ep ath \"Project Name\" \"Employee First Name\" \"Employee Last Name\" \"7\"");
					}
				} else if (args[1].compareTo("gfe") == 0) {
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
		} else {
			System.out.println("Get Started with Employees and Projects:");
			System.out.println("e|p|ep");
		}
	}

	public static void loadEverything() {
		try {
			BufferedReader myReader = new BufferedReader(new FileReader("database.txt"));
			myReader.lines().forEach(line -> {
				if (line.trim().length() > 0) {
					computeLine(makeCliOfString(line));
				}
		    });
			myReader.close();
		} catch (IOException e) {
			System.out.println("An error occurred while loading the database.");
			e.printStackTrace();
		}
	}

	public static void saveEverything() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> lines = new LinkedList<String>();
		for (Employee e : Employee.allEmployees) {
			lines.add(String.format("e \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"", e.getFirstName(),
					e.getLastName(), e.getEMail(), e.getTelephoneNumber(), e.getStreetName(), e.getStreetNumber(),
					e.getZipCode(), e.getCountry()));
		}
		for (Project p : Project.allProjects) {
			lines.add(String.format("p \"%s\" \"%s\" \"%s\" \"%s\"", p.getName(),
					simpleDateFormat.format(p.getStartDate()), simpleDateFormat.format(p.getEndDate()),
					p.getHoursTotal()));
			for (Entry<Employee, EmployeeInProject> pair : p.getAssignedEmployees().entrySet()) {
				lines.add(String.format("ep aep \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"", p.getName(),
						pair.getKey().getFirstName(), pair.getKey().getLastName(), pair.getValue().isProductOwner(),
						pair.getValue().isScrumMaster(), pair.getValue().isBackendDeveloper(),
						pair.getValue().isFrontendDeveloper(), pair.getValue().getWantedHours()));
				lines.add(String.format("ep ath \"%s\" \"%s\" \"%s\" \"%s\"", p.getName(), pair.getKey().getFirstName(),
						pair.getKey().getLastName(), pair.getValue().getTrackedHours()));
			}
		}
		try {
			FileWriter myWriter = new FileWriter("database.txt");
			for (String line : lines) {
				myWriter.write(line);
				myWriter.write("\n");
			}
			myWriter.close();
			System.out.println("Databse saved.");
		} catch (IOException e) {
			System.out.println("An error occurred while saving the database.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		loadEverything();

		if (args.length > 0)
			computeLine(args);

		saveEverything();

		for (Employee e : Employee.allEmployees) {
			System.out.println(String.format("Name: %s %s", e.getFirstName(), e.getLastName()));
			System.out.println(String.format("Email: %s", e.getEMail()));
			System.out.println(String.format("Tel: %s", e.getTelephoneNumber()));
			System.out.println(String.format("Adress: %s %s, %s %s", e.getStreetName(), e.getStreetNumber(),
					e.getZipCode(), e.getCountry()));
			System.out.println();
		}
		for (Project p : Project.allProjects) {
			System.out.println("Project:");
			System.out.println(String.format("Name: %s", p.getName()));
			System.out.println(
					String.format("Time Window: %s - %s", p.getStartDate().toString(), p.getEndDate().toString()));
			System.out.println(String.format("Total Hours: %d", p.getHoursTotal()));
			System.out.println();
		}
	}
}
