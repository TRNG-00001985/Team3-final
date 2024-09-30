<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.revature.revshop.ProductResponse" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }
        .card:hover {
            transform: scale(1.05);
        }
        .card-img-top {
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            height: 200px;
            object-fit: cover;
        }
        .search-form {
            margin-bottom: 20px;
        }
        .no-products {
            text-align: center;
            margin-top: 20px;
            font-size: 1.2rem;
        }
        .btn-custom {
            background-color: #007bff;
            color: #fff;
            border-radius: 20px;
            padding: 10px 20px;
            border: none;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .btn-success-custom {
            background-color: #28a745;
            color: #fff;
            border-radius: 20px;
            padding: 10px 20px;
            border: none;
        }
        .btn-success-custom:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center mb-4">Product List</h1>
    
    <!-- Search form -->
    <form action="searchProducts" method="get" class="form-inline justify-content-center search-form">
        <input type="text" name="query" class="form-control mr-2" placeholder="Search for products" required>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    
    <div class="row">
        <%
            List<ProductResponse> products = (List<ProductResponse>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (ProductResponse product : products) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card">
               <img src="<%= product.getImageurl() %>" class="card-img-top" alt="<%= product.getName() %>">
               
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <p class="card-text">Price: $<%= product.getPrice() %></p>
                    <p class="card-text">SKU Code: <%= product.getSkuCode() %></p>
                 <p class="card-text">Category: <%= product.getCategory().getName() %></p>
                    <a href="addToCart?productId=<%= product.getId() %>" class="btn btn-custom">Add to Cart</a>
                    <a href="buyNow?productId=<%= product.getId() %>" class="btn btn-success-custom">Buy Now</a>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="col-12 no-products">
            <p>No products found.</p>
        </div>
        <%
            }
        %>
    </div>
</div>



<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
