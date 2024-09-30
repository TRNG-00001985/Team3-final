<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"], input[type="url"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #4cae4c;
        }
        .alert {
            margin-top: 20px;
            color: #fff;
            padding: 10px;
            display: none;
        }
        .alert-success {
            background-color: #5cb85c;
        }
        .alert-error {
            background-color: #d9534f;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h1>Create Product</h1>

    <div class="form-group">
        <label for="productName">Product Name</label>
        <input type="text" id="productName" placeholder="Enter product name" required>
    </div>

    <div class="form-group">
        <label for="productDescription">Product Description</label>
        <input type="text" id="productDescription" placeholder="Enter product description" required>
    </div>

    <div class="form-group">
        <label for="skuCode">SKU Code</label>
        <input type="text" id="skuCode" placeholder="Enter SKU code" required>
    </div>

    <div class="form-group">
        <label for="imageUrl">Image URL</label>
        <input type="url" id="imageUrl" placeholder="Enter product image URL" required>
    </div>

    <div class="form-group">
        <label for="price">Price</label>
        <input type="number" id="price" placeholder="Enter price" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="categoryId">Category ID</label>
        <input type="number" id="categoryId" placeholder="Enter category ID" required>
    </div>

    <div class="form-group">
        <label for="userId">User ID</label>
        <input type="text" id="userId" value="${profile.sub.length() > 6 ? profile.sub.substring(6) : profile.sub}" required> <!-- Use user ID from the OIDC claims -->
    </div>

    <button type="button" onclick="createProduct()">Create Product</button>

    <div id="alertSuccess" class="alert alert-success">Product created successfully!</div>
    <div id="alertError" class="alert alert-error">Failed to create product. Please try again.</div>
</div>

<script>
// Function to create a product
function createProduct() {
    const name = document.getElementById('productName').value;
    const description = document.getElementById('productDescription').value;
    const skuCode = document.getElementById('skuCode').value;
    const imageUrl = document.getElementById('imageUrl').value;
    const price = document.getElementById('price').value;
    const categoryId = document.getElementById('categoryId').value;
    const userId = document.getElementById('userId').value; // Retrieve user ID

    // Construct the product request object
    const productRequest = {
        name: name,
        description: description,
        skuCode: skuCode,
        imageurl: imageUrl,
        price: parseFloat(price),
        categoryId: parseInt(categoryId)
    };

    // API Gateway URL with user ID as a query parameter
    const apiUrl = "http://localhost:9000/api/product?userId=" + encodeURIComponent(userId);

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(productRequest),
    })
    .then(response => {
        if (response.ok) {
            document.getElementById('alertSuccess').style.display = 'block';
            document.getElementById('alertError').style.display = 'none';
            clearFields();
        } else {
            document.getElementById('alertSuccess').style.display = 'none';
            document.getElementById('alertError').style.display = 'block';
        }
    })
    .catch(error => {
        console.error('Error creating product:', error);
        document.getElementById('alertSuccess').style.display = 'none';
        document.getElementById('alertError').style.display = 'block';
    });
}

// Clear input fields after successful creation
function clearFields() {
    document.getElementById('productName').value = '';
    document.getElementById('productDescription').value = '';
    document.getElementById('skuCode').value = '';
    document.getElementById('imageUrl').value = '';
    document.getElementById('price').value = '';
    document.getElementById('categoryId').value = '';
    document.getElementById('userId').value = '${profile.sub}'; // Reset to the user's ID
}
</script>

</body>
</html>
