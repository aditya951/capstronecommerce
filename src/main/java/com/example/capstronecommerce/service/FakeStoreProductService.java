package com.example.capstronecommerce.service;

import com.example.capstronecommerce.model.Product;
import com.example.capstronecommerce.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService {
	
//	@Autowired
    private RestTemplate restTemplate;//=new RestTemplateBuilder().build();

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(Long id) throws JsonProcessingException {
//        Product productDto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + id,
//                Product.class
//        );
        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<String> response = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.GET, entity, String.class);
        System.out.println(response+" aditya");
        Product foo = mapper.readValue(response.getBody(), Product.class);
        return foo;
    }

//    public List<Product> getProducts() {
	public Response getProducts() throws JsonProcessingException {
//        List<Product> productDto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products" ,
//                Product.class
//        );
    	
//    	 ResponseEntity<ArrayList> rateResponse =
//               restTemplate.exchange( "https://fakestoreapi.com/products",
//                       HttpMethod.GET, null,  ArrayList.class);
//       ArrayList<Product> body = rateResponse.getBody();
//	return body;
    	
    	
    	// this is also correct 
//        ResponseEntity<List<Product>> rateResponse =
//                restTemplate.exchange( "https://fakestoreapi.com/products",
//                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
//                        });
//        return rateResponse.getBody();

//        ArrayList<Product> ary=restTemplate.getForObject("https://fakestoreapi.com/products", ArrayList.class);
		String response=restTemplate.getForObject("https://fakestoreapi.com/products", String.class);
		ObjectMapper mapper = new ObjectMapper();
		Product[] foo = mapper.readValue(response, Product[].class);
//		ArrayList<Product> foo = mapper.readValue(response, ArrayList.class);
		List<Product> list= Arrays.asList(foo);
//		Response res=new Response(foo);
		Response res=new Response(list);
        return res;


//		List<Product> list= Arrays.asList(foo);

    }

	public Product addSingleProduct(Product product) {
		HttpEntity<Product> request = new HttpEntity<>(product);
		ResponseEntity<Product> response = restTemplate
				  .exchange("https://fakestoreapi.com/products", HttpMethod.POST, request, Product.class);
		return response.getBody();
	}

	public Product updateSingleProduct(Product product, Long id) {
//		restTemplate.put("https://fakestoreapi.com/products/" + id, product);
		HttpEntity<Product> request = new HttpEntity<>(product);
		ResponseEntity<Product> response = restTemplate
				  .exchange("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, request, Product.class);
		return response.getBody();
//		return null;
	}

	public Product updateSingleProductField(Product product, Long id) {
		HttpEntity<Product> request = new HttpEntity<>(product);
		Product patchForObject = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,request,Product.class);
				 
		return patchForObject;
	}

	public Product deleteSingleProduct(Long id) {
		restTemplate.delete("https://fakestoreapi.com/products/" + id);
		ResponseEntity<Product> response = restTemplate
				  .exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, Product.class);
		return response.getBody();

	}
}
