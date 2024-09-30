<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Category</title>
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
        input[type="text"], input[type="file"] {
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
    <h1>Create Category</h1>

    <div class="form-group">
        <label for="categoryName">Category Name</label>
        <input type="text" id="categoryName" placeholder="Enter category name" required>
    </div>

    <div class="form-group">
        <label for="categoryLogo">Category Logo URL</label>
        <input type="text" id="categoryLogo" placeholder="Enter category logo URL" required>
    </div>

    <button type="button" onclick="createCategory()">Create Category</button>

    <div id="alertSuccess" class="alert alert-success">Category created successfully!</div>
    <div id="alertError" class="alert alert-error">Failed to create category. Please try again.</div>
</div>

<script>
    // Function to create a category
    function createCategory() {
        const name = document.getElementById('categoryName').value;
        const logo = document.getElementById('categoryLogo').value;

        // Construct the category request object
        const categoryRequest = {
            name: name,
            logo: logo
        };

        // API Gateway URL
        const apiUrl = "http://localhost:9000/api/category";

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(categoryRequest),
        })
        .then(response => {
            if (response.ok) {
                document.getElementById('alertSuccess').style.display = 'block';
                document.getElementById('alertError').style.display = 'none';
            } else {
                document.getElementById('alertSuccess').style.display = 'none';
                document.getElementById('alertError').style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Error creating category:', error);
            document.getElementById('alertSuccess').style.display = 'none';
            document.getElementById('alertError').style.display = 'block';
        });
    }
</script>

</body>
</html>
