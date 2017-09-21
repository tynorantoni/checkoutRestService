package pl.pawelSz.Spring.Rest.RestService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Service.ItemServiceImplementation;

/**
 *
 * @author Paweł Szymaszek
 * @version 1.0
 * @since 21.09.2017
 *
 */

public class ItemServiceImplTest {

	/*
	 * ======================================== 
	 * Unit test of service class
	 * ========================================
	 */

	public static ItemServiceImplementation itemServTest = new ItemServiceImplementation();
	// List of dummy objects
	public static List<Item> testItems;
	// cart - list where will be added/modified/removed Items from testItems
	// storage
	public static List<Item> cart = new ArrayList<>();

	
	
	
	/*====================================================
	 *Methods copied from ItemServiceImplementation class
	 */
	public Item findItem(String name) {
		for (Item item : testItems) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public Item findItem(int id) {
		for (Item item : testItems) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	/*
	 * ====================================================
	 */
	
	
	
	
	
	
	/*
	 * Initialize list of item objects before testing
	 * 
	 */
	@BeforeClass
	public static void initializeClass() {

		testItems = new ArrayList<>();
		testItems.add(new Item(1, "A", 40, 70, 3, 0, 0));
		testItems.add(new Item(2, "B", 10, 15, 2, 0, 0));
		testItems.add(new Item(3, "C", 30, 60, 4, 0, 0));
		testItems.add(new Item(4, "D", 25, 40, 2, 0, 0));

	}

	/*
	 * Clears cart list before each test
	 */
	@Before
	public void initialize() {
		cart.clear();
	}

	/*
	 * Test of showBasket method, ItemServiceImplementation.class
	 */
	@Test
	public void testShowBasket() {

		assertTrue("Should be empty", cart.isEmpty());
	}

	/*
	 * Test of addToBasket (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testAddToBasketStringInt() {
		String name = "A";
		int qty = 5;
		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qty);
		
		assertEquals(testItems.get(0), cart.get(0));

	}

	/*
	 * Test of addToBasket (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testAddToBasketIntInt() {
		int id = 2;
		int qty = 6;
		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(1), cart.get(0));
	}

	/*
	 * Test of removeFromBasket (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testRemoveFromBasketString() {
		assertTrue(cart.isEmpty());
		String name = "C";
		int qty = 6;
		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(2), cart.get(0));

		for (int i = 0; i < cart.size(); i++) {

			if (cart.get(i).getName().equals(name)) {
				cart.remove(i);
				assertTrue(cart.isEmpty());
			}
		}

	}

	/*
	 * Test of removeFromBasket (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testRemoveFromBasketInt() {
		assertTrue(cart.isEmpty());
		int id = 3;
		int qty = 6;
		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(2), cart.get(0));

		for (int i = 0; i < cart.size(); i++) {

			if (cart.get(i).getId() == (id)) {
				cart.remove(i);
				assertTrue(cart.isEmpty());
			}
		}

	}

	/*
	 * Test of modifyOrder (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testModifyOrderStringInt() {
		String name = "D";
		int qty = 8;
		int qty2 = 80;

		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(3), cart.get(0));
		for (Item item : cart) {
			if (item.getName().equals(name)) {
				item.setQuantity(qty2);
				assertEquals(testItems.get(3), cart.get(0));
			}

		}

	}

	/*
	 * Test of modifyOrder (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testModifyOrderIntInt() {
		int id = 4;
		int qty = 8;
		int qty2 = 80;

		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(3), cart.get(0));
		for (Item item : cart) {
			if (item.getId() == (id)) {
				item.setQuantity(qty2);
				assertEquals(testItems.get(3), cart.get(0));
			}

		}
	}

	/*
	 * Test of findItem (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testFindItemString() {
		String name = "A";
		for (Item item : testItems) {
			if (item.getName().equals(name)) {
				assertEquals(testItems.get(0), item);
			}
		}
	}

	/*
	 * Test of findItem (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testFindItemInt() {
		int id = 1;
		for (Item item : testItems) {
			if (item.getId() == id) {
				assertEquals(testItems.get(0), item);
			}
		}
	}

	/*
	 * Test of itemCost (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testItemCostString() {
		String name = "D";
		int qty = 8;
		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qty);

		int price = 0;
		for (Item item : cart) {
			if (item.getName().equals(name)) {
				if (item.getQuantity() % item.getQtyToDiscount() == 0) {
					price = item.getSpecialPrice() * (item.getQuantity() / item.getQtyToDiscount());
					item.setCost(price);
				} else {
					price = item.getQuantity() * item.getPrice();
					item.setCost(price);
				}
			}
		}
		assertEquals(testItems.get(3).getSpecialPrice()
				* (testItems.get(3).getQuantity() / testItems.get(3).getQtyToDiscount()), price);
	}

	/*
	 * Test of itemCost (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testItemCostInt() {
		int id = 4;
		int qty = 7;
		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		int price = 0;
		for (Item item : cart) {
			if (item.getId() == id) {
				if (item.getQuantity() % item.getQtyToDiscount() == 0) {
					price = item.getSpecialPrice() * (item.getQuantity() / item.getQtyToDiscount());
					item.setCost(price);
				} else {
					price = item.getQuantity() * item.getPrice();
					item.setCost(price);
				}
			}
		}
		assertEquals(testItems.get(3).getQuantity() * testItems.get(3).getPrice(), price);
	}

	/*
	 * Test of totalCost method, ItemServiceImplementation.class
	 */
	@Test
	public void testTotalCost() {
		String name = "D";
		int qtyName = 3;
		int id = 3;
		int qtyId = 7;
		int total = 0;

		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qtyId);
		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qtyName);
		assertEquals(testItems.get(3), cart.get(1));
		assertEquals(testItems.get(2), cart.get(0));
		cart.get(0).setCost(qtyId * cart.get(0).getPrice());
		cart.get(1).setCost(qtyName * cart.get(1).getPrice());
		for (Item item : cart) {
			total += item.getCost();
		}
		assertEquals(cart.get(0).getCost() + cart.get(1).getCost(), total);
	}

	/*
	 * Test of removeAllfromBasket method, 
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testRemoveAllFromBasket() {
		String name = "D";
		int qty = 8;

		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qty);
		assertEquals(testItems.get(3), cart.get(0));
		cart.clear();
		assertTrue(cart.isEmpty());

	}

}
