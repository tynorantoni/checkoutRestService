package pl.pawelSz.Spring.Rest.RestService.Service;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

public interface ItemCRUDService {

	Iterable<Basket> showBasket();

	Iterable<Basket> addToBasket(String name, int qty);

	Iterable<Basket> addToBasket(long id, int qty);

	Iterable<Basket> removeFromBasket(long id);

	Iterable<Basket> removeFromBasket(String name);

	Iterable<Basket> modifyOrder(long id, int qty);

	Iterable<Basket> modifyOrder(String name, int qty);

	void removeAllFromBasket();

}
