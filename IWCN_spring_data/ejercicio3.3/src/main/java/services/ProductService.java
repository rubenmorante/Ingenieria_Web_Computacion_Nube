package services;

import org.springframework.stereotype.Service;

import objects.Product;
import repository.ProductRepository;

@Service
public class ProductService{	
	
    private ProductRepository productRepository;//<---Si aqui le pongo autowired me dice que tengo que hacer un
    //bean, no puedo hacer un bean de una inteface
	
	public void add(Product product){
		System.out.println(product.getNumCode());
		System.out.println(product.getName());//<-----------------------------hasta aqui funciona
		this.productRepository.save(product);//<----------------------------- NullPointerException
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
