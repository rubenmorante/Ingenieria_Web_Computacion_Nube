package main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Product;
import main.repository.ProductRepository;

@Service
public class ProductServiceDB implements ProductService<Product>{	
	
	@Autowired
    private ProductRepository productRepository;
	
	@Override
	public void add(Product product){
		this.productRepository.save(product);
	}
	
	@Override
	public void remove(int num){
		this.productRepository.delete(num);
	}
	
	@Override
	public Product get(int num){
		return this.productRepository.findOne(num);
	}

	@Override
	public Iterable<Product> findAll() {	
		return this.productRepository.findAll();
	}
}
