package pl.adudkiewicz.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.adudkiewicz.model.Register;
import pl.adudkiewicz.model.Wholesaler;

@Repository
public interface RegisterRepository extends CrudRepository<Register, Long>
{
    public Register findByYearAndMonth(Integer year, Integer month);
    public ArrayList<Register> findAll();
}
