package main.repository;

import org.springframework.data.repository.CrudRepository;

import main.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
