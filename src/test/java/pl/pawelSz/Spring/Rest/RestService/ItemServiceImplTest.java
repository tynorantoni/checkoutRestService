package pl.pawelSz.Spring.Rest.RestService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;
import pl.pawelSz.Spring.Rest.RestService.Model.BasketRepository;
import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Model.ItemRepository;
import pl.pawelSz.Spring.Rest.RestService.Service.ItemService;
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
	 * ======================================== Unit test of service class
	 * ========================================
	 */

	private ItemServiceImplementation itemServImpl;

	@Mock
	private BasketRepository basketRepository;
	@Mock
	private ItemRepository itemRepository;
	@Mock
	private Basket basket;
	@Mock
	private Item item;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		itemServImpl = new ItemServiceImplementation();
		itemServImpl.setItemRepository(itemRepository);
		itemServImpl.setBasketRepository(basketRepository);
	}



	/*
	 * Test of showBasket method, ItemServiceImplementation.class
	 */
	@Test
	public void testShowBasket() {
		Iterable<Basket> element = basketRepository.findAll();
		
		when(itemServImpl.showBasket()).thenReturn(element);
		
		Iterable<Basket> elementOne = itemServImpl.showBasket();

		assertThat(elementOne, is(equalTo(element)));
	}

	/*
	 * Test of addToBasket (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testAddToBasketStringInt() {
		Iterable<Basket> element = basketRepository.findAll();
		Item a = new Item("A", 40, 70, 3);
		itemRepository.save(a);
		when(itemServImpl.addToBasket("A", 3)).thenReturn(element);

		Basket testObj = new Basket(3, 70, a);
		basketRepository.save(testObj);
		assertThat(testObj, is(equalTo(basketRepository.findAll())));
	}

	
	 /*
	 * Test of addToBasket (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	 @Test
	 public void testAddToBasketIntInt() {
		 Iterable<Basket> element = basketRepository.findAll();
			Item a = new Item("A", 40, 70, 3);
			itemRepository.save(a);
			when(itemServImpl.addToBasket(1, 3)).thenReturn(element);

			Basket testObj = new Basket(3, 70, a);
			basketRepository.save(testObj);
			assertThat(testObj, is(equalTo(basketRepository.findAll())));
		}
	 
	//
	// /*
	// * Test of removeFromBasket (specified by name) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testRemoveFromBasketString() {
	// assertTrue(cart.isEmpty());
	// String name = "C";
	// int qty = 6;
	// cart.add(findItem(name));
	// cart.get(cart.size() - 1).setQuantity(qty);
	//
	// assertEquals(testItems.get(2), cart.get(0));
	//
	// for (int i = 0; i < cart.size(); i++) {
	//
	// if (cart.get(i).getName().equals(name)) {
	// cart.remove(i);
	// assertTrue(cart.isEmpty());
	// }
	// }
	//
	// }
	//
	// /*
	// * Test of removeFromBasket (specified by id) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testRemoveFromBasketInt() {
	// assertTrue(cart.isEmpty());
	// int id = 3;
	// int qty = 6;
	// cart.add(findItem(id));
	// cart.get(cart.size() - 1).setQuantity(qty);
	//
	// assertEquals(testItems.get(2), cart.get(0));
	//
	// for (int i = 0; i < cart.size(); i++) {
	//
	// if (cart.get(i).getId() == (id)) {
	// cart.remove(i);
	// assertTrue(cart.isEmpty());
	// }
	// }
	//
	// }
	//
	// /*
	// * Test of modifyOrder (specified by name) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testModifyOrderStringInt() {
	// String name = "D";
	// int qty = 8;
	// int qty2 = 80;
	//
	// cart.add(findItem(name));
	// cart.get(cart.size() - 1).setQuantity(qty);
	//
	// assertEquals(testItems.get(3), cart.get(0));
	// for (Item item : cart) {
	// if (item.getName().equals(name)) {
	// item.setQuantity(qty2);
	// assertEquals(testItems.get(3), cart.get(0));
	// }
	//
	// }
	//
	// }
	//
	// /*
	// * Test of modifyOrder (specified by id) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testModifyOrderIntInt() {
	// int id = 4;
	// int qty = 8;
	// int qty2 = 80;
	//
	// cart.add(findItem(id));
	// cart.get(cart.size() - 1).setQuantity(qty);
	//
	// assertEquals(testItems.get(3), cart.get(0));
	// for (Item item : cart) {
	// if (item.getId() == (id)) {
	// item.setQuantity(qty2);
	// assertEquals(testItems.get(3), cart.get(0));
	// }
	//
	// }
	// }
	//
	// /*
	// * Test of findItem (specified by name) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testFindItemString() {
	// String name = "A";
	// for (Item item : testItems) {
	// if (item.getName().equals(name)) {
	// assertEquals(testItems.get(0), item);
	// }
	// }
	// }
	//
	// /*
	// * Test of findItem (specified by id) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testFindItemInt() {
	// int id = 1;
	// for (Item item : testItems) {
	// if (item.getId() == id) {
	// assertEquals(testItems.get(0), item);
	// }
	// }
	// }
	//
	// /*
	// * Test of itemCost (specified by name) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testItemCostString() {
	// String name = "D";
	// int qty = 8;
	// cart.add(findItem(name));
	// cart.get(cart.size() - 1).setQuantity(qty);
	//
	// int price = 0;
	// for (Item item : cart) {
	// if (item.getName().equals(name)) {
	// if (item.getQuantity() % item.getQtyToDiscount() == 0) {
	// price = item.getSpecialPrice() * (item.getQuantity() /
	// item.getQtyToDiscount());
	// item.setCost(price);
	// } else {
	// price = item.getQuantity() * item.getPrice();
	// item.setCost(price);
	// }
	// }
	// }
	// assertEquals(testItems.get(3).getSpecialPrice()
	// * (testItems.get(3).getQuantity() / testItems.get(3).getQtyToDiscount()),
	// price);
	// }
	//
	// /*
	// * Test of itemCost (specified by id) method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testItemCostInt() {
	// int id = 4;
	// int qty = 7;
	// cart.add(findItem(id));
	// cart.get(cart.size() - 1).setQuantity(qty);
	//
	// int price = 0;
	// for (Item item : cart) {
	// if (item.getId() == id) {
	// if (item.getQuantity() % item.getQtyToDiscount() == 0) {
	// price = item.getSpecialPrice() * (item.getQuantity() /
	// item.getQtyToDiscount());
	// item.setCost(price);
	// } else {
	// price = item.getQuantity() * item.getPrice();
	// item.setCost(price);
	// }
	// }
	// }
	// assertEquals(testItems.get(3).getQuantity() *
	// testItems.get(3).getPrice(), price);
	// }
	//
	// /*
	// * Test of totalCost method, ItemServiceImplementation.class
	// */
	// @Test
	// public void testTotalCost() {
	// String name = "D";
	// int qtyName = 3;
	// int id = 3;
	// int qtyId = 7;
	// int total = 0;
	//
	// cart.add(findItem(id));
	// cart.get(cart.size() - 1).setQuantity(qtyId);
	// cart.add(findItem(name));
	// cart.get(cart.size() - 1).setQuantity(qtyName);
	// assertEquals(testItems.get(3), cart.get(1));
	// assertEquals(testItems.get(2), cart.get(0));
	// cart.get(0).setCost(qtyId * cart.get(0).getPrice());
	// cart.get(1).setCost(qtyName * cart.get(1).getPrice());
	// for (Item item : cart) {
	// total += item.getCost();
	// }
	// assertEquals(cart.get(0).getCost() + cart.get(1).getCost(), total);
	// }
	//
	// /*
	// * Test of removeAllfromBasket method,
	// * ItemServiceImplementation.class
	// */
	// @Test
	// public void testRemoveAllFromBasket() {
	// String name = "D";
	// int qty = 8;
	//
	// cart.add(findItem(name));
	// cart.get(cart.size() - 1).setQuantity(qty);
	// assertEquals(testItems.get(3), cart.get(0));
	// cart.clear();
	// assertTrue(cart.isEmpty());
	//
	// }
	//
}
