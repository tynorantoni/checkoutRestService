package pl.pawelSz.Spring.Rest.RestService.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;
import pl.pawelSz.Spring.Rest.RestService.Model.BasketRepository;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

@Service("crudService")
@Transactional
public class CRUDServiceImplementation implements ItemCRUDService {

	public static final Logger logger = LoggerFactory.getLogger(CRUDServiceImplementation.class);

	@Autowired
	private BasketRepository basketRepository;
	@Autowired
	private CostServiceImplementation costServImpl;
	@Autowired
	private FindServiceImplementation findServImpl;

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
			qty += basketRepository.findOne(idOfObject).getQuantity();
			modifyOrder(findServImpl.findItem(name).getId(), qty);
			logger.info("modify z elza");

		} else {
			logger.info("add to empty list");
			basketRepository.save(new Basket(qty, costServImpl.itemCost(name, qty), findServImpl.findItem(name)));
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
			qty += basketRepository.findOne(idOfObject).getQuantity();
			modifyOrder(findServImpl.findItem(id).getId(), qty);
			logger.info("modify z elza");

		} else {
			logger.info("add to empty list");
			basketRepository.save(new Basket(qty, costServImpl.itemCost(id, qty), findServImpl.findItem(id)));
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
		basketRepository.findOne(idOfObjectToModify).setCost(costServImpl.itemCost(id, qty));

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
		basketRepository.findOne(idOfObjectToModify).setCost(costServImpl.itemCost(name, qty));

		return showBasket();
	}

	public void removeAllFromBasket() {
		basketRepository.deleteAll();
	}

}
