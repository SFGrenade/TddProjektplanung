package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

public class EmployeeTest {
	private Employee e1;
	private Employee e2;
	private Project p1;
	
	@BeforeEach
	public void testSetup() {
		e1 = new Employee("Firstname", "Lastname");
		e2 = new Employee("2nd First", "2nd Last");
		p1 = new Project("P1", new Date(2021, 02, 17), new Date(2021, 06, 04), 1300);
	}
	
	@AfterEach
	public void testCleanup() {
		e1.remove();
		e2.remove();
		p1.remove();
	}
	
	@Test
	public void testGetterSetter() {
		e1.setFirstName("FirstName");
		e1.setLastName("LastName");
		e1.setTelephoneNumber("TelephoneNumber");
		e1.setStreetName("StreetName");
		e1.setStreetNumber("StreetNumber");
		e1.setZipCode("ZipCode");
		e1.setCountry("Country");
		e1.setEMail("EMail");
		
		assertEquals("FirstName", e1.getFirstName());
		assertEquals("LastName", e1.getLastName());
		assertEquals("TelephoneNumber", e1.getTelephoneNumber());
		assertEquals("StreetName", e1.getStreetName());
		assertEquals("StreetNumber", e1.getStreetNumber());
		assertEquals("ZipCode", e1.getZipCode());
		assertEquals("Country", e1.getCountry());
		assertEquals("EMail", e1.getEMail());
	}
	
	@Test
	public void testFindEmployee() {
		assertEquals(e1, Employee.findEmployee("Firstname", "Lastname"));
		assertEquals(e2, Employee.findEmployee("2nd First", "2nd Last"));
	}
	
	@Test
	public void testTimespanProjects() {
		p1.addEmployeeToProject(e1, true, true, true, true, 1300);
		
		assertEquals(p1, e1.getProjectsInTimespan(new Date(2021, 03, 11), new Date(2021, 03, 12)).get(0));
	}
}
