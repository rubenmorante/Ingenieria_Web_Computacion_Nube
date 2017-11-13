package main.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import main.model.Product;

@Service
public class DataService implements Data<Product>{

	/** Override ask the server to add a product */
	@Override
	public void add(Product product) {
		IProduct iProduct = Feign.builder()
			.client(new OkHttpClient())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.target(IProduct.class, "http://localhost:8080/add");
		iProduct.add(product);		
	}

	/** Override ask the server to remove a product */
	@Override
	public void remove(int num) {
		IProduct iProduct = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(IProduct.class, "http://localhost:8080/delete/" + num);
		iProduct.delete();		
	}

	/** Override ask the server to get a product */
	@Override
	public Product get(int num) {
		IProduct iProduct = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(IProduct.class, "http://localhost:8080/show/" + num);
		return iProduct.get();	
	}

	/** Override ask the server to get the list of all products */
	@Override
	public Collection<Product> findAll() {
		IProduct iProduct = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(IProduct.class, "http://localhost:8080/list");
		return iProduct.list();
	}

}
