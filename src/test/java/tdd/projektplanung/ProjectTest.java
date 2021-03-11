package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class ProjectTest {
	@Test
	public void testCreateProject() {
		Project pTest = new Project("test", new Date(2021, 02, 17), new Date(2021, 06, 04), 1337);
		
		assertEquals("test", pTest.getName());
		assertEquals((new Date(2021, 02, 17)).toString(), pTest.getStartDate().toString());
		assertEquals((new Date(2021, 06, 04)).toString(), pTest.getEndDate().toString());
		assertEquals(1337, pTest.getHoursTotal());
		
		pTest.remove();
	}
	
	@Test
	public void testChangeProject() {
		Project pTest = new Project("test", new Date(2021, 02, 17), new Date(2021, 06, 04), 1337);
		pTest.setEndDate(new Date(2021, 06, 05));
		pTest.setHoursTotal(1111);
		
		assertEquals("test", pTest.getName());
		assertEquals((new Date(2021, 02, 17)).toString(), pTest.getStartDate().toString());
		assertEquals((new Date(2021, 06, 05)).toString(), pTest.getEndDate().toString());
		assertEquals(1111, pTest.getHoursTotal());
		
		pTest.remove();
	}
	
	@Test
	public void testAddEmployeeToProject() {
		Employee e1 = new Employee("Firstname", "Lastname");
		Project pTest = new Project("test", new Date(2021, 02, 17), new Date(2021, 06, 04), 1337);
		pTest.addEmployeeToProject(e1, false, false, false, false, 30);
		
		assertTrue(pTest.getAssignedEmployees().containsKey(e1));

		e1.remove();
		pTest.remove();
	}
	
	@Test
	public void testGetFreeEmployees() {
		Employee e1 = new Employee("Firstname", "Lastname");
		Project pTest = new Project("test", new Date(2021, 02, 17), new Date(2021, 06, 04), 1337);
		
		assertEquals(e1, pTest.GetFreeEmployees().get(0));

		e1.remove();
		pTest.remove();
	}
	
	@Test
	public void testPlanProject() {
		Project p1 = new Project("p1", new Date(1000), new Date(2000), 3000);
		Project p2 = new Project("p2", new Date(1500), new Date(2500), 3000);
		Employee t1 = new Employee();
		t1.setFirstName("Jans");
		Employee t2 = new Employee();
		t2.setFirstName("Herbert");
		Employee t3 = new Employee();
		t3.setFirstName("Gunther");
		Employee t4 = new Employee();
		t4.setFirstName("Peter");
		Employee t5 = new Employee();
		t5.setFirstName("Bert");
		
		p1.addEmployeeToProject(t1, false, true, false, false, 30);
		p1.addEmployeeToProject(t2, false, true, false, false, 30);
		p1.addEmployeeToProject(t3, false, true, false, false, 30);
		
		p2.addEmployeeToProject(t4, false, true, false, false, 30);
		p2.addEmployeeToProject(t5, false, true, false, false, 30);
		
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				p2.addEmployeeToProject(t3, false, true, false, false, 30);
			}
		});
		assertEquals("Employees can not be in multiple projects at the same time!", e.getMessage());
		
		p1.remove();
		p2.remove();
		t1.remove();
		t2.remove();
		t3.remove();
		t4.remove();
		t5.remove();
	}
	
	@Test
	public void testAllTrackedHours() {
		Project p1 = new Project("p1", new Date(2021, 02, 17), new Date(2021, 06, 04), 3000);
		Employee t1 = new Employee();
		t1.setFirstName("Jans");
		Employee t2 = new Employee();
		t2.setFirstName("Herbert");
		Employee t3 = new Employee();
		t3.setFirstName("Gunther");
		Employee t4 = new Employee();
		t4.setFirstName("Peter");
		Employee t5 = new Employee();
		t5.setFirstName("Bert");
		
		p1.addEmployeeToProject(t1, false, true, false, false, 30);
		p1.addEmployeeToProject(t2, false, true, false, false, 30);
		p1.addEmployeeToProject(t3, false, true, false, false, 30);
		p1.addEmployeeToProject(t4, false, true, false, false, 30);
		p1.addEmployeeToProject(t5, false, true, false, false, 30);

		p1.addTrackedHours(t1, 1);
		p1.addTrackedHours(t2, 3);
		p1.addTrackedHours(t3, 3);
		p1.addTrackedHours(t4, 3);
		p1.addTrackedHours(t5, 7);
		
		Map<Employee, Integer> m = p1.getEmployeeTrackedHours();
		assertEquals(1, m.get(t1).intValue());
		assertEquals(3, m.get(t2).intValue());
		assertEquals(3, m.get(t3).intValue());
		assertEquals(3, m.get(t4).intValue());
		assertEquals(7, m.get(t5).intValue());

		p1.remove();
		t1.remove();
		t2.remove();
		t3.remove();
		t4.remove();
		t5.remove();
	}
}
