package pl.pawelSz.Spring.Rest.RestService.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;
import pl.pawelSz.Spring.Rest.RestService.Model.BasketRepository;
import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Model.ItemRepository;
import pl.pawelSz.Spring.Rest.RestService.Service.ItemServiceImplementation;
import pl.pawelSz.Spring.Rest.RestService.Util.MyError;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

@Controller
@RequestMapping("/checkout")
public class ServoControll {

	public static final Logger logger = LoggerFactory.getLogger(ServoControll.class);

	@Autowired
	ItemRepository itemRepository;
	@Autowired
	BasketRepository basketRepository;
	@Autowired
	ItemServiceImplementation itemService;
	
	//==========================================================================
	//TESTING ORM DB MySQL
	//==========================================================================
	@GetMapping(path="/buy")
	public @ResponseBody String buyProds() {
		Item x = itemRepository.findOne(1l);
		Basket b = new Basket(3, 666, x);
		basketRepository.save(b);
		return "Bought";
	}

	@GetMapping(path="/add") 
	public @ResponseBody String addNew () {
		
		Item a = new Item( "A", 40, 70, 3);
		Item b = new Item( "B", 10, 15, 2);
		Item c = new Item( "C", 30, 60, 4);
		Item d = new Item( "D", 25, 40, 2);
		itemRepository.save(a);
		itemRepository.save(b);
		itemRepository.save(c);
		itemRepository.save(d);
		return "Saved";
	}
	
	@GetMapping(path="/show")
	public @ResponseBody Iterable<Basket> showProds() {
		
		return basketRepository.findAll();
	}
	//========================================================================
	
	
	
	
	/*
	 * Show Basket
	 */
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Basket>> showCart() {
		
		if (itemService.showBasket().iterator().hasNext()==false) {
			logger.info("nothing in basket");
			return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(),HttpStatus.OK);

		}
		logger.info("something is in");
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(),HttpStatus.OK);
	}

	/*
	 * Add Item to basket
	 * 
	 * @param name
	 * 
	 * @param qty
	 * 
	 */
	@RequestMapping(value = "/basket/add/name/{name}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Basket>> addItemToBasket(@PathVariable String name, @PathVariable int qty) {
		
		if (itemService.findItem(name) == null) {
			logger.error("Item not found. " + name);
			return new ResponseEntity(new MyError("Unable to add item with name: " + name + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if (qty <= 0) {
			logger.error("Quantity must be bigger than 0, qty=" + qty);
			return new ResponseEntity(new MyError("Unable to add item with qty: " + qty + ", must be bigger than zero"),
					HttpStatus.NOT_FOUND);
		}
		itemService.addToBasket(name, qty);
		
		logger.info("Added " + qty + " items " + name + " to basket");
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Add Item to basket
	 * 
	 * @param id
	 * 
	 * @param qty
	 * 
	 */
	@RequestMapping(value = "/basket/add/id/{id}/{qty}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Basket>> addItemToBasket(@PathVariable long id, @PathVariable int qty) {
		
		if (itemService.findItem(id) == null) {
			logger.error("Item not found. " + id);
			return new ResponseEntity(new MyError("Unable to add item with id: " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if (qty <= 0) {
			logger.error("Quantity must be bigger than 0, qty=" + qty);
			return new ResponseEntity(new MyError("Unable to add item with qty: " + qty + ", must be bigger than zero"),
					HttpStatus.NOT_FOUND);
		}
		itemService.addToBasket(id, qty);
		
		logger.info("Added " + qty + " items id: " + id + " to basket");
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Remove Item from basket
	 * 
	 * @param name
	 * 
	 * @param qty
	 * 
	 */
	@RequestMapping(value = "/basket/remove/name/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Iterable<Basket>> removeItemFromBasket(@PathVariable String name) {
		
		if (itemService.findItem(name) == null) {
			logger.error("Item not found. " + name);
			return new ResponseEntity(new MyError("Unable to remove item with name: " + name + " not found."),
					HttpStatus.NOT_FOUND);
		}
		itemService.removeFromBasket(name);
		logger.info("Removed item: " + name + " from basket");
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Remove Item from basket
	 * 
	 * @param id
	 * 
	 * @param qty
	 * 
	 */

	@RequestMapping(value = "/basket/remove/id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Iterable<Basket>> removeItemFromBasket(@PathVariable long id) {
		
		if (itemService.findItem(id) == null) {
			logger.error("Item not found. " + id);
			return new ResponseEntity(new MyError("Unable to remove item with id: " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		itemService.removeFromBasket(id);
		logger.info("Removed item: " + id + " from basket");
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Change amount of Item in basket
	 * 
	 * @param name
	 * 
	 * @param qty
	 * 
	 */

	@RequestMapping(value = "/basket/change/name/{name}/{qty}", method = RequestMethod.PUT)
	public ResponseEntity<Iterable<Basket>> modifyQtyItemFromBasket(@PathVariable String name, @PathVariable int qty) {
		
		if (itemService.findItem(name) == null) {
			logger.error("Item not found. " + name);
			return new ResponseEntity(new MyError("Unable to modify item with name: " + name + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if (qty < 0) {
			logger.error("Quantity must be bigger than 0, qty=" + qty);
			return new ResponseEntity(new MyError("Unable to add item with qty: " + qty + ", must be bigger or equal zero"),
					HttpStatus.NOT_FOUND);
		}
		itemService.modifyOrder(name, qty);
		logger.info("Changed quantity of item " + name + " to " + qty);
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Change amount of Item in basket
	 * 
	 * @param id
	 * 
	 * @param qty
	 * 
	 */
	@RequestMapping(value = "/basket/change/id/{id}/{qty}", method = RequestMethod.PUT)
	public ResponseEntity<Iterable<Basket>> modifyQtyItemFromBasket(@PathVariable long id, @PathVariable int qty) {
		
		if (itemService.findItem(id) == null) {
			logger.error("Item not found. " + id);
			return new ResponseEntity(new MyError("Unable to modify item with id: " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if (qty < 0) {
			logger.error("Quantity must be bigger than 0, qty=" + qty);
			return new ResponseEntity(new MyError("Unable to add item with qty: " + qty + ", must be bigger or equal zero"),
					HttpStatus.NOT_FOUND);
		}
		itemService.modifyOrder(id, qty);
		logger.info("Changed quantity item id: " + id + " to " + qty);
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Remove all Items from basket
	 * 
	 */

	@RequestMapping(value = "/basket/remove/all", method = RequestMethod.DELETE)
	public ResponseEntity<Iterable<Basket>> removeAllItemsFromBasket() {
		itemService.removeAllFromBasket();
		logger.info("Removed everything from basket");
		return new ResponseEntity<Iterable<Basket>>(itemService.showBasket(), HttpStatus.OK);
	}

	/*
	 * Get total cost of all items in basket
	 */
	@RequestMapping(value = "/basket/total", method = RequestMethod.GET)
	public ResponseEntity<Integer> totalCostFromBasket() {
		logger.info("Total Cost: " + itemService.totalCost());

		return new ResponseEntity<Integer>(itemService.totalCost(), HttpStatus.OK);

	}

	/*
	 * Get summary of items in basket
	 */
	@RequestMapping(value = "/basket/summary", method = RequestMethod.GET)
	public ResponseEntity<String> summaryBasket() {
		
		String result = "";
		for (Basket basketOrders : itemService.showBasket()) {
			result += "Item name: " + itemService.findItem(basketOrders.getOrderId()).getName() + " Price: " + itemService.findItem(basketOrders.getOrderId()).getPrice() + " quantity: " + basketOrders.getQuantity()
					+ " Cost: " + basketOrders.getCost() + "\n";

		}
		result += "\n Total Cost: " + itemService.totalCost();
		logger.info(result);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}
}