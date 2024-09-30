/*
 * package com.revature.revshop;
 * 
 * import static org.junit.jupiter.api.Assertions.*; import static
 * org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.*;
 * 
 * import java.util.Arrays; import java.util.List; import java.util.Optional;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.mockito.MockitoAnnotations;
 * 
 * import com.revature.revshop.dto.InventoryRequest; import
 * com.revature.revshop.dto.InventoryResponse; import
 * com.revature.revshop.dto.OrderLineItemsDto; import
 * com.revature.revshop.model.Inventory; import
 * com.revature.revshop.model.Status; import
 * com.revature.revshop.repository.InventoryRepository; import
 * com.revature.revshop.service.InventoryService;
 * 
 * public class InventoryServiceApplicationTests {
 * 
 * @Mock private InventoryRepository inventoryRepository;
 * 
 * @InjectMocks private InventoryService inventoryService;
 * 
 * @BeforeEach void setUp() { MockitoAnnotations.openMocks(this); }
 * 
 * @Test public void testCreateInventoryEntry() { // Given InventoryRequest
 * inventoryRequest = InventoryRequest.builder() .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * Inventory savedInventory = Inventory.builder() .id(1L) .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * // When when(inventoryRepository.save(any(Inventory.class))).thenReturn(
 * savedInventory); InventoryResponse inventoryResponse =
 * inventoryService.createInventoryEntry(inventoryRequest);
 * 
 * // Then assertNotNull(inventoryResponse); assertEquals("Test Product",
 * inventoryResponse.getName()); assertEquals("SKU123",
 * inventoryResponse.getSkuCode()); assertEquals(10,
 * inventoryResponse.getQuantity()); }
 * 
 * @Test public void testUpdateInventory() { // Given InventoryRequest
 * inventoryRequest = InventoryRequest.builder() .skuCode("SKU123")
 * .name("Updated Product") .quantity(15) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * Inventory existingInventory = Inventory.builder() .id(1L) .skuCode("SKU123")
 * .name("Old Product") .quantity(5) .userId("user1") .status(Status.AVAILABLE)
 * .build();
 * 
 * Inventory updatedInventory = Inventory.builder() .id(1L) .skuCode("SKU123")
 * .name("Updated Product") .quantity(15) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * // When when(inventoryRepository.findById(1L)).thenReturn(Optional.of(
 * existingInventory));
 * when(inventoryRepository.save(any(Inventory.class))).thenReturn(
 * updatedInventory);
 * 
 * InventoryResponse inventoryResponse = inventoryService.updateInventory(1L,
 * inventoryRequest);
 * 
 * // Then assertNotNull(inventoryResponse); assertEquals("Updated Product",
 * inventoryResponse.getName()); assertEquals(15,
 * inventoryResponse.getQuantity()); }
 * 
 * @Test public void testDeleteInventory() { // Given Inventory
 * existingInventory = Inventory.builder() .id(1L) .skuCode("SKU123")
 * .name("Test Product") .quantity(10) .userId("user1")
 * .status(Status.AVAILABLE) .build();
 * 
 * // When when(inventoryRepository.findById(1L)).thenReturn(Optional.of(
 * existingInventory));
 * doNothing().when(inventoryRepository).delete(existingInventory);
 * 
 * String result = inventoryService.deleteInventory(1L);
 * 
 * // Then assertEquals("Inventory item deleted successfully", result);
 * verify(inventoryRepository, times(1)).delete(existingInventory); }
 * 
 * @Test public void testGetInventoryById() { // Given Inventory inventory =
 * Inventory.builder() .id(1L) .skuCode("SKU123") .name("Test Product")
 * .quantity(10) .userId("user1") .status(Status.AVAILABLE) .build();
 * 
 * // When
 * when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));
 * InventoryResponse inventoryResponse = inventoryService.getInventoryById(1L);
 * 
 * // Then assertNotNull(inventoryResponse); assertEquals("Test Product",
 * inventoryResponse.getName()); assertEquals(10,
 * inventoryResponse.getQuantity()); }
 * 
 * @Test public void testGetAllInventoryEntries() { // Given Inventory
 * inventory1 = Inventory.builder() .id(1L) .skuCode("SKU123")
 * .name("Product 1") .quantity(10) .userId("user1") .status(Status.AVAILABLE)
 * .build();
 * 
 * Inventory inventory2 = Inventory.builder() .id(2L) .skuCode("SKU124")
 * .name("Product 2") .quantity(20) .userId("user2") .status(Status.AVAILABLE)
 * .build();
 * 
 * List<Inventory> inventoryList = Arrays.asList(inventory1, inventory2);
 * 
 * // When when(inventoryRepository.findAll()).thenReturn(inventoryList);
 * List<InventoryResponse> inventoryResponses =
 * inventoryService.getAllInventoryEntries();
 * 
 * // Then assertNotNull(inventoryResponses); assertEquals(2,
 * inventoryResponses.size()); }
 * 
 * 
 * }
 */