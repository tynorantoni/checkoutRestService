package pl.pawelSz.Spring.Rest.RestService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Service.ItemServiceImplementation;

public class ItemServiceImplTest {

	public static ItemServiceImplementation itemServTest = new ItemServiceImplementation();
	public static List<Item> testItems;
	public static List<Item> cart = new ArrayList<>();

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

	@BeforeClass
	public static void initializeClass() {

		testItems = new ArrayList<>();
		testItems.add(new Item.Builder().name("A").id(1).price(40).qtyToDiscount(3).specialPrice(70).build());
		testItems.add(new Item.Builder().name("B").id(2).price(10).qtyToDiscount(2).specialPrice(15).build());
		testItems.add(new Item.Builder().name("C").id(3).price(30).qtyToDiscount(4).specialPrice(60).build());
		testItems.add(new Item.Builder().name("D").id(4).price(25).qtyToDiscount(2).specialPrice(40).build());

	}

	@Before
	public void initialize() {
		cart.clear();
	}

	@Test
	public void testShowBasket() {

		assertTrue("Should be empty", cart.isEmpty());
	}

	@Test
	public void testAddToBasketStringInt() {
		String name = "A";
		int qty = 5;
		cart.add(findItem(name));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(0), cart.get(0));

	}

	@Test
	public void testAddToBasketIntInt() {
		int id = 2;
		int qty = 6;
		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(1), cart.get(0));
	}

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

	@Test
	public void testRemoveFromBasketInt() {
		assertTrue(cart.isEmpty());
		int id = 3;
		int qty = 6;
		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(2), cart.get(0));

		for (int i = 0; i < cart.size(); i++) {

			if (cart.get(i).getId()==(id)) {
				cart.remove(i);
				assertTrue(cart.isEmpty());
			}
		}

	}

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

	@Test
	public void testModifyOrderIntInt() {
		int id = 4;
		int qty = 8;
		int qty2 = 80;

		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);

		assertEquals(testItems.get(3), cart.get(0));
		for (Item item : cart) {
			if (item.getId()==(id)) {
				item.setQuantity(qty2);
				assertEquals(testItems.get(3), cart.get(0));
			}

		}
	}

	@Test
	public void testFindItemString() {
		String name = "A";
		for (Item item : testItems) {
			if (item.getName().equals(name)) {
				assertEquals(testItems.get(0), item);
			}
		}
	}

	@Test
	public void testFindItemInt() {
		int id = 1;
		for (Item item : testItems) {
			if (item.getId()==id) {
				assertEquals(testItems.get(0), item);
			}
		}
	}

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
		assertEquals(testItems.get(3).getSpecialPrice()*(testItems.get(3).getQuantity()/testItems.get(3).getQtyToDiscount()), price);
	}

	@Test
	public void testItemCostInt() {
		int id = 4;
		int qty = 7;
		cart.add(findItem(id));
		cart.get(cart.size() - 1).setQuantity(qty);
		
		int price = 0;
		for (Item item : cart) {
			if (item.getId()==id) {
				if (item.getQuantity() % item.getQtyToDiscount() == 0) {
					price = item.getSpecialPrice() * (item.getQuantity() / item.getQtyToDiscount());
					item.setCost(price);
				} else {
					price = item.getQuantity() * item.getPrice();
					item.setCost(price);
				}
			}
		}
		assertEquals(testItems.get(3).getQuantity()*testItems.get(3).getPrice(), price);
	}
	

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
		cart.get(0).setCost(qtyId*cart.get(0).getPrice());
		cart.get(1).setCost(qtyName*cart.get(1).getPrice());
		for (Item item : cart) {
			total += item.getCost();
		}
		assertEquals(cart.get(0).getCost()+cart.get(1).getCost(), total);
	}

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
