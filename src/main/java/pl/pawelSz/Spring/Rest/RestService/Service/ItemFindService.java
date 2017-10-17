package pl.pawelSz.Spring.Rest.RestService.Service;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

import pl.pawelSz.Spring.Rest.RestService.Model.Item;

public interface ItemFindService {

	Item findItem(String name);

	Item findItem(long id);

}
