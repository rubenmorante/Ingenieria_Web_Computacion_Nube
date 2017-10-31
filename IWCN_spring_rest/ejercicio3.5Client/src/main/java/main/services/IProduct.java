package main.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import feign.RequestLine;
import main.model.Product;

/** defines what the server controller returns */
public interface IProduct {

	/** to add to the database */
	@RequestLine("POST")
	@Headers("Content-Type: application/json")
	public void add(@RequestBody Product product);

	/** to return the list of the database */
	@RequestLine("GET")
	public List<Product> list();
	
	/** to get from the database */
	@RequestLine("GET")
	public Product get();
	
	/** to delete from the database */
	@RequestLine("GET")
	public void delete();
}
