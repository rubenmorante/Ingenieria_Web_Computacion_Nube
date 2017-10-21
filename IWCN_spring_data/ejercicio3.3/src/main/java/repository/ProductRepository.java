package repository;

import org.springframework.data.repository.CrudRepository;

import objects.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
