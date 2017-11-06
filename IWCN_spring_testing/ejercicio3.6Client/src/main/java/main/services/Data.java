package main.services;

import java.util.Collection;

import main.model.Product;

public interface Data<T> {

	/** ask the server to add a product */
	public void add(T element);
	
	/** ask the server to remove a product */
	public void remove(int num);
	
	/** ask the server to get a product */
	public Product get(int num);

	/** ask the server to get the list of all products */
	public Collection<T> findAll();
}
