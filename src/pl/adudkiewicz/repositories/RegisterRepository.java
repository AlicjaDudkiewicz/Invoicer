package pl.adudkiewicz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.adudkiewicz.model.Register;

@Repository
public interface RegisterRepository extends CrudRepository<Register, Long>
{
	public Register findByYearAndMonth(Integer year,Integer month);
}
