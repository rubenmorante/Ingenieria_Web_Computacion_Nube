package com.iwcn.main;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import main.model.Product;
import main.repository.ProductRepository;
import main.services.ProductServiceDB;

@RunWith(MockitoJUnitRunner.class)
public class JUnitProductServiceDB {

	//Interface
	@Mock
	private ProductRepository productRepository;
	
	//El Mock que introduces
	@InjectMocks
	private ProductServiceDB productServiceDB;// = new ProductServiceDB();

	private Product product;	
	private ArrayList<Product> productList;// = new ArrayList<Product>();
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);//inicializa cosas de mockito
		
		productServiceDB = new ProductServiceDB();
		productList = new ArrayList<Product>();
		
		this.product = new Product(1, 456, "productOne", "descriptionOne", 12);	
		this.productList.add(product);		

		
	}
	
	@Test
	public void addTest() {
		int size = Arrays.asList(this.productServiceDB.findAll()).size();
		assertEquals(Arrays.asList(this.productServiceDB.findAll()).size(), size);
		
		Product product2 = new Product(2, 789, "productTwo", "descriptionTwo", 23);
		System.out.println(Arrays.asList(this.productServiceDB.findAll()).size() + "----" + size + "----" + this.productList.size());
		
		this.productServiceDB.add(product2);
		verify(this.productRepository, times(1)).save(product2);
		this.productList.add(product2);	

		System.out.println(Arrays.asList(this.productServiceDB.findAll()).size() + "----" + size + "----" + this.productList.size());
		assertFalse(Arrays.asList(this.productServiceDB.findAll()).size() == size);
		
		size++;
		assertEquals(Arrays.asList(this.productServiceDB.findAll()).size(), size);
	}
	
	@Test
	public void removeTest() {
		assertEquals(Arrays.asList(this.productServiceDB.findAll()).size(), 1);
		
		this.productServiceDB.remove(1);
		verify(this.productRepository).delete(1);
		this.productList.remove(0);

		assertEquals(Arrays.asList(this.productServiceDB.findAll()).size(), 0);
	}
	
	@Test
	public void getTest() {
		when(this.productRepository.findAll()).thenReturn(this.productList);
		when(this.productRepository.findOne(1)).thenReturn(this.product); 
		
		Product product1 = this.productServiceDB.get(1);
//		verify(this.productRepository).findOne(1);
		
		assertEquals(product1.getId(), 1);
		assertEquals(product1.getNumCode(), 456);
		assertEquals(product1.getName(), "productOne");
		assertEquals(product1.getDescription(), "descriptionOne");
		assertTrue(product1.getPrice() == 12);				
	}

	@Test
	public void findAllTest() {
		assertEquals(Arrays.asList(this.productServiceDB.findAll()).size(), 1);
		verify(this.productRepository).findAll();		
	}
}
