package com.cg.cartservice.testController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import com.cg.authservice.dto.ForgotPasswordRequest;
import com.cg.cartservice.dto.CartDto;
import com.cg.cartservice.dto.ItemDto;
import com.cg.cartservice.dto.OrderDto;
import com.cg.cartservice.entities.ProductInOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CartControllerTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	static {
		System.setProperty("spring.profiles.active", "test");
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testFetchCartByIdSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/cart/100001").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testFetchCartByIdFailure() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/cart/100010").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testFetchCartByUserIdSuccess() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/cart/user/100001").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testFetchCartByUserIdFailure() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/cart/user/100010").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	void testDeleteFromCartSuccess() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/cart/delete/100001/100001").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	void testDeleteFromCartFailure() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/cart/delete/100003/100001").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	
	@Test
	void testAddToCartSuccess() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

//				  List<ItemDto> list=new ArrayList<ItemDto>();
//				  list.add(new ItemDto(100002L, 4));

		ItemDto item = new ItemDto(100002L, 4);

		// CartDto cart = new CartDto(list);

		mockMvc.perform(MockMvcRequestBuilders.put("/cart/100001").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(item))).andExpect(status().isOk());

//				  mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/100001/100001").contentType(MediaType.APPLICATION_JSON))
//			        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	void testAddToCartFailure() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

//				  List<ItemDto> list=new ArrayList<ItemDto>();
//				  list.add(new ItemDto(100002L, 4));

		ItemDto item = new ItemDto(100002L, 4);

		// CartDto cart = new CartDto(list);

		mockMvc.perform(MockMvcRequestBuilders.put("/cart/100015").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(item))).andExpect(status().isBadRequest());

//				  mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/100001/100001").contentType(MediaType.APPLICATION_JSON))
//			        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

//	@Test
//	void testCheckOutSuccess() throws Exception {
//
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/cart/checkout/100001").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//	}
	
	@Test
	void testCheckOutFailure() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cart/checkout/100003").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	void testManualCheckOutSuccess() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		OrderDto dto=new OrderDto("411030","pune","maharashtra","kothrud",new BigDecimal(500),100001L);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cart/manualcheckout/100001").contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsString(dto))).andExpect(status().isOk());

	}
	
	@Test
	void testManualCheckOutFailure() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		OrderDto dto=new OrderDto("411030","pune","maharashtra","kothrud",new BigDecimal(500),100001L);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cart/manualcheckout/100007").contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsString(dto))).andExpect(status().isBadRequest());

	}
	
	
	@Test
	void testMergeCartSuccess() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

				  

		ItemDto item = new ItemDto(100002L, 4);
		List<ItemDto> list=new ArrayList<ItemDto>();
		  list.add(new ItemDto(100002L, 4));

		 CartDto cart = new CartDto(list);

		mockMvc.perform(MockMvcRequestBuilders.post("/cart/merge/100001").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(cart))).andExpect(status().isOk());

//				  mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/100001/100001").contentType(MediaType.APPLICATION_JSON))
//			        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	void testMergeCartFailure() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

				  

		ItemDto item = new ItemDto(100002L, 4);
		List<ItemDto> list=new ArrayList<ItemDto>();
		  list.add(new ItemDto(100002L, 4));

		 CartDto cart = new CartDto(list);

		mockMvc.perform(MockMvcRequestBuilders.post("/cart/merge/100010").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(cart))).andExpect(status().isBadRequest());

//				  mockMvc.perform(MockMvcRequestBuilders.delete("/cart/delete/100001/100001").contentType(MediaType.APPLICATION_JSON))
//			        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	

}