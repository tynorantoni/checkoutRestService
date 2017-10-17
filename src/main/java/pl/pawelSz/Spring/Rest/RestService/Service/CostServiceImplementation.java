package pl.pawelSz.Spring.Rest.RestService.Service;

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

@Service("costService")
@Transactional
public class CostServiceImplementation implements ItemCostService{

	@Autowired
	private BasketRepository basketRepository;
	@Autowired
	private FindServiceImplementation findServImpl;
	
	@Override
	public int itemCost(String name, int qty) {
		int price = 0;
		int modulo = qty % findServImpl.findItem(name).getQtyToDiscount();

		price = findServImpl.findItem(name).getPrice() * modulo
				+ findServImpl.findItem(name).getSpecialPrice() * ((qty - modulo) / findServImpl.findItem(name).getQtyToDiscount());
		
		return price;

	}

	@Override
	public int itemCost(long id, int qty) {
		int price = 0;
		int modulo = qty % findServImpl.findItem(id).getQtyToDiscount();

		price = findServImpl.findItem(id).getPrice() * modulo
				+ findServImpl.findItem(id).getSpecialPrice() * ((qty - modulo) / findServImpl.findItem(id).getQtyToDiscount());
		return price;
	}

	@Override
	public int totalCost() {
		int total = 0;
		for (Basket basket : basketRepository.findAll()) {
			total += basket.getCost();
		}
		return total;
	}

}
