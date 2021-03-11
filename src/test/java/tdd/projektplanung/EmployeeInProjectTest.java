package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

public class EmployeeInProjectTest {
	@Test
	public void testEmployeeInProject() {
		Employee e1 = new Employee("Firstname", "Lastname");
		Project p1 = new Project("P1", new Date(2021, 02, 17), new Date(2021, 06, 04), 1337);
		p1.addEmployeeToProject(e1, true, true, true, true, 1300);

		assertTrue(p1.getAssignedEmployees().get(e1).isProductOwner());
		assertTrue(p1.getAssignedEmployees().get(e1).isScrumMaster());
		assertTrue(p1.getAssignedEmployees().get(e1).isBackendDeveloper());
		assertTrue(p1.getAssignedEmployees().get(e1).isFrontendDeveloper());
		assertEquals(1300, p1.getAssignedEmployees().get(e1).getWantedHours());
		assertEquals(0, p1.getAssignedEmployees().get(e1).getTrackedHours());
		p1.addTrackedHours(e1, 37);
		assertEquals(37, p1.getAssignedEmployees().get(e1).getTrackedHours());

		e1.remove();
		p1.remove();
	}
}
