package pl.adudkiewicz.repositories;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

import pl.adudkiewicz.model.Wholesaler;

public interface WholesalerRepository extends CrudRepository<Wholesaler, String>
{
    public ArrayList<Wholesaler> findByNipContaining(String nip);
    public ArrayList<Wholesaler> findAll();
}
