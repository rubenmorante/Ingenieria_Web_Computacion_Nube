package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import objects.Product;
import repository.ProductRepository;

@Service
public class ProductService{	
	
	@Autowired
    private ProductRepository productRepository;//<---Si aqui le pongo autowired me dice que tengo que hacer un
    //bean, no puedo hacer un bean de una inteface
	
	public void add(Product product){
		System.out.println("ProductService" + product.getNumCode());
		System.out.println(product.getName());
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
