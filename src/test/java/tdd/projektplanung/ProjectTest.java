package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class ProjectTest {
	private Project p;
	
	@BeforeEach
	public void setup() {
		p = new Project("test", new Date(), new Date(), 1337);
	}
	
	@Test
	public void testCreateProject() {
		Project pTest = new Project("test", new Date(), new Date(), 1337);
		
		assertEquals("test", pTest.getName());
		assertEquals((new Date()).toString(), pTest.getStartDate().toString());
		assertEquals((new Date()).toString(), pTest.getEndDate().toString());
		assertEquals(1337, pTest.getHoursTotal());
	}
	
	@Test
	public void testChangeProject() {
		Project pTest = new Project("test", new Date(), new Date(), 1337);
		pTest.setEndDate(new Date());
		pTest.setHoursTotal(1111);
		
		assertEquals("test", pTest.getName());
		assertEquals((new Date()).toString(), pTest.getStartDate().toString());
		assertEquals((new Date()).toString(), pTest.getEndDate().toString());
		assertEquals(1111, pTest.getHoursTotal());
	}
	
	@Test
	public void testGetEmployeeInProject() {
		EmployeeInProject t1 = new EmployeeInProject();
		p.AddEmployee(t1, false, false, false, false, 30);
		
		assertSame(t1, p.getEmployees().get(0));
	}
	
	@Test
	public void testGetEmployeeRoles() {
		EmployeeInProject t1 = new EmployeeInProject();
		p.AddEmployee(t1, false, true, false, false, 30);
		
		assertFalse(t1.isProductOwner());
		assertTrue(t1.isScrumMaster());
		assertFalse(t1.isBackendDeveloper());
		assertFalse(t1.isFrontendDeveloper());
	}
	
	@Test
	public void testChangeEmployeeContactDetails() {
		EmployeeInProject t1 = new EmployeeInProject();
		// Change from "" to "Jans"
		t1.setFirstName("Jans");
		
		assertEquals("Jans", t1.getFirstName());
	}
	
	@Test
	public void testPlanProject() {
		Project p1 = new Project("p1", new Date(1000), new Date(2000), 3000);
		Project p2 = new Project("p2", new Date(1500), new Date(2500), 3000);
		EmployeeInProject t1 = new EmployeeInProject();
		t1.setFirstName("Jans");
		EmployeeInProject t2 = new EmployeeInProject();
		t1.setFirstName("Herbert");
		EmployeeInProject t3 = new EmployeeInProject();
		t1.setFirstName("Gunther");
		EmployeeInProject t4 = new EmployeeInProject();
		t1.setFirstName("Peter");
		EmployeeInProject t5 = new EmployeeInProject();
		t1.setFirstName("Bert");
		p1.AddEmployee(t1, false, true, false, false, 30);
		p1.AddEmployee(t2, false, true, false, false, 30);
		p1.AddEmployee(t3, false, true, false, false, 30);
		p2.AddEmployee(t4, false, true, false, false, 30);
		p2.AddEmployee(t5, false, true, false, false, 30);
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				p2.AddEmployee(t3, false, true, false, false, 30);
			}
		});
		assertEquals("Employees can not be in multiple projects at the same time!", e.getMessage());
	}
}
