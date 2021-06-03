package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class ProjectTest {
	private Employee e1;
	private Employee e2;
	private Employee e3;
	private Employee e4;
	private Employee e5;
	private Project p1;
	private Project p2;
	
	@BeforeEach
	public void testSetup() {
		e1 = new Employee("Jans", "Lastname");
		e2 = new Employee("Herbert", "Lastname");
		e3 = new Employee("Gunther", "Lastname");
		e4 = new Employee("Peter", "Lastname");
		e5 = new Employee("Bert", "Lastname");
		p1 = new Project("P1", new Date(2021, 02, 17), new Date(2021, 06, 04), 1300);
		p2 = new Project("P2", new Date(2021, 05, 03), new Date(2021, 07, 21), 3000);
	}
	
	@AfterEach
	public void testCleanup() {
		e1.remove();
		e2.remove();
		e3.remove();
		e4.remove();
		e5.remove();
		p1.remove();
		p2.remove();
	}
	
	@Test
	public void testCreateProject() {
		assertEquals("P1", p1.getName());
		assertEquals((new Date(2021, 02, 17)).toString(), p1.getStartDate().toString());
		assertEquals((new Date(2021, 06, 04)).toString(), p1.getEndDate().toString());
		assertEquals(1300, p1.getHoursTotal());
	}
	
	@Test
	public void testChangeProject() {
		p1.setEndDate(new Date(2021, 06, 05));
		p1.setHoursTotal(1111);
		
		assertEquals("P1", p1.getName());
		assertEquals((new Date(2021, 02, 17)).toString(), p1.getStartDate().toString());
		assertEquals((new Date(2021, 06, 05)).toString(), p1.getEndDate().toString());
		assertEquals(1111, p1.getHoursTotal());
	}
	
	@Test
	public void testAddEmployeeToProject() {
		p1.addEmployeeToProject(e1, false, false, false, false, 30);
		
		assertTrue(p1.getAssignedEmployees().containsKey(e1));
	}
	
	@Test
	public void testGetFreeEmployees() {
		assertEquals(e1, p1.GetFreeEmployees().get(0));
	}
	
	@Test
	public void testPlanProject() {
		p1.addEmployeeToProject(e1, false, true, false, false, 30);
		p1.addEmployeeToProject(e2, false, true, false, false, 30);
		p1.addEmployeeToProject(e3, false, true, false, false, 30);
		
		p2.addEmployeeToProject(e4, false, true, false, false, 30);
		p2.addEmployeeToProject(e5, false, true, false, false, 30);
		
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				p2.addEmployeeToProject(e3, false, true, false, false, 30);
			}
		});
		assertEquals("Employees can not be in multiple projects at the same time!", e.getMessage());
	}
	
	@Test
	public void testAllTrackedHours() {
		p1.addEmployeeToProject(e1, false, true, false, false, 30);
		p1.addEmployeeToProject(e2, false, true, false, false, 30);
		p1.addEmployeeToProject(e3, false, true, false, false, 30);
		p1.addEmployeeToProject(e4, false, true, false, false, 30);
		p1.addEmployeeToProject(e5, false, true, false, false, 30);

		p1.addTrackedHours(e1, 1);
		p1.addTrackedHours(e2, 3);
		p1.addTrackedHours(e3, 3);
		p1.addTrackedHours(e4, 3);
		p1.addTrackedHours(e5, 7);
		
		Map<Employee, Integer> m = p1.getEmployeeTrackedHours();
		assertEquals(1, m.get(e1).intValue());
		assertEquals(3, m.get(e2).intValue());
		assertEquals(3, m.get(e3).intValue());
		assertEquals(3, m.get(e4).intValue());
		assertEquals(7, m.get(e5).intValue());
	}
}
