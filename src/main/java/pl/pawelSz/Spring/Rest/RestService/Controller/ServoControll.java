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
	@RequestMapping(value = "/basket/", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> listAllItems() {
		List<Item> items = itemService.showBasket();
		if (items.isEmpty()) {
			logger.info("nothing in basket");
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}
		logger.info("Something in");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Add Item to
	// basket---------------------------------------------
	@RequestMapping(value = "/basket/add/{name}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> addItemToBasket(@PathVariable String name, @PathVariable int qty) {
		List<Item> items = itemService.showBasket();
		itemService.addToBasket(name, qty);
		logger.info("Added "+qty+" "+name+" to basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Remove Item from
	// basket------------------------------------------

	@RequestMapping(value = "/basket/remove/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeItemFromBasket(@PathVariable String name) {
		List<Item> items = itemService.showBasket();
		itemService.removeFromBasket(name);
		logger.info("Removed "+name+" from basket");
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	// -------------------Change Quantity Item from
	// basket------------------------------------------

	@RequestMapping(value = "/basket/change/{name}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> removeQtyItemFromBasket(@PathVariable String name, @PathVariable int qty) {
		List<Item> items = itemService.showBasket();
		for (Item item : items) {
			if (item.getName().equals(name)) {
				item.setQuantity(qty);
			}
		}
		logger.info("Changed quantity of "+name+" to "+qty);
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
		itemService.totalCost();
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
 * 1) Uprzątnięcie metod
 * 2) Posprzątanie projektu
 * 3) workbench na gitHubie
 * 4) Testy Unit
 * 5) testy Integration
 * 6)UATy
 * 7)  Ogarnięcie czemu cost działa z opóźnieniem
 * 8) wrzucenie buildera do itema
 */
}