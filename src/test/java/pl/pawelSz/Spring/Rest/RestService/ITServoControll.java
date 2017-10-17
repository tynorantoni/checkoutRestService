package pl.pawelSz.Spring.Rest.RestService;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Model.ItemRepository;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.properties")
public class ITServoControll {

	public static final Logger logger = LoggerFactory.getLogger(ITServoControll.class);

	@LocalServerPort
	private int port;

	@Autowired
	private ItemRepository itemRepository;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	private HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {

		return "http://localhost:" + port + uri;
	}

	@Before
	public void initDummyDB() {

		Item a = new Item("A", 40, 70, 3);
		Item b = new Item("B", 10, 15, 2);
		Item c = new Item("C", 30, 60, 4);
		Item d = new Item("D", 25, 40, 2);

		itemRepository.save(a);
		itemRepository.save(b);
		itemRepository.save(c);
		itemRepository.save(d);

	}

	@Test
	public void testCShowCart() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket"), HttpMethod.GET,
				entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":3,\"cost\":70,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}},"
				+ "{\"orderId\":2,\"quantity\":10,\"cost\":75,\"items\":{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2}}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testAAddItemToBasketA() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/add/name/A/3"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":3,\"cost\":70,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testBAddItemToBasketB() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/add/id/2/10"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":3,\"cost\":70,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}},"
				+ "{\"orderId\":2,\"quantity\":10,\"cost\":75,\"items\":{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2}}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testHRemoveItemFromBasketA() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/remove/name/B/"),
				HttpMethod.DELETE, entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":10,\"cost\":250,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testIRemoveItemFromBasketB() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/remove/id/3/"),
				HttpMethod.DELETE, entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":10,\"cost\":250,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}}]";
		
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testDModifyItemFromBasketA() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/change/name/A/10"),
				HttpMethod.PUT, entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":10,\"cost\":250,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}},"
				+ "{\"orderId\":2,\"quantity\":10,\"cost\":75,\"items\":{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2}}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	
	@Test
	public void testEModifyItemFromBasketB() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/change/id/2/15"),
				HttpMethod.PUT, entity, String.class);

		String expected = "[{\"orderId\":1,\"quantity\":10,\"cost\":250,\"items\":{\"id\":1,\"name\":\"A\",\"price\":40,\"specialPrice\":70,\"qtyToDiscount\":3}},"
				+ "{\"orderId\":2,\"quantity\":15,\"cost\":115,\"items\":{\"id\":2,\"name\":\"B\",\"price\":10,\"specialPrice\":15,\"qtyToDiscount\":2}}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testJRemoveAllItemsFromBasket() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/remove/all"),
				HttpMethod.DELETE, entity, String.class);

		String expected = "[]";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testFTotalCost() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/total"),
				HttpMethod.GET, entity, String.class);

		String expected = "365";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void testGBasketSummary() {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/checkout/basket/summary"),
				HttpMethod.GET, entity, String.class);

		String expected = "Item name: A Price: 40 quantity: 10 Cost: 250\n"
				+ "Item name: B Price: 10 quantity: 15 Cost: 115\n" + "\n Total Cost: 365";
		assertEquals(expected, response.getBody());

	}
}
