package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

public class EmployeeInProjectTest {
	private Employee e1;
	private Project p1;
	
	@BeforeEach
	public void testSetup() {
		e1 = new Employee("Firstname", "Lastname");
		p1 = new Project("P1", new Date(2021, 02, 17), new Date(2021, 06, 04), 1300);
		p1.addEmployeeToProject(e1, true, true, true, true, 1300);
	}
	
	@AfterEach
	public void testCleanup() {
		e1.remove();
		p1.remove();
	}
	
	@Test
	public void testEmployeeInProject() {
		assertTrue(p1.getAssignedEmployees().get(e1).isProductOwner());
		assertTrue(p1.getAssignedEmployees().get(e1).isScrumMaster());
		assertTrue(p1.getAssignedEmployees().get(e1).isBackendDeveloper());
		assertTrue(p1.getAssignedEmployees().get(e1).isFrontendDeveloper());
		assertEquals(1300, p1.getAssignedEmployees().get(e1).getWantedHours());
		assertEquals(0, p1.getAssignedEmployees().get(e1).getTrackedHours());
	}

	@Test
	public void testAddTrackedHours() {
		p1.addTrackedHours(e1, 37);
		assertEquals(37, p1.getAssignedEmployees().get(e1).getTrackedHours());
	}
}
