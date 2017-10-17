package pl.pawelSz.Spring.Rest.RestService.Model;

/**
* @author Paweł Szymaszek
* @version 1.1
* @since 17.10.2017
*/

import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository<Basket, Long> {

}
