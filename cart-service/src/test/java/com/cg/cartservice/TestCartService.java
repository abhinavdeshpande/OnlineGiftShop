package com.cg.cartservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cartservice.dto.ItemDto;
import com.cg.cartservice.entities.Cart;
import com.cg.cartservice.services.CartService;

@SpringBootTest
class TestCartService {
	
	@Autowired
	CartService cartService;
	
	@Test
	void testFetchCartById() {
		System.out.println(cartService.toString());
		assertNotNull(cartService.fetchByUserId(100001L));
	}
	
	@Test
	void testAddToCart() {
		System.out.println(cartService.toString());
		assertEquals(cartService.fetchByUserId(100001L), cartService.addToCart(new ItemDto(100001L, 2), 100001L));
	}
	
	@Test
	void testDeleteFromCart() {
		Cart cart = cartService.deleteFromCart(100001L, 100001L);
		System.out.println("Inside testDeleteFromCart"+cart);
		assertEquals(cartService.fetchByUserId(100001L), cart);
	}

}
