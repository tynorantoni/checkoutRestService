package pl.pawelSz.Spring.Rest.RestService.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Service.ItemServiceImplementation;

@RestController
@RequestMapping("/checkout")
public class ServoControll {

	public static final Logger logger = LoggerFactory.getLogger(ServoControll.class);

	@Autowired
	ItemServiceImplementation itemService;

	// -------------------------Show Basket------------------------
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> listAllItems() {
		List<Item> items = itemService.showBasket();
		if (items.isEmpty()) {
			logger.info("nothing in basket");
			return new ResponseEntity(HttpStatus.NOT_FOUND);

		}
		logger.info("Something is in");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Add Item to
	// basket---------------------------------------------
	@RequestMapping(value = "/basket/add/name/{name}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> addItemToBasket(@PathVariable String name, @PathVariable int qty) {
		List<Item> items = itemService.showBasket();
		itemService.addToBasket(name, qty);
		itemService.itemCost(name);
		logger.info("Added "+qty+" items "+name+" to basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/basket/add/id/{id}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> addItemToBasket(@PathVariable int id, @PathVariable int qty) {
		List<Item> items = itemService.showBasket();
		itemService.addToBasket(id, qty);
		itemService.itemCost(id);
		logger.info("Added "+qty+" items id: "+id+" to basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Remove Item from
	// basket------------------------------------------

	@RequestMapping(value = "/basket/remove/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeItemFromBasket(@PathVariable String name) {
		List<Item> items = itemService.showBasket();
		itemService.removeFromBasket(name);
		logger.info("Removed item: "+name+" from basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/basket/remove/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeItemFromBasket(@PathVariable int id) {
		List<Item> items = itemService.showBasket();
		itemService.removeFromBasket(id);
		logger.info("Removed item: "+id+" from basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}


	// -------------------Change Quantity Item from
	// basket------------------------------------------

	@RequestMapping(value = "/basket/change/name/{name}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeQtyItemFromBasket(@PathVariable String name, @PathVariable int qty) {
		List<Item> items = itemService.showBasket();
		for (Item item : items) {
			if (item.getName().equals(name)) {
				item.setQuantity(qty);
			}
		}
		logger.info("Changed quantity of item "+name+" to "+qty);
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/basket/change/id/{id}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeQtyItemFromBasket(@PathVariable int id, @PathVariable int qty) {
		List<Item> items = itemService.showBasket();
		for (Item item : items) {
			if (item.getId()==id) {
				item.setQuantity(qty);
			}
		}
		logger.info("Changed quantity item id: "+id+" to "+qty);
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Remove all Items from
	// basket------------------------------------------

	@RequestMapping(value = "/basket/remove/all", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeItemFromBasket() {
		List<Item> items = itemService.showBasket();
		itemService.removeAllFromBasket();
		logger.info("Removed everything from basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Total Cost-------------------------------------------
	@RequestMapping(value = "/basket/total", method = RequestMethod.GET)
	public ResponseEntity<?> totalCostFromBasket() {
		logger.info("Total Cost: "+ itemService.totalCost());
		
		return new ResponseEntity<Integer>(itemService.totalCost(), HttpStatus.OK);

	}
	
	// -------------------Basket Summary-------------------------------------------
		@RequestMapping(value = "/basket/summary", method = RequestMethod.GET)
		public ResponseEntity <String> summaryBasket() {
			List<Item> items = itemService.showBasket();
			String result="";
			for (Item item : items) {
				result+="Item name: "+item.getName()+" Price: "+item.getPrice()+" quantity: "+item.getQuantity()+" Cost: " 
								+itemService.itemCost(item.getName())+"\n";
				
			}
			result+="\n Total Cost: "+itemService.totalCost();
			logger.info(result);
			return new ResponseEntity<String>(result, HttpStatus.OK);

		}
/*
 * TODO:
 * 1)add the same object? => change qty 
 * 2) Posprzątanie projektu
 * 3) put post get delete
 * 4) Testy Unit
 * 5) testy Integration
 * 6)UATy
 * 
 * 8) wrzucenie buildera do itema
 */
}