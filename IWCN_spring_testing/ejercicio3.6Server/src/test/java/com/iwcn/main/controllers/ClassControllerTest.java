package com.iwcn.main.controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import main.controllers.ClassController;
import main.model.Product;
import main.services.ProductServiceDB;

@RunWith(SpringRunner.class)
public class ClassControllerTest {

	private static final int ID = 1;
	private static final int NUMCODE = 456;
	private static final String NAME = "productOne";
	private static final String DESCRIPTION = "descriptionOne";
	private static final double PRICE = 12;
	
	@Mock
	private ProductServiceDB productService;
	
	@InjectMocks
	private ClassController classController;

	private MockMvc mockMvc;

	private Product product;	
	
	@Before
	public void init(){
		this.classController = new ClassController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.classController).build();
		
		this.product = new Product();		
		this.product.setId(ClassControllerTest.ID);
		this.product.setNumCode(ClassControllerTest.NUMCODE);
		this.product.setName(ClassControllerTest.NAME);
		this.product.setDescription(ClassControllerTest.DESCRIPTION);
		this.product.setPrice(ClassControllerTest.PRICE);		
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(this.product);		
		
		when(this.productService.findAll()).then(answer ->{return productList;});
		when(this.productService.get(anyInt())).then(answer ->{return this.product;});
	}
	
	/** to add to the database */
	@Test
	public void addTest() throws Exception{
		String body = "{\"id\":\""+ClassControllerTest.ID+"\",\"numCode\":\""+ClassControllerTest.NUMCODE+"\",\"name\":\""+ClassControllerTest.NAME+"\",\"description\":\""+ClassControllerTest.DESCRIPTION+"\",\"price\":\""+ClassControllerTest.PRICE+"\"}";
		this.mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(body));
		
		ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
		verify(this.productService, times(1)).add(argumentCaptor.capture());
		
		assertTrue(argumentCaptor.getValue().getName().equals(ClassControllerTest.NAME));
	}
	
	/** to return the list of the database */
	@Test
	public void listTest() throws Exception {
		this.mockMvc.perform(get("/list").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.[0].name").value(ClassControllerTest.NAME)).andExpect(status().isOk());
		
		verify(this.productService, times(1)).findAll();
	}
	
	/** to get from the database */
	@Test
	public void getTest() throws Exception {		
		this.mockMvc.perform(get("/show/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(jsonPath("$.name").value(ClassControllerTest.NAME));
		
		verify(this.productService, times(1)).get(ClassControllerTest.ID);
	}
	
	/** to delete from the database */
	@Test
	public void deleteTest() throws Exception {
		this.mockMvc.perform(get("/delete/1"));
		verify(this.productService, times(1)).remove(ClassControllerTest.ID);
	}

}
