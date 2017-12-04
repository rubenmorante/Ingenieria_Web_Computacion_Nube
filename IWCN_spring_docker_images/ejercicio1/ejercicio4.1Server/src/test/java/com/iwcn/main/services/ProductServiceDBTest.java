package com.iwcn.main.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import main.model.Product;
import main.repository.ProductRepository;
import main.services.ProductServiceDB;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceDBTest {	

	private static final int ID = 1;
	private static final int NUMCODE = 456;
	private static final String NAME = "productOne";
	private static final String DESCRIPTION = "descriptionOne";
	private static final double PRICE = 12;

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceDB productServiceDB = new ProductServiceDB();

	private Product product;	
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		
		this.product = new Product();		
		this.product.setId(ProductServiceDBTest.ID);
		this.product.setNumCode(ProductServiceDBTest.NUMCODE);
		this.product.setName(ProductServiceDBTest.NAME);
		this.product.setDescription(ProductServiceDBTest.DESCRIPTION);
		this.product.setPrice(ProductServiceDBTest.PRICE);		
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(this.product);			
		
		when(this.productRepository.findAll()).thenReturn(productList);
		when(this.productRepository.findOne(anyInt())).thenReturn(this.product); 
	}
	
	@Test
	public void productTest() {	
		
		assertNotNull(this.product);
		
		assertTrue(this.product.getClass().getSimpleName().equals("Product"));
		assertTrue(this.product.getId() == ProductServiceDBTest.ID);
		assertTrue(this.product.getNumCode() == ProductServiceDBTest.NUMCODE);
		assertTrue(this.product.getName().equals(ProductServiceDBTest.NAME));
		assertTrue(this.product.getDescription().equals(ProductServiceDBTest.DESCRIPTION));
		assertTrue(this.product.getPrice() == ProductServiceDBTest.PRICE);		
	}
	
	@Test
	public void productListTest() {
		List<Product> productList = Lists.newArrayList(this.productServiceDB.findAll());
		assertNotNull(productList);
		assertTrue(productList.size() > 0);
		
		assertTrue(productList.get(0).getName().equals(ProductServiceDBTest.NAME));
	}
	
	@Test
	public void addTest() {
		List<Product> productList = Lists.newArrayList(this.productServiceDB.findAll());
		when(this.productRepository.findAll()).thenReturn(productList);
		
		int size = productList.size();
		assertEquals(productList.size(), size);
		
		Product product2 = new Product(2, 789, "productTwo", "descriptionTwo", 23);
		
		this.productServiceDB.add(product2);
		productList.add(product2);

		ArgumentCaptor <Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
		verify(this.productRepository, times(1)).save(argumentCaptor.capture());
		
		assertEquals(product2, argumentCaptor.getValue());
		assertFalse(Lists.newArrayList(this.productServiceDB.findAll()).size() == size);
		
		size++;
		assertEquals(Lists.newArrayList(this.productServiceDB.findAll()).size(), size);
	}
	
	@Test
	public void removeTest() {
		List<Product> productList = Lists.newArrayList(this.productServiceDB.findAll());
		when(this.productRepository.findAll()).thenReturn(productList);
		
		int num = 1;
		int size = productList.size();
		
		this.productServiceDB.remove(num);
		productList.remove(0);

		ArgumentCaptor <Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
		verify(this.productRepository).delete(argumentCaptor.capture());
		
		assertTrue(num == argumentCaptor.getValue());

		assertFalse(Lists.newArrayList(this.productServiceDB.findAll()).size() == size);
		
		size--;
		assertEquals(Lists.newArrayList(this.productServiceDB.findAll()).size(), size);
		
	}
	
	@Test
	public void getTest() {		
		int num = 1;
		
		Product product1 = this.productServiceDB.get(num);
		verify(this.productRepository).findOne(num);
		
		assertTrue(product1.getId() == ProductServiceDBTest.ID);
		assertTrue(product1.getNumCode() == ProductServiceDBTest.NUMCODE);
		assertTrue(product1.getName().equals(ProductServiceDBTest.NAME));
		assertTrue(product1.getDescription().equals(ProductServiceDBTest.DESCRIPTION));
		assertTrue(product1.getPrice() == ProductServiceDBTest.PRICE);
	}

	@Test
	public void findAllTest() {		
		assertTrue(Lists.newArrayList(this.productServiceDB.findAll()).size() == 1);
		verify(this.productRepository).findAll();		
	}
}
