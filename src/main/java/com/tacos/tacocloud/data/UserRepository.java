package com.tacos.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import com.tacos.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
