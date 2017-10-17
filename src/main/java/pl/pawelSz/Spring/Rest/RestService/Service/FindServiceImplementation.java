package pl.pawelSz.Spring.Rest.RestService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelSz.Spring.Rest.RestService.Model.Item;
import pl.pawelSz.Spring.Rest.RestService.Model.ItemRepository;

/**
 * @author Paweł Szymaszek
 * @version 1.1
 * @since 17.10.2017
 */

@Service("findService")
@Transactional
public class FindServiceImplementation implements ItemFindService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item findItem(String name) {
		for (Item item : itemRepository.findAll()) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Item findItem(long id) {
		if (itemRepository.exists(id)) {
			return itemRepository.findOne(id);
		}
		return null;

	}

}