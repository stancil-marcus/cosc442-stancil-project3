package project3;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.InputMismatchException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest {

	static VendingMachine machine;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	/**
	 * Creates VendingMachine object to test.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		machine = new VendingMachine();
	}

	/**
	 * Makes machine null after the testing is done
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		machine = null;
	}
	
	/**
	 * This method tests the getItem() method by creating an item, adding it to the machine in slot A and seeing if the item we
	 * just added to slot A is the same item we created.
	 */
	@Test
	public void testAddItem() {
		VendingMachineItem item = new VendingMachineItem("bisket", 2.0);
		machine.addItem(item, "A");
		assertSame(item, machine.getItem("A"));
	}
	
	/**
	 * This method tests the getItem() method by adding an item and retrieve it through the getItem() method.
	 */
	@Test
	public void testGetItem() {
		VendingMachineItem item = new VendingMachineItem("bisket", 2.0);
		machine.addItem(item, "A");
		assertSame(item, machine.getItem("A"));
	}
	
	/**
	 * This method tests to see if the getItem method fails when the user tries to retrieve an item that is not in the machine.
	 */
	@Test (expected = VendingMachineException.class )
	public void testGetItemFail() {
		machine.getItem("R");
	}
	
	/**
	 * This method tests the getItem() method by seeing if the prerequisite that an item must be added to a null
	 * slot is acknowledged. This method should fail.
	 */
	@Test(expected = VendingMachineException.class)
	public void testAddItemFailOccupiedSpace()
	{
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "A");
		machine.addItem(item, "A");
	}
	
	/**
	 * This method tests the addItem() and getSlotIndex() methods to see if they acknowledge that the user is trying to place 
	 * an item in a slot that does not exist
	 */
	@Test(expected = VendingMachineException.class)
	public void testAddItemFailWrongCode()
	{
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "E");
	}
	
	/**
	 * This method tests the removeItem() method to see if it successfully removes an item from the vending machine.
	 */
	@Test
	public void testRemoveItem()
	{
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "C");
		machine.removeItem("C");
	}
	
	/**
	 * This method tests the removeItem() method to see if it fails when trying to remove an item from a null slot.
	 * This test should fail.
	 */
	@Test(expected = VendingMachineException.class)
	public void testRemoveItemFail()
	{
		machine.removeItem("A");
	}
	
	@Test(expected = VendingMachineException.class)
	public void testRemoveItemWrongCode()
	{
		machine.removeItem("5");
	}
	
	/**
	 * This method test the insertMoney() method. This method tests if the user can successfully enter money into
	 * the machine and uses the assertEquals method to see if our balance is the same amount of money we entered.
	 */
	@Test
	public void testInsertMoney()
	{
		machine.insertMoney(10.00);
		assertEquals(10.00, machine.getBalance(), 0.1);
	}
	
	/**
	 * This method tests to see if the insertMoney() method fails when you try to allocate a negative value to the machine.
	 * This test should fail.
	 */
	@Test(expected = VendingMachineException.class)
	public void testInsertMoneyFail()
	{
		machine.insertMoney(-10.00);
	}
	
	/**
	 * This method tests the getBalance() method by inserting money into the machine and then using getBalance() to see if the money
	 * the user should have in the machine is equal to their balance.
	 */
	@Test
	public void testGetBalance()
	{
		machine.insertMoney(10.00);
		assertEquals(10.00, machine.getBalance(),0.1);
	}
	
	/**
	 * This method tests the makePurchase() method by inserting money into the vending machine and using makePurchase method to see if
	 * the user can successfully make a purchase. If the makePurchase() method returns true, then the test was
	 * successful.
	 */
	@Test
	public void testMakePurchase()
	{
		machine.insertMoney(10.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "A");
		assertTrue(machine.makePurchase("A"));
	}
	
	/**
	 * This method tests to see if makePurchase() fails when the user has insufficient funds. This test shouldn't
	 * fail.
	 */
	@Test
	public void testMakePurchaseFailLackOfFunds()
	{
		machine.insertMoney(1.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.00);
		machine.addItem(item, "A");
		assertFalse(machine.makePurchase("A"));
	}
	
	/**
	 * This method tests to see it makePurchase() fails when the user inputs a wrong code. This test shouldn't
	 * fail.
	 */
	@Test
	public void testMakePurchaseFailIncorrectCode()
	{
		machine.insertMoney(2.00);
		assertFalse(machine.makePurchase("B"));
	}
	
	/**
	 * This method tests to see if the machine returns the correct change after the user makes a purchase.
	 */
	@Test
	public void testReturnChange()
	{
		machine.insertMoney(10.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "D");
		machine.makePurchase("D");
		assertEquals(machine.returnChange(), 8,0.1);
	}
	
	@Test
	public void testGetSlotIndex() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = VendingMachine.class.getDeclaredMethod("getSlotIndex", String.class);
		m.setAccessible(true);
		int i = (int) m.invoke(machine, "C");
		assertEquals(i,2);
}

}
