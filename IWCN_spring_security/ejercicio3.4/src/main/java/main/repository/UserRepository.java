package main.repository;

import org.springframework.data.repository.CrudRepository;

import main.model.User;

public interface UserRepository  extends CrudRepository<User, Long>{
	
	User findByUser(String user);

}
