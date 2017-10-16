package pl.pawelSz.Spring.Rest.RestService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;
import pl.pawelSz.Spring.Rest.RestService.Model.BasketRepository;
import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Model.ItemRepository;
import pl.pawelSz.Spring.Rest.RestService.Service.ItemServiceImplementation;

/**
 *
 * @author Paweł Szymaszek
 * @version 1.0
 * @since 21.09.2017
 *
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class ItemServiceImplTest {

	/*
	 * ======================================== Unit test of service class
	 * ========================================
	 */

	public static final Logger logger = LoggerFactory.getLogger(ItemServiceImplTest.class);

	@Autowired
	private ItemServiceImplementation itemServImpl;

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private BasketRepository basketRepository;

	@Before
	public void dbInit() {
		Item a = new Item("A", 40, 70, 3);
		Item b = new Item("B", 10, 15, 2);
		Item c = new Item("C", 30, 60, 4);
		Item d = new Item("D", 25, 40, 2);
		itemRepository.save(a);
		itemRepository.save(b);
		itemRepository.save(c);
		itemRepository.save(d);
		basketRepository.deleteAll();
	}

//	@After
//	public void dbClear(){
//		
//	}
	/*
	 * Test of showBasket method, ItemServiceImplementation.class
	 */
	@Test
	public void testShowBasket() {
		assertEquals(basketRepository.findAll(), itemServImpl.showBasket());
	}

	/*
	 * Test of addToBasket (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testAddToBasketStringLong() {

		itemServImpl.addToBasket("A", 3);

		assertNotNull(basketRepository);
		assertEquals(basketRepository.findOne(1l).getCost(), itemServImpl.showBasket().iterator().next().getCost());

	}

	/*
	 * Test of addToBasket (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testAddToBasketLongint() {
		itemServImpl.addToBasket(2, 1);

		assertNotNull(basketRepository);
		assertEquals(basketRepository.findOne(1l).getCost(), itemServImpl.showBasket().iterator().next().getCost());
	}

	/*
	 * Test of removeFromBasket (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testRemoveFromBasketString() {
		itemServImpl.addToBasket("A", 3);
		itemServImpl.addToBasket(2, 1);
		itemServImpl.removeFromBasket("B");
		assertEquals(1, basketRepository.count());
	}

	/*
	 * Test of removeFromBasket (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testRemoveFromBasketLong() {
		itemServImpl.addToBasket("A", 3);
		itemServImpl.addToBasket(2, 1);
		
		itemServImpl.removeFromBasket(1);
		assertEquals(1, basketRepository.count());
	}

	/*
	 * Test of modifyOrder (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testModifyOrderStringInt() {
		itemServImpl.addToBasket("D", 1);
		itemServImpl.modifyOrder("D", 100);
		assertEquals(100, basketRepository.findAll().iterator().next().getQuantity());
	}
		

	/*
	 * Test of modifyOrder (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testModifyOrderIntInt() {
		itemServImpl.addToBasket(4, 1);
		itemServImpl.modifyOrder(1, 200);
		assertEquals(200, basketRepository.findAll().iterator().next().getQuantity());
	}

	/*
	 * Test of findItem (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testFindItemString() {
	assertEquals("A", itemServImpl.findItem("A").getName());
	}

	/*
	 * Test of findItem (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	@Test
	public void testFindItemInt() {
		assertEquals("A", itemServImpl.findItem(1).getName());
	}

	 /*
	 * Test of itemCost (specified by name) method,
	 * ItemServiceImplementation.class
	 */
	 @Test
	 public void testItemCostString() {
		 itemServImpl.addToBasket("A", 1);
		 assertEquals(40, basketRepository.findAll().iterator().next().getCost());
	 }
	 /*
	 * Test of itemCost (specified by id) method,
	 * ItemServiceImplementation.class
	 */
	 @Test
	 public void testItemCostInt() {
		 itemServImpl.addToBasket(1, 1);
		 assertEquals(40, basketRepository.findAll().iterator().next().getCost());
	 }
	 /*
	 * Test of totalCost method, ItemServiceImplementation.class
	 */
	 @Test
	 public void testTotalCost() {
		 itemServImpl.addToBasket(1, 1);
		 itemServImpl.addToBasket(2, 1);
		 assertEquals(50, itemServImpl.totalCost());
	 }
	
	/*
	 * Test of removeAllfromBasket method, ItemServiceImplementation.class
	 */
	@Test
	public void testRemoveAllFromBasket() {
		itemServImpl.addToBasket(4, 1);
		itemServImpl.removeAllFromBasket();
		assertEquals(0, basketRepository.count());
		
	}

}
