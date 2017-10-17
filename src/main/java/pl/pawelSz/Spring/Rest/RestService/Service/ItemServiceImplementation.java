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
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

@Service("itemService")
@Transactional
public class ItemServiceImplementation implements ItemCRUDService, ItemCostService, ItemFindService {

	public static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);

	@Autowired
	private BasketRepository basketRepository;
	@Autowired
	private ItemRepository itemRepository;

	public Iterable<Basket> showBasket() {

		return basketRepository.findAll();
	}

	public Iterable<Basket> addToBasket(String name, int qty) {
		long idOfObject = 0;

		for (Basket basket : showBasket()) {
			if (basket.getItems().getName().equals(name)) {
				idOfObject = basket.getOrderId();
			}
		}
		if (basketRepository.exists(idOfObject)) {
			qty+=basketRepository.findOne(idOfObject).getQuantity();
			modifyOrder(findItem(name).getId(), qty );
			logger.info("modify z elza");

		}else{
		logger.info("add to empty list");
		basketRepository.save(new Basket(qty, itemCost(name, qty), findItem(name)));
		logger.info("adding");
		}
		return showBasket();

	}

	public Iterable<Basket> addToBasket(long id, int qty) {
		long idOfObject = 0;

		for (Basket basket : showBasket()) {
			if (basket.getItems().getId() == id) {
				idOfObject = basket.getOrderId();
			}
		}
		if (basketRepository.exists(idOfObject)) {
			qty+=basketRepository.findOne(idOfObject).getQuantity();
			modifyOrder(findItem(id).getId(), qty );
			logger.info("modify z elza");

		}else{
		logger.info("add to empty list");
		basketRepository.save(new Basket(qty, itemCost(id, qty), findItem(id)));
		logger.info("adding");
		}
		return showBasket();

	}

	public Iterable<Basket> removeFromBasket(long id) {
		long idOfObjectToDelete = 0;
		for (Basket basket : showBasket()) {
			if (basket.getItems().getId() == id) {
				idOfObjectToDelete = basket.getOrderId();
			}
		}
		if (basketRepository.count() == 0 || basketRepository.exists(idOfObjectToDelete) == false) {

			return showBasket();
		}

		basketRepository.delete(idOfObjectToDelete);
		logger.info("delete");

		return showBasket();
	}

	public Iterable<Basket> removeFromBasket(String name) {
		long idOfObjectToDelete = 0;
		for (Basket basket : showBasket()) {
			if (basket.getItems().getName().equals(name)) {
				idOfObjectToDelete = basket.getOrderId();
			}
		}
		if (basketRepository.count() == 0 || basketRepository.exists(idOfObjectToDelete) == false) {

			return showBasket();
		}

		basketRepository.delete(idOfObjectToDelete);
		logger.info("delete");

		return showBasket();
	}

	public Iterable<Basket> modifyOrder(long id, int qty) {
		long idOfObjectToModify = 0;

		for (Basket basket : showBasket()) {
			if (basket.getItems().getId() == id) {
				idOfObjectToModify = basket.getOrderId();
			}
		}
		if (basketRepository.count() == 0 || basketRepository.exists(idOfObjectToModify) == false) {

			return showBasket();
		}
		if (qty == 0) {
			removeFromBasket(id);
		}
		
		basketRepository.findOne(idOfObjectToModify).setQuantity(qty);
		basketRepository.findOne(idOfObjectToModify).setCost(itemCost(id, qty));

		return showBasket();
	}

	public Iterable<Basket> modifyOrder(String name, int qty) {
		long idOfObjectToModify = 0;
		for (Basket basket : showBasket()) {
			if (basket.getItems().getName().equals(name)) {
				idOfObjectToModify = basket.getOrderId();
			}
		}
		if (basketRepository.count() == 0 || basketRepository.exists(idOfObjectToModify) == false) {

			return showBasket();
		}
		if (qty == 0) {
			removeFromBasket(name);
		}

		basketRepository.findOne(idOfObjectToModify).setQuantity(qty);
		basketRepository.findOne(idOfObjectToModify).setCost(itemCost(name, qty));

		return showBasket();
	}

	public Item findItem(String name) {

		for (Item item : itemRepository.findAll()) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public Item findItem(long id) {
		if (itemRepository.exists(id)) {
			return itemRepository.findOne(id);
		}
		return null;
	}

	public int itemCost(String name, int qty) {

		int price = 0;
		int modulo = qty % findItem(name).getQtyToDiscount();

		price = findItem(name).getPrice() * modulo
				+ findItem(name).getSpecialPrice() * ((qty - modulo) / findItem(name).getQtyToDiscount());
		
		return price;

	}

	public int itemCost(long id, int qty) {

		int price = 0;
		int modulo = qty % findItem(id).getQtyToDiscount();

		price = findItem(id).getPrice() * modulo
				+ findItem(id).getSpecialPrice() * ((qty - modulo) / findItem(id).getQtyToDiscount());
		return price;
	}

	public int totalCost() {
		int total = 0;
		for (Basket basket : basketRepository.findAll()) {
			total += basket.getCost();
		}
		return total;
	}

	public void removeAllFromBasket() {
		basketRepository.deleteAll();
	}

}
