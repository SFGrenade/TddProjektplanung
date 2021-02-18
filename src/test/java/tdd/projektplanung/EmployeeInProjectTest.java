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
}
