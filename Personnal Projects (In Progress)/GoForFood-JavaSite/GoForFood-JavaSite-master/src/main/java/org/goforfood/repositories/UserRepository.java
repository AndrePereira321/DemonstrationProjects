package org.goforfood.repositories;

import org.goforfood.models.auth.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{
    
}
