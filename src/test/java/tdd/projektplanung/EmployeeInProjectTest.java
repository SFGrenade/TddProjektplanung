package tdd.projektplanung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class EmployeeInProjectTest {
	private EmployeeInProject eip;
	
	@BeforeEach
	public void setup() {
		eip = new EmployeeInProject();
	}
	
	@Test
	public void testIsProductOwner() {
		eip.setProductOwner(true);
		
		assertTrue(eip.isProductOwner());
	}
	
	@Test
	public void testIsScrumMaster() {
		eip.setScrumMaster(true);
		
		assertTrue(eip.isScrumMaster());
	}
	
	@Test
	public void testIsBackendDeveloper() {
		eip.setBackendDeveloper(true);
		
		assertTrue(eip.isBackendDeveloper());
	}
	
	@Test
	public void testIsFrontendDeveloper() {
		eip.setFrontendDeveloper(true);
		
		assertTrue(eip.isFrontendDeveloper());
	}
}
