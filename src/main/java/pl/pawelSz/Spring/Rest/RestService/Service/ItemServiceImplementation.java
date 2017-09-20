package pl.pawelSz.Spring.Rest.RestService.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;

@Service("itemService")
@Transactional
public class ItemServiceImplementation implements ItemService {

	public static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);
	private static List<Item> items;
	private static List<Item> basket = new ArrayList<>();
	static {

		items = itemList();

	}

	private static List<Item> itemList() {
		List<Item> items = new ArrayList<>();
		items.add(new Item.Builder().name("A").id(1).price(40).qtyToDiscount(3).specialPrice(70).build());
		items.add(new Item.Builder().name("B").id(2).price(10).qtyToDiscount(2).specialPrice(15).build());
		items.add(new Item.Builder().name("C").id(3).price(30).qtyToDiscount(4).specialPrice(60).build());
		items.add(new Item.Builder().name("D").id(4).price(25).qtyToDiscount(2).specialPrice(40).build());

		return items;
	}

	public List<Item> showBasket() {
		return basket;
	}

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

	public Item findItem(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public Item findItem(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

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

	public int itemCost(int id) {
		int price = 0;
		for (Item item : basket) {
			if (item.getId() == id) {
				if (item.getQuantity() % item.getQtyToDiscount() == 0) {
					price = item.getSpecialPrice() * (item.getQuantity() / item.getQtyToDiscount());
				} else {
					price = item.getQuantity() * item.getPrice();
				}
			}
		}
		return price;

	}

	public int totalCost() {
		int total = 0;
		for (Item item : basket) {
			total += itemCost(item.getName());
		}
		return total;
	}

	public void removeAllFromBasket() {
		basket.clear();

	}

}
