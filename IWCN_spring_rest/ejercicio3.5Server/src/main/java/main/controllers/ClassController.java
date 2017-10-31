package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.model.Product;
import main.services.ProductServiceDB;

@RestController
public class ClassController {
	
	/** Service Element */
	@Autowired
	private ProductServiceDB productService;
	
	/** to add to the database */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody Product product) {
		this.productService.add(product);
	}
	
	/** to return the list of the database */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Iterable<Product> list() {
		return this.productService.findAll();
	}
	
	/** to get from the database */
	@RequestMapping(value = "/show/{numProduct}", method = RequestMethod.GET)
	public Product get(@PathVariable int numProduct) {
		return this.productService.get(numProduct);
	}
	
	/** to delete from the database */
	@RequestMapping(value = "/delete/{numProduct}", method = RequestMethod.GET)
	public void delete(@PathVariable int numProduct) {
		this.productService.remove(numProduct);
	}
}