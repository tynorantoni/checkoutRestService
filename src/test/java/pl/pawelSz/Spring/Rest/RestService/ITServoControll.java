package pl.pawelSz.Spring.Rest.RestService;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Paweł Szymaszek
 * @version 1.0
 * @since 21.09.2017
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Sorting methods in ascending order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITServoControll {

	@LocalServerPort
	private int port;
	
	private String createURLWithPort(String uri) {
		
		return "http://localhost:" + port + uri;
	}

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	/*
	 * ================================================== 
	 * Integration Test performed in alphabetic order 
	 * for check that whole service is working properly 
	 * ==================================================
	 */

	
	/*
	 * Test of showCart method, ServoControll.class
	 */
	@Test
	public void testCShowCart() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket"), HttpMethod.GET,
				entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":3,\"cost\":70},"
				+ "{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2,\"quantity\":10,\"cost\":75}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/*
	 * Test of addItemToBasket(String) method, ServoControll.class
	 */
	@Test
	public void testAAddItemToBasketA() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/add/name/A/3"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":3,\"cost\":70}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/*
	 * Test of addItemToBasket(int) method, ServoControll.class
	 */
	@Test
	public void testBAddItemToBasketB() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/add/id/2/10"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":3,\"cost\":70},"
				+ "{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2,\"quantity\":10,\"cost\":75}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/*
	 * Test of removeItemFromBasket(String) method, ServoControll.class
	 */
	@Test
	public void testHRemoveItemFromBasketA() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/remove/name/B/"),
				HttpMethod.DELETE, entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":10,\"cost\":250}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/*
	 * Test of removeItemFromBasket(int) method, ServoControll.class
	 */
	@Test
	public void testIRemoveItemFromBasketB() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/remove/id/3/"),
				HttpMethod.DELETE, entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":10,\"cost\":250}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/*
	 * Test of modifyQtyItemFromBasket(String) method, ServoControll.class
	 */
	@Test
	public void testDModifyItemFromBasketA() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/change/name/A/10"),
				HttpMethod.PUT, entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":10,\"cost\":250},"
				+ "{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2,\"quantity\":10,\"cost\":75}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/*
	 * Test of modifyQtyItemFromBasket(int) method, ServoControll.class
	 */
	@Test
	public void testEModifyItemFromBasketB() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/change/id/2/15"),
				HttpMethod.PUT, entity, String.class);

		String expected = "[{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3,\"quantity\":10,\"cost\":250},"
				+ "{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2,\"quantity\":15,\"cost\":150}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/*
	 * Test of removeAllItemsFromBasket method, ServoControll.class
	 */
	@Test
	public void testJRemoveAllItemsFromBasket() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/remove/all"),
				HttpMethod.DELETE, entity, String.class);

		String expected = "[]";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/*
	 * Test of totalCostFromBasket method, ServoControll.class
	 */
	@Test
	public void testFTotalCost() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/total"),
				HttpMethod.GET, entity, String.class);

		String expected = "550";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/*
	 * Test of summaryBasket method, ServoControll.class
	 */
	@Test
	public void testGBasketSummary() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/summary"),
				HttpMethod.GET, entity, String.class);

		String expected = "Item name: A Price: 40 quantity: 10 Cost: 250\n"
				+ "Item name: B Price: 10 quantity: 15 Cost: 150\n" + "\n Total Cost: 550";
		assertEquals(expected, response.getBody());

	}
}
