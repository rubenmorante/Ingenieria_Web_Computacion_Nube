package main.repository;

import org.springframework.data.repository.CrudRepository;

import main.objects.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
