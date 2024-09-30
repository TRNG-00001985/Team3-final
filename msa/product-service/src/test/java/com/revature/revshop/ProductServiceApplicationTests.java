/*
 * package com.revature.revshop;
 * 
 * import com.revature.revshop.dto.CategoryDto; import
 * com.revature.revshop.dto.ProductRequest; import
 * com.revature.revshop.dto.ProductResponse; import
 * com.revature.revshop.model.Category; import
 * com.revature.revshop.model.Product; import
 * com.revature.revshop.repository.CategoryRepository; import
 * com.revature.revshop.repository.ProductRepository; import
 * com.revature.revshop.service.ProductService; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * import java.util.Arrays; import java.util.List; import java.util.Optional;
 * 
 * import static org.junit.jupiter.api.Assertions.*; import static
 * org.mockito.Mockito.*;
 * 
 * @SpringBootTest public class ProductServiceApplicationTests {
 * 
 * @Mock private ProductRepository productRepository;
 * 
 * @Mock private CategoryRepository categoryRepository;
 * 
 * @InjectMocks private ProductService productService;
 * 
 * private ProductRequest productRequest; private Product product; private
 * Category category;
 * 
 * @BeforeEach void setUp() { // Set up a mock Category object category =
 * Category.builder() .id(1L) .name("Electronics") .logo("electronics.png")
 * .build();
 * 
 * // Set up a mock ProductRequest object productRequest =
 * ProductRequest.builder() .name("Laptop") .description("A powerful laptop")
 * .skuCode("LAP123") .price(999.99) .categoryId(1L) .build();
 * 
 * // Set up a mock Product object product = Product.builder() .id(1L)
 * .name("Laptop") .description("A powerful laptop") .skuCode("LAP123")
 * .price(999.99) .category(category) .build(); }
 * 
 * @Test void createProduct_ShouldReturnProductResponse() { // Mock the behavior
 * of categoryRepository and productRepository
 * when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
 * when(productRepository.save(any(Product.class))).thenReturn(product);
 * 
 * // Test the createProduct method ProductResponse response =
 * productService.createProduct(productRequest);
 * 
 * // Validate the result assertNotNull(response); assertEquals("Laptop",
 * response.getName()); assertEquals("Electronics",
 * response.getCategory().getName());
 * 
 * // Verify that the save method of productRepository was called
 * verify(productRepository, times(1)).save(any(Product.class));
 * verify(categoryRepository, times(1)).findById(1L); }
 * 
 * @Test void createProduct_ShouldThrowExceptionWhenCategoryNotFound() { // Mock
 * the case where category is not found
 * when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
 * 
 * // Validate that an exception is thrown assertThrows(RuntimeException.class,
 * () -> productService.createProduct(productRequest));
 * 
 * // Verify that the save method of productRepository is never called
 * verify(productRepository, never()).save(any(Product.class)); }
 * 
 * @Test void getProductById_ShouldReturnProductResponse() { // Mock the
 * behavior of productRepository
 * when(productRepository.findById(1L)).thenReturn(Optional.of(product));
 * 
 * // Test the getProductById method ProductResponse response =
 * productService.getProductById(1L);
 * 
 * // Validate the result assertNotNull(response); assertEquals("Laptop",
 * response.getName()); assertEquals("Electronics",
 * response.getCategory().getName());
 * 
 * // Verify that the findById method of productRepository was called
 * verify(productRepository, times(1)).findById(1L); }
 * 
 * @Test void getProductById_ShouldThrowExceptionWhenProductNotFound() { // Mock
 * the case where product is not found
 * when(productRepository.findById(1L)).thenReturn(Optional.empty());
 * 
 * // Validate that an exception is thrown assertThrows(RuntimeException.class,
 * () -> productService.getProductById(1L));
 * 
 * // Verify that findById was called once verify(productRepository,
 * times(1)).findById(1L); }
 * 
 * @Test void updateProductById_ShouldReturnUpdatedProductResponse() { // Mock
 * the behavior of categoryRepository and productRepository
 * when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
 * when(productRepository.save(any(Product.class))).thenReturn(product);
 * when(productRepository.findById(1L)).thenReturn(Optional.of(product));
 * 
 * // Test the updateProductById method ProductResponse response =
 * productService.updateProductById(productRequest, 1L);
 * 
 * // Validate the result assertNotNull(response); assertEquals("Laptop",
 * response.getName()); assertEquals("Electronics",
 * response.getCategory().getName());
 * 
 * // Verify that the save method was called verify(productRepository,
 * times(1)).save(any(Product.class)); verify(categoryRepository,
 * times(1)).findById(1L); }
 * 
 * @Test void deleteProductById_ShouldReturnTrue() { // Mock the behavior of
 * productRepository doNothing().when(productRepository).deleteById(1L);
 * 
 * // Test the deleteProductById method boolean result =
 * productService.deleteProductById(1L);
 * 
 * // Validate the result assertTrue(result);
 * 
 * // Verify that deleteById was called once verify(productRepository,
 * times(1)).deleteById(1L); }
 * 
 * // @Test // void deleteProductById_ShouldThrowExceptionWhenProductNotFound()
 * { // // Mock the case where product does not exist // doThrow(new
 * RuntimeException("Product not found")).when(productRepository).deleteById(1L)
 * ; // // // Validate that an exception is thrown //
 * assertThrows(RuntimeException.class, () ->
 * productService.deleteProductById(1L)); // // // Verify that deleteById was
 * called once // verify(productRepository, times(1)).deleteById(1L); // }
 * 
 * @Test void getAllProducts_ShouldReturnListOfProductResponses() { // Mock the
 * behavior of productRepository
 * when(productRepository.findAll()).thenReturn(Arrays.asList(product));
 * 
 * // Test the getAllProducts method List<ProductResponse> responses =
 * productService.getAllProducts();
 * 
 * // Validate the result assertNotNull(responses); assertEquals(1,
 * responses.size()); assertEquals("Laptop", responses.get(0).getName());
 * 
 * // Verify that findAll was called once verify(productRepository,
 * times(1)).findAll(); }
 * 
 * @Test void contextLoads() { // This test ensures that the Spring context
 * loads correctly } }
 */