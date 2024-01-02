package com.example.capstronecommerce.service;

import com.example.capstronecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService {
    private final RestTemplate restTemplate=new RestTemplateBuilder().build();

//    @Autowired
//    public FakeStoreProductService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public Product getSingleProduct(Long id) {
        Product productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                Product.class
        );

        return productDto;
    }

    public List<Product> getProducts() {
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

        ArrayList<Product> ary=restTemplate.getForObject("https://fakestoreapi.com/products", ArrayList.class);
        return ary;
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
