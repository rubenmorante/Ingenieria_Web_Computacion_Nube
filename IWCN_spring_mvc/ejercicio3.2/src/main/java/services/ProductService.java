package services;

import java.util.LinkedList;

import org.springframework.stereotype.Service;

import objects.Product;

@Service
public class ProductService {

    private LinkedList<Product> list;

	public ProductService() {	    
		this.list = new LinkedList<>();
	}
	
	public void add(Product product){
		this.list.add(product);
	}
	
	public void remove(int num){
		this.list.remove(num);
	}
	
	public Product get(int num){
		return this.list.get(num);
	}

	public LinkedList<Product> getList() {
		return this.list;
	}

	public void setList(LinkedList<Product> list) {
		this.list = list;
	}
}
