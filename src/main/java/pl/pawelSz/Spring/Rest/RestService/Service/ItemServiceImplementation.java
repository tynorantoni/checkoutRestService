package pl.pawelSz.Spring.Rest.RestService.Service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;
import pl.pawelSz.Spring.Rest.RestService.Model.BasketRepository;
import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Model.ItemRepository;

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

	@Autowired
	private BasketRepository basketRepository;
	@Autowired
	private ItemRepository itemRepository;

	/*
	 * Shows basket - list created for item storage
	 * 
	 * @return basket list
	 * 
	 */
	public Iterable<Basket> showBasket() {
		return basketRepository.findAll();
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
	public Iterable<Basket> addToBasket(String name, int qty) {
		long idO = 0;
		if (!basketRepository.exists(itemRepository.findOne(findItem(name).getId()).getId())) {
			logger.info("add to empty list");
			basketRepository.save(new Basket(idO, qty, itemCost(name, qty), findItem(name)));
			logger.info("adding ");
		}
		
		modifyOrder(findItem(name).getId(), qty);

		logger.info("modify");

		return showBasket();

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
	public Iterable<Basket> addToBasket(long id, int qty) {
		long idO = 0;
		if (!basketRepository.exists(id)) {
			logger.info("add to empty list");
			basketRepository.save(new Basket(idO, qty, itemCost(id, qty), findItem(id)));

		}
		modifyOrder(id, qty);

		logger.info("modify");

		return showBasket();

	}

	/*
	 * Remove specific item from basket
	 * 
	 * @param id - id of item
	 * 
	 * @return basket list
	 * 
	 */
	public Iterable<Basket> removeFromBasket(long id) {
		if (basketRepository.count() == 0) {

			return showBasket();
		}
		basketRepository.delete(id);

		logger.info("delete");

		return showBasket();
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
	public Iterable<Basket> modifyOrder(long id, int qty) {
		if (qty == 0) {
			removeFromBasket(id);
		}
		basketRepository.findOne(id).setQuantity(qty);

		return showBasket();

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

		for (Item item : itemRepository.findAll()) {
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
	public Item findItem(long id) {
		if (itemRepository.exists(id)) {
			return itemRepository.findOne(id);
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
	public int itemCost(String name, int qty) {

		int price = 0;
		findItem(name);
		price = findItem(name).getSpecialPrice() * (qty / findItem(name).getQtyToDiscount())
				+ findItem(name).getPrice() * (qty % findItem(name).getQtyToDiscount());

		return price;

	}

	/*
	 * Method calculating cost of added or modified item
	 * 
	 * @param id - id of item
	 * 
	 * @return price - cost of selected item
	 */
	public int itemCost(long id, int qty) {

		int price = 0;
		findItem(id);
		price = findItem(id).getSpecialPrice() * (qty / findItem(id).getQtyToDiscount())
				+ findItem(id).getPrice() * (qty % findItem(id).getQtyToDiscount());

		return price;
	}

	/*
	 * Calculates total cost of items from basket
	 * 
	 * @return total - total price of items
	 */
	public int totalCost() {
		int total = 0;
		for (Basket basket : basketRepository.findAll()) {
			total += basket.getCost();
		}
		return total;
	}

	/*
	 * Removing all items from basket list
	 */
	public void removeAllFromBasket() {
		basketRepository.deleteAll();

	}

}
