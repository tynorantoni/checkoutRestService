package pl.pawelSz.Spring.Rest.RestService.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.spi.LocationAwareLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;

@Service("itemService")
@Transactional
public class ItemServiceImplementation implements ItemService {

	private static List<Item> items;
	private static List<Item> basket = new ArrayList<>();
	static {

		items = itemList();

	}

	private static List<Item> itemList() {
		List<Item> items = new ArrayList<>();
		items.add(new Item.Builder().name("A").);
		items.add(new Item(2, "B", 10, 15, 2, 0, 0));
		items.add(new Item(3, "C", 30, 60, 4, 0, 0));
		items.add(new Item(4, "D", 25, 40, 2, 0, 0));
		return items;
	}

	public List<Item> showBasket() {
		return basket;
	}

	public List<Item> addToBasket(String name, int qty) {
		if(!basket.isEmpty()){
		for (Item item : basket) {
			if(item.getName().equals(name)){
				System.out.println("raz");
				this.modifyOrder(name, qty);
			}
		}
		}else{
				System.out.println("dwa");
				basket.add(findItem(name));
				basket.get(basket.size() - 1).setQuantity(qty);
			}
		return basket;
	}

	public List<Item> addToBasket(int id, int qty) {
		if(!basket.isEmpty()){
			for (Item item : basket) {
				if(item.getId()==id){
					System.out.println("raz");
					this.modifyOrder(id, qty);
				}
			}
			}else{
					System.out.println("dwa");
					basket.add(findItem(id));
					basket.get(basket.size() - 1).setQuantity(qty);
				}
			return basket;
	}

	public List<Item> removeFromBasket(String name) {
		if (!basket.contains(name)) {

			return basket;
		} else {
			for (Item item : basket) {

				if (item.getName().equals(name)) {
					basket.remove(item);
				}
			}
		}
		return basket;

	}

	public List<Item> removeFromBasket(int id) {
		if (!basket.contains(id)) {

			return basket;
		} else {
			for (Item item : basket) {

				if (item.getId() == id) {
					basket.remove(item);
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
