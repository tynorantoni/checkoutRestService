package pl.pawelSz.Spring.Rest.RestService.Service;

import pl.pawelSz.Spring.Rest.RestService.Model.Basket;
import pl.pawelSz.Spring.Rest.RestService.Model.Item;

/**
 *
 * @author Paweł Szymaszek
 * @version 1.0
 * @since 21.09.2017
 *
 */

public interface ItemService {

	Iterable<Basket> showBasket();

	Iterable<Basket> addToBasket(String name, int qty);

	Iterable<Basket> addToBasket(long id, int qty);

	Iterable<Basket> removeFromBasket(long id);
	
	Iterable<Basket> removeFromBasket(String name);

	Iterable<Basket> modifyOrder(long id, int qty);
	
	Iterable<Basket> modifyOrder(String name, int qty);

	Item findItem(String name);

	Item findItem(long id);

	int itemCost(String name,int qty);

	int itemCost(long id,int qty);

	int totalCost();

	void removeAllFromBasket();

}
