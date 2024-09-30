<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
     <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .btn-add-stock {
            background-color: #28a745;
            color: white;
        }
        .btn-edit {
            background-color: #007bff;
            color: white;
        }
        .btn-delete {
            background-color: #dc3545;
            color: white;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center mb-4">Product Management</h1>
    
    <!-- Search Products -->
    <div class="mb-4 d-flex justify-content-center">
        <input type="text" class="form-control w-50" id="searchInput" placeholder="Enter product name...">
        <button class="btn btn-primary ms-2" onclick="searchProducts()">Search</button>
    </div>

    <!-- Form to Create/Update Product -->
    <div class="card mt-4">
        <div class="card-header" id="formHeader">Create Product</div>
        <div class="card-body">
            <form id="productForm" action="http://localhost:9000/api/product" method="post">
                <input type="hidden" id="productId" name="id">
                <div class="mb-3">
                    <label for="name" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description" required>
                </div>
                <div class="mb-3">
                    <label for="skuCode" class="form-label">SKU Code</label>
                    <input type="text" class="form-control" id="skuCode" name="skuCode" required>
                </div>
                <div class="mb-3">
                    <label for="categoryId" class="form-label">Category ID</label>
                    <input type="number" class="form-control" id="categoryId" name="categoryId" required>
                </div>
                <div class="mb-3">
                    <label for="imageurl" class="form-label">Image URL</label>
                    <input type="text" class="form-control" id="imageurl" name="imageurl" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" class="form-control" id="price" name="price" required>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary" id="submitBtn">Submit</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Display All Products -->
    <div class="card mt-4">
        <div class="card-header">All Products</div>
        <div class="card-body">
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>SKU Code</th>
                        <th>Category</th>
                        <th>Image URL</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody id="productTableBody">
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.skuCode}</td>
                            <td>${product.category.name}</td>
                            <td><img src="${product.imageurl}" alt="${product.name}" style="width: 50px;"></td>
                            <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$" /></td>
                            <td>
                                <button type="button" class="btn btn-add-stock" onclick="addToStock(${product.id})">Add to Stock</button>
                                <button type="button" class="btn btn-edit" onclick="editProduct(${product.id})">Edit</button>
                                <button class="btn btn-delete" onclick="deleteProduct(${product.id})">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
// JavaScript to handle form submission and API calls

// Edit product functionality
function editProduct(id) {
    const apiUrl = "http://localhost:9000/api/product";
    fetch(`${apiUrl}/${id}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById("name").value = product.name;
            document.getElementById("description").value = product.description;
            document.getElementById("skuCode").value = product.skuCode;
            document.getElementById("categoryId").value = product.category.id;
            document.getElementById("imageurl").value = product.imageurl;
            document.getElementById("price").value = product.price;
            document.getElementById("productId").value = product.id;

            document.getElementById("productForm").action = `${apiUrl}/update`;
            document.getElementById("submitBtn").innerText = "Update"; 
            document.getElementById("formHeader").innerText = "Update Product"; 
        });
}

function searchProducts() {
    const query = document.getElementById("searchInput").value.toLowerCase();
    const rows = document.querySelectorAll("#productTableBody tr");

    rows.forEach(row => {
        const productName = row.cells[1].textContent.toLowerCase();
        if (productName.includes(query)) {
            row.style.display = "";  // Show row if product name matches the query
        } else {
            row.style.display = "none";  // Hide row if product name doesn't match
        }
    });
}

// Delete product functionality
function deleteProduct(id) {
    const apiUrl = "http://localhost:9000/api/product";
    if (confirm("Are you sure you want to delete this product?")) {
        fetch(`${apiUrl}?id=${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                location.reload();  
            } else {
                alert("Failed to delete the product.");
            }
        })
        .catch(error => {
            console.error("Error deleting product:", error);
            alert("An error occurred. Check the console for details.");
        });
    }
}

// Add to stock functionality
function addToStock(id) {
    window.location.href = `/addToStock.jsp?productId=${id}`;
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
