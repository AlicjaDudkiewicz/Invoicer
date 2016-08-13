package pl.adudkiewicz.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.Wholesaler;

public interface WholesalersRepository extends CrudRepository<Wholesaler, Long>
{
	
}
