/*
 * package com.revature.revshop;
 * 
 * import static org.mockito.ArgumentMatchers.any; import static
 * org.mockito.Mockito.when; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import
 * static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 * 
 * import java.util.Arrays; import java.util.List;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.MockitoAnnotations; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.setup.MockMvcBuilders;
 * 
 * import com.fasterxml.jackson.databind.ObjectMapper; import
 * com.revature.revshop.controller.InventoryController; import
 * com.revature.revshop.dto.InventoryRequest; import
 * com.revature.revshop.dto.InventoryResponse; import
 * com.revature.revshop.dto.OrderLineItemsDto; import
 * com.revature.revshop.model.Status; import
 * com.revature.revshop.service.InventoryService;
 * 
 * public class InventoryMockMVC {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Mock private InventoryService inventoryService;
 * 
 * @InjectMocks private InventoryController inventoryController;
 * 
 * @BeforeEach public void setUp() { MockitoAnnotations.openMocks(this); mockMvc
 * = MockMvcBuilders.standaloneSetup(inventoryController).build(); }
 * 
 * @Test public void testCreateInventoryEntry() throws Exception { // Given
 * InventoryRequest request = InventoryRequest.builder() .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * InventoryResponse response = InventoryResponse.builder() .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .build();
 * 
 * when(inventoryService.createInventoryEntry(any(InventoryRequest.class))).
 * thenReturn(response);
 * 
 * // When and Then mockMvc.perform(post("/api/inventory/")
 * .contentType(MediaType.APPLICATION_JSON) .content(new
 * ObjectMapper().writeValueAsString(request))) .andExpect(status().isCreated())
 * .andExpect(jsonPath("$.skuCode").value("SKU123"))
 * .andExpect(jsonPath("$.name").value("Test Product"))
 * .andExpect(jsonPath("$.quantity").value(10)); }
 * 
 * 
 * 
 * @Test public void testUpdateInventory() throws Exception { // Given
 * InventoryRequest request = InventoryRequest.builder() .skuCode("SKU123")
 * .name("Updated Product") .quantity(15) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * InventoryResponse response = InventoryResponse.builder() .skuCode("SKU123")
 * .name("Updated Product") .quantity(15) .build();
 * 
 * when(inventoryService.updateInventory(any(Long.class),
 * any(InventoryRequest.class))).thenReturn(response);
 * 
 * // When and Then mockMvc.perform(put("/api/inventory/update") .param("id",
 * "1") .contentType(MediaType.APPLICATION_JSON) .content(new
 * ObjectMapper().writeValueAsString(request))) .andExpect(status().isOk())
 * .andExpect(jsonPath("$.skuCode").value("SKU123"))
 * .andExpect(jsonPath("$.name").value("Updated Product"))
 * .andExpect(jsonPath("$.quantity").value(15)); }
 * 
 * @Test public void testDeleteInventory() throws Exception { // Given
 * when(inventoryService.deleteInventory(1L)).
 * thenReturn("Inventory item deleted successfully");
 * 
 * // When and Then mockMvc.perform(delete("/api/inventory/1"))
 * .andExpect(status().isOk())
 * .andExpect(content().string("Inventory item deleted successfully")); }
 * 
 * @Test public void testGetInventoryById() throws Exception { // Given
 * InventoryResponse response = InventoryResponse.builder() .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .build();
 * 
 * when(inventoryService.getInventoryById(1L)).thenReturn(response);
 * 
 * // When and Then mockMvc.perform(get("/api/inventory/1"))
 * .andExpect(status().isOk()) .andExpect(jsonPath("$.skuCode").value("SKU123"))
 * .andExpect(jsonPath("$.name").value("Test Product"))
 * .andExpect(jsonPath("$.quantity").value(10)); }
 * 
 * @Test public void testGetAllInventoryEntries() throws Exception { // Given
 * List<InventoryResponse> responseList = Arrays.asList(
 * InventoryResponse.builder().skuCode("SKU123").name("Product 1").quantity(10).
 * build(),
 * InventoryResponse.builder().skuCode("SKU124").name("Product 2").quantity(20).
 * build() );
 * 
 * when(inventoryService.getAllInventoryEntries()).thenReturn(responseList);
 * 
 * // When and Then mockMvc.perform(get("/api/inventory/all"))
 * .andExpect(status().isOk())
 * .andExpect(jsonPath("$[0].skuCode").value("SKU123"))
 * .andExpect(jsonPath("$[0].name").value("Product 1"))
 * .andExpect(jsonPath("$[0].quantity").value(10))
 * .andExpect(jsonPath("$[1].skuCode").value("SKU124"))
 * .andExpect(jsonPath("$[1].name").value("Product 2"))
 * .andExpect(jsonPath("$[1].quantity").value(20)); }
 * 
 * @Test public void testGetInventoryByUserId() throws Exception { // Given
 * InventoryResponse response = InventoryResponse.builder() .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .build();
 * 
 * when(inventoryService.getInventoryByUserId("user1")).thenReturn(response);
 * 
 * // When and Then mockMvc.perform(get("/api/inventory/user") .param("userId",
 * "user1")) .andExpect(status().isOk())
 * .andExpect(jsonPath("$.skuCode").value("SKU123"))
 * .andExpect(jsonPath("$.name").value("Test Product"))
 * .andExpect(jsonPath("$.quantity").value(10)); } }
 */