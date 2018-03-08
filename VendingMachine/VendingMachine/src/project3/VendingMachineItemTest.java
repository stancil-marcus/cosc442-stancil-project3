package project3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class VendingMachineItemTest {

	VendingMachineItem item1;
	VendingMachineItem item2;
	VendingMachineItem item3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		item1 = new VendingMachineItem("toast", 2.0);
		item2 = new VendingMachineItem("muffin", 3.0);
	}

	@After
	public void tearDown() throws Exception {
		item1 = null;
		item2 = null;
	}
	
	/**
	 * This method tests to see if the items still have the same name we named them in the the setUp() method.
	 */
	@Test
	public void testName() {
		assertSame("toast", item1.getName());
		assertSame("muffin", item2.getName());
	}
	
	/**
	 * This method tests to see if the items still have the same price we gave them in the setUp() method.
	 */
	@Test
	public void testPrice()
	{
		assertEquals(2.0, item1.getPrice(),0.1);
		assertEquals(3.0, item2.getPrice(), 0.1);
	}
	
	/**
	 * This method tests to see if the constructor for the VendingMachineItem test fails when the user tries to 
	 * give an item a price < 0.
	 */
	@Test(expected = VendingMachineException.class)
	public void testConstructorFail()
	{
	
		item1 = new VendingMachineItem("crackers", -10.0);
		item2 = new VendingMachineItem("crackers", -10.0);
	}
	

	
	

}