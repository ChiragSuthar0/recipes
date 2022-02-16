package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import recipes.businesslayer.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}