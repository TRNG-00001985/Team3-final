<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Orders</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .alert {
            margin-top: 20px;
            padding: 10px;
            display: none;
        }
        .alert-success {
            background-color: #5cb85c;
            color: #fff;
        }
        .alert-error {
            background-color: #d9534f;
            color: #fff;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Your Orders</h1>
    <input type="hidden" id="userId" value="${sessionScope.userId}"> <!-- Get userId from session -->

    <div id="alertSuccess" class="alert alert-success">Orders fetched successfully!</div>
    <div id="alertError" class="alert alert-error">Failed to fetch orders. Please try again.</div>

    <table id="ordersTable">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <!-- Orders will be populated here -->
        </tbody>
    </table>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const userId = document.getElementById('userId').value; // Retrieve user ID
        console.log("User ID:", userId); // Debugging statement to print userId
        fetchOrders(userId);
    });

    function fetchOrders(userId) {
        const apiUrl = "http://localhost:8082/api/order/user?userId=" + encodeURIComponent(userId);

        fetch(apiUrl)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to fetch orders');
                }
            })
            .then(orders => {
                const tableBody = document.querySelector('#ordersTable tbody');
                tableBody.innerHTML = ''; // Clear existing rows
                orders.forEach(order => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>\${order.id}</td>
                        <td>\${order.productName}</td>
                        <td>\${order.quantity}</td>
                        <td>\${order.status}</td>
                        `;
                    tableBody.appendChild(row);
                });
                document.getElementById('alertSuccess').style.display = 'block';
            })
            .catch(error => {
                console.error('Error fetching orders:', error);
                document.getElementById('alertError').style.display = 'block';
            });
    }
</script>

</body>
</html>
