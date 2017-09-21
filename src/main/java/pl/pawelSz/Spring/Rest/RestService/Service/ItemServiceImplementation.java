package pl.pawelSz.Spring.Rest.RestService.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;

/**
 *
 * @author Paweł Szymaszek
 * @version 1.0
 * @since 21.09.2017
 *
 */

@Service("itemService")
@Transactional
public class ItemServiceImplementation implements ItemService {

	/*
	 * =======================================================================
	 * Example of ItemSerice interface implementation. Created for example of
	 * API behavior, with dummy objects - items
	 * ======================================================================
	 */

	public static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);

	private static List<Item> items;
	private static List<Item> basket = new ArrayList<>();
	static {

		items = itemList();

	}

	/*
	 * Method creating dummy objects, used to present the action of RestApi
	 * 
	 * @return items list of item objects
	 */
	private static List<Item> itemList() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1, "A", 40, 70, 3, 0, 0));
		items.add(new Item(2, "B", 10, 15, 2, 0, 0));
		items.add(new Item(3, "C", 30, 60, 4, 0, 0));
		items.add(new Item(4, "D", 25, 40, 2, 0, 0));

		return items;
	}

	/*
	 * Shows basket - list created for item storage
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> showBasket() {
		return basket;
	}

	/*
	 * Add specific item to basket
	 * 
	 * @param name - name of item
	 * 
	 * @param qty - amount of item
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> addToBasket(String name, int qty) {

		if (basket.isEmpty()) {
			logger.info("add to empty list");
			basket.add(findItem(name));
			basket.get(basket.size() - 1).setQuantity(qty);

		} else {
			for (int i = 0; i < basket.size(); i++) {

				if (basket.get(i).getName().equals(name)) {
					logger.info("modify");
					modifyOrder(name, qty + basket.get(i).getQuantity());
					return basket;
				}
			}
			logger.info("adding ");
			basket.add(findItem(name));
			basket.get(basket.size() - 1).setQuantity(qty);
		}
		return basket;

	}

	/*
	 * Add specific item to basket
	 * 
	 * @param id - id of item
	 * 
	 * @param qty - amount of item
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> addToBasket(int id, int qty) {
		if (basket.isEmpty()) {
			logger.info("add to empty list");
			basket.add(findItem(id));
			basket.get(basket.size() - 1).setQuantity(qty);

		} else {
			for (int i = 0; i < basket.size(); i++) {

				if (basket.get(i).getId() == id) {
					logger.info("modify");
					modifyOrder(id, qty + basket.get(i).getQuantity());
					return basket;
				}
			}
			logger.info("adding object ");
			basket.add(findItem(id));
			basket.get(basket.size() - 1).setQuantity(qty);
		}
		return basket;

	}

	/*
	 * Remove specific item from basket
	 * 
	 * @param name - name of item
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> removeFromBasket(String name) {
		if (basket.isEmpty()) {

			return basket;
		} else {
			for (int i = 0; i < basket.size(); i++) {

				if (basket.get(i).getName().equals(name)) {
					logger.info("delete");
					basket.remove(i);
					return basket;
				}
			}

		}
		return basket;
	}

	/*
	 * Remove specific item from basket
	 * 
	 * @param id - id of item
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> removeFromBasket(int id) {
		if (basket.isEmpty()) {

			return basket;
		} else {
			for (int i = 0; i < basket.size(); i++) {

				if (basket.get(i).getId() == id) {
					logger.info("delete");
					basket.remove(i);
					return basket;
				}
			}

		}
		return basket;
	}

	/*
	 * Modifies quantity of specific item in the basket
	 * 
	 * @param name - name of item
	 * 
	 * @param qty - new amount of item
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> modifyOrder(String name, int qty) {
		if (qty == 0) {
			this.removeFromBasket(name);
		} else {
			for (Item item : basket) {
				if (item.getName().equals(name)) {
					item.setQuantity(qty);
				}
			}
		}
		return basket;

	}

	/*
	 * Modifies quantity of specific item in the basket
	 * 
	 * @param id - id of item
	 * 
	 * @param qty - new amount of item
	 * 
	 * @return basket list
	 * 
	 */
	public List<Item> modifyOrder(int id, int qty) {
		if (qty == 0) {
			this.removeFromBasket(id);
		} else {
			for (Item item : basket) {
				if (item.getId() == id) {
					item.setQuantity(qty);
				}
			}
		}
		return basket;

	}

	/*
	 * Searches for specific item
	 * 
	 * @param name - name of item
	 * 
	 * @return item list or null if item not found
	 *
	 */
	public Item findItem(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	/*
	 * Searches for specific item
	 * 
	 * @param id - id of item
	 * 
	 * @return item list or null if item not found
	 *
	 */
	public Item findItem(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	/*
	 * Method calculating cost of added or modified item
	 * 
	 * @param name - name of item
	 * 
	 * @return price - cost of selected item
	 */
	public int itemCost(String name) {
		int price = 0;
		for (Item item : basket) {
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
		return price;

	}

	/*
	 * Method calculating cost of added or modified item
	 * 
	 * @param id - id of item
	 * 
	 * @return price - cost of selected item
	 */
	public int itemCost(int id) {
		int price = 0;
		for (Item item : basket) {
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
		return price;
	}

	/*
	 * Calculates total cost of items from basket
	 * 
	 * @return total - total price of items
	 */
	public int totalCost() {
		int total = 0;
		for (Item item : basket) {
			total += itemCost(item.getName());
		}
		return total;
	}

	/*
	 * Removing all items from basket list
	 */
	public void removeAllFromBasket() {
		basket.clear();

	}

}
