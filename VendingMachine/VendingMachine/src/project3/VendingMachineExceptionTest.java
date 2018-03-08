package project3;

import static org.junit.Assert.*;

import org.junit.Test;

public class VendingMachineExceptionTest {

	@Test
	public void testConstructor() {
		VendingMachineException exception = new VendingMachineException ("Wrong");
		assertSame(exception.getMessage(), "Wrong");
}
}