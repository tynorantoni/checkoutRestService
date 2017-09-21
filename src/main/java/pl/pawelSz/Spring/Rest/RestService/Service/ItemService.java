package pl.pawelSz.Spring.Rest.RestService.Service;

import java.util.List;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;

/**
 *
 * @author Paweł Szymaszek
 * @version 1.0
 * @since 21.09.2017
 *
 */

public interface ItemService {

	List<Item> showBasket();

	List<Item> addToBasket(String name, int qty);

	List<Item> addToBasket(int id, int qty);

	List<Item> removeFromBasket(String name);

	List<Item> removeFromBasket(int id);

	List<Item> modifyOrder(String name, int qty);

	List<Item> modifyOrder(int id, int qty);

	Item findItem(String name);

	Item findItem(int id);

	int itemCost(String name);

	int itemCost(int id);

	int totalCost();

	void removeAllFromBasket();

}
