package pl.pawelSz.Spring.Rest.RestService.Service;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

public interface ItemCostService {

	int itemCost(String name, int qty);

	int itemCost(long id, int qty);

	int totalCost();

}
