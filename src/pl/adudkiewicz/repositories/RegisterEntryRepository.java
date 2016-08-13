package pl.adudkiewicz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.adudkiewicz.model.RegisterEntry;

@Repository
public interface RegisterEntryRepository extends CrudRepository<RegisterEntry, Long>
{
	
	
}
