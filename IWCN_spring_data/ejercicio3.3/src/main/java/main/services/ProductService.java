package main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.objects.Product;
import main.repository.ProductRepository;

@Service
public class ProductService{	
	
	@Autowired
    private ProductRepository productRepository;
	
	public void add(Product product){
		this.productRepository.save(product);
	}
	
	public void remove(int num){
		this.productRepository.delete(num);
	}
	
	public Product get(int num){
		return this.productRepository.findOne(num);
	}

	public Iterable<Product> findAll() {	
		return this.productRepository.findAll();
	}
}
