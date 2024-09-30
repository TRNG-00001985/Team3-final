<form id="updateProductForm">
    <!-- Hidden input for storing the productId -->
    <input type="hidden" id="productId" value="/${product.id}">

    <label for="name">Product Name:</label>
    <input type="text" id="name" name="name" value="\${product.name}">

    <label for="description">Product Description:</label>
    <textarea id="description" name="description">\${product.description}</textarea>

    <label for="sku">SKU Code:</label>
    <input type="text" id="sku" name="sku" value="\${product.skuCode}">

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="\${product.price}" step="0.01">

    <label for="imageurl">Image URL:</label>
    <input type="text" id="imageurl" name="imageurl" value="\${product.imageurl}">

    <label for="categoryId">Category ID:</label>
    <input type="number" id="categoryId" name="categoryId" value="\${product.categoryId}">

    <button type="button" onclick="submitUpdateForm()">Update Product</button>
</form>

<script>
// Function to get productId from URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

document.addEventListener('DOMContentLoaded', function() {
    // Get productId from URL and store in the hidden input
    const productId = getQueryParam('id');
    document.getElementById('productId').value = productId;

    // Optionally, pre-fill form fields with product data if necessary
});

function submitUpdateForm() {
    const productId = document.getElementById('productId').value;
    const name = document.getElementById('name').value;
    const description = document.getElementById('description').value;
    const skuCode = document.getElementById('sku').value;
    const price = document.getElementById('price').value;
    const imageurl = document.getElementById('imageurl').value;
    const categoryId = document.getElementById('categoryId').value;

    const updatedProduct = {
        id: productId,
        name: name,
        description: description,
        skuCode: skuCode,
        price: price,
        imageurl: imageurl,
        categoryId: categoryId
    };

    fetch(`http://localhost:9000/api/product/\${productId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedProduct)
    })
    .then(response => {
        if (response.ok) {
            alert('Product updated successfully!');
            window.location.href = 'http://localhost:9000/productList'; // Redirect back to product list after successful update
        } else {
            console.error('Failed to update product');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
</script>
