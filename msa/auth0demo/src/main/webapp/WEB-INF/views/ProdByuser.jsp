<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products by User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }

        .dashboard-item {
            margin-bottom: 20px;
        }

        .table-container {
            margin-top: 20px;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 18px;
            text-align: left;
            background-color: #f8f9fa;
        }

        th, td {
            padding: 12px;
            border: 1px solid #dee2e6;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tbody tr:hover {
            background-color: #d1ecf1;
        }

        .product-image {
            width: 50px;
            height: auto;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <div class="dashboard-item">
        <h2>View Products by User ID</h2>
        <input type="hidden" id="userId" value="${profile.sub.length() > 6 ? profile.sub.substring(6) : profile.sub}"> <!-- Substring from index 6 -->
        <button id="viewProductsButton">View My Products</button>
    </div>

    <div id="productsList" class="table-container">
        <table id="productsTable">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>SKU</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Category ID</th>
                </tr>
            </thead>
            <tbody>
                <!-- Dynamic rows will be added here -->
            </tbody>
        </table>
    </div>

    <script>
        // Event listener for "View My Products" button
        document.getElementById('viewProductsButton').onclick = function() {
            // Get userId from the hidden input field
            const userId = document.getElementById('userId').value;
            
            // API URL with userId as a query parameter
            const apiUrl = "http://localhost:8081/api/product/user/" + encodeURIComponent(userId);

            // Fetch products from the API
            fetch(apiUrl)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }
                    return response.json();
                })
                .then(products => {
                    const productsTableBody = document.querySelector('#productsTable tbody');
                    productsTableBody.innerHTML = ''; // Clear previous products

                    if (products.length === 0) {
                        alert('No products found for this user.');
                        return;
                    }

                    // Populate table with fetched products
                    products.forEach(product => {
                        const productRow = document.createElement('tr');
                        productRow.innerHTML = `
                            <td>${userId}</td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.skuCode}</td>
                            <td>${product.price.toFixed(2)}</td>
                            <td><img src="${product.imageUrl}" alt="${product.name}" class="product-image"></td>
                            <td>${product.categoryId}</td>
                        `;
                        productsTableBody.appendChild(productRow);
                    });
                })
                .catch(error => console.error('Error fetching products:', error));
        };
    </script>

</body>
</html>
