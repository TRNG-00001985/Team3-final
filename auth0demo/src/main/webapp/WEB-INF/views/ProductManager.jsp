<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4; /* Light background for the page */
        }

        .dashboard-item {
            margin-bottom: 20px;
        }

        .product-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around; /* Center items */
            margin-top: 20px;
        }

        .product-card {
            background-color: #ffffff; /* White background for cards */
            border: 1px solid #dee2e6; /* Light border color */
            border-radius: 5px; /* Rounded corners for cards */
            margin: 10px;
            padding: 10px;
            text-align: center; /* Center text within cards */
            width: 200px; /* Fixed width for cards */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        .product-image {
            width: 100px; /* Set a fixed width for images */
            height: auto;
            border-radius: 5px; /* Rounded corners for images */
        }

        .product-price {
            font-size: 18px;
            color: #28a745; /* Green color for price */
            margin: 10px 0;
        }

        .product-description {
            font-size: 14px; /* Smaller font for description */
            color: #555; /* Gray color */
            margin: 5px 0;
        }

        .action-button {
            margin: 5px; /* Add some margin between buttons */
            cursor: pointer; /* Show pointer cursor on hover */
            background-color: #007bff; /* Bootstrap primary color */
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px; /* Rounded corners for buttons */
            transition: background-color 0.3s;
        }

        .action-button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }

        /* Modal Styles */
        .modal {
            display: none; 
            position: fixed; 
            z-index: 1; 
            left: 0;
            top: 0;
            width: 100%; 
            height: 100%; 
            overflow: auto; 
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 300px; 
            border-radius: 5px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div class="dashboard-item">
        <h2>View Products</h2>
    </div>

    <div id="productsList" class="product-container">
        <!-- Dynamic product cards will be added here -->
    </div>

    <!-- Modal for Adding Stock -->
    <div id="addStockModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="document.getElementById('addStockModal').style.display='none'">&times;</span>
            <h2>Add to Stock</h2>
    <form id="addStockForm">
    <label for="productId">Product ID:</label><br>
    <input type="text" id="modalProductId" required ><br>

    <label for="productName">Product Name:</label><br>
    <input type="text" id="modalProductName" required><br>

    <label for="skuCode">SKU Code:</label><br>
    <input type="text" id="modalSkuCode" required><br>

    <label for="userId">User ID:</label><br>
    <input type="text" id="modalUserId" required><br>

    <label for="quantity">Quantity:</label><br>
    <input type="number" id="modalQuantity" required min="1"><br>

    <button type="submit">Submit</button>
</form>
        </div>
    </div>

    <script>
        // Fetch products automatically on page load
        window.onload = function() {
            fetchProducts();
        };

        function fetchProducts() {
            console.log('Fetching products...'); // Debugging log
            fetch('http://localhost:9000/api/product')
                .then(response => {
                    console.log('Response status:', response.status); // Debugging log
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(products => {
                    console.log('Fetched products:', products); // Debugging log
                    const productsContainer = document.querySelector('#productsList');
                    productsContainer.innerHTML = ''; // Clear previous products
                    products.forEach(product => {
                        const productCard = document.createElement('div');
                        productCard.classList.add('product-card');
                        productCard.innerHTML = `
                            <img src="${product.imageurl}" alt="${product.name}" class="product-image" onerror="this.onerror=null; this.src='path/to/placeholder-image.jpg';">
                            <h3>${product.name}</h3>
                            <div class="product-price">&#8377;\${product.price.toFixed(2)}</div>
                            <div class="product-description">\${product.description}</div>
                            <div>SKU: \${product.skuCode}</div>
                            <button class="action-button" onclick="updateProduct(${product.id})">Edit</button>
                            <button class="action-button" onclick="deleteProduct(${product.id})">Delete</button>
                            <button class="action-button" onclick="openAddStockModal(${product.id})">Add to Stock</button>
                        `;
                        productsContainer.appendChild(productCard);
                    });
                })
                .catch(error => console.error('Error fetching products:', error));
        }

        function openAddStockModal(productId) {
            document.getElementById('modalProductId').value = productId; // Set the product ID in the modal
            document.getElementById('addStockModal').style.display = 'block'; // Show the modal
        }

        document.getElementById('addStockForm').onsubmit = async function(event) {
            event.preventDefault(); // Prevent the default form submission

            const productId = document.getElementById('modalProductId').value;
            const quantity = document.getElementById('modalQuantity').value;

            try {
                const response = await fetch("http://localhost:8083/api/inventory", { // Replace with your actual API endpoint
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ productId, quantity }),
                });

                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                alert('Stock added successfully!');
                document.getElementById('addStockModal').style.display = 'none'; // Close the modal
                document.getElementById('addStockForm').reset(); // Reset form
            } catch (error) {
                console.error('Error adding stock:', error);
                alert('Failed to add stock. Please try again later.');
            }
        };

        function updateProduct(productId) {
            console.log('Update product with ID:', productId);
            // Redirect to updateProduct page, passing productId
            window.location.href = `http://localhost/jsp/updateProduct?id=${productId}`;
        }

        function deleteProduct(productId) {
            console.log('Delete product with ID:', productId);
            if (confirm(`Are you sure you want to delete product ID ${productId}?`)) {
                fetch(`http://localhost:9000/api/product?id=${productId}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        console.log(`Product with ID ${productId} deleted successfully.`);
                        fetchProducts(); // Refresh the product list after deletion
                    } else {
                        console.error('Error deleting product:', response.statusText);
                    }
                })
                .catch(error => console.error('Error deleting product:', error));
            }
        }
    </script>

</body>
</html>
