package main.services;

import main.model.Product;

public interface ProductService<T> {

	public void add(T element);
	
	public void remove(int num);
	
	public Product get(int num);

	public Iterable<T> findAll();
}
