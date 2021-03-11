package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

public class EmployeeTest {
	@Test
	public void testGetterSetter() {
		Employee e = new Employee();
		e.setFirstName("FirstName");
		e.setLastName("LastName");
		e.setTelephoneNumber("TelephoneNumber");
		e.setStreetName("StreetName");
		e.setStreetNumber("StreetNumber");
		e.setZipCode("ZipCode");
		e.setCountry("Country");
		e.setEMail("EMail");
		
		assertEquals("FirstName", e.getFirstName());
		assertEquals("LastName", e.getLastName());
		assertEquals("TelephoneNumber", e.getTelephoneNumber());
		assertEquals("StreetName", e.getStreetName());
		assertEquals("StreetNumber", e.getStreetNumber());
		assertEquals("ZipCode", e.getZipCode());
		assertEquals("Country", e.getCountry());
		assertEquals("EMail", e.getEMail());
		
		e.remove();
	}
	
	@Test
	public void testFindEmployee() {
		Employee e1 = new Employee("Firstname", "Lastname");
		Employee e2 = new Employee("2nd First", "2nd Last");

		assertEquals(e1, Employee.findEmployee("Firstname", "Lastname"));
		assertEquals(e2, Employee.findEmployee("2nd First", "2nd Last"));
		
		e1.remove();
		e2.remove();
	}
	
	@Test
	public void testTimespanProjects() {
		Employee e1 = new Employee("Firstname", "Lastname");
		Project p1 = new Project("P1", new Date(2021, 02, 17), new Date(2021, 06, 04), 1337);
		p1.addEmployeeToProject(e1, true, true, true, true, 1300);
		
		assertEquals(p1, e1.getProjectsInTimespan(new Date(2021, 03, 11), new Date(2021, 03, 12)).get(0));

		e1.remove();
		p1.remove();
	}
}
