package pl.adudkiewicz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.adudkiewicz.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>
{

}
