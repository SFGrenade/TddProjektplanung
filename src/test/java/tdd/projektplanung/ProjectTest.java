package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

public class ProjectTest {
	private Project p;
	
	@BeforeEach
	public void setup() {
		p = new Project();
	}
	
	@Test
	public void testCreateProject() {
		Project pTest = new Project("test", new Date(), new Date(), 1337);
		
		assertEquals("test", pTest.getName());
		assertEquals((new Date()).toString(), pTest.getStartDate().toString());
		assertEquals((new Date()).toString(), pTest.getEndDate().toString());
		assertEquals(1337, pTest.getHoursTotal());
	}
}
