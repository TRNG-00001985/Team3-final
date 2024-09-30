<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> <!-- Font Awesome for icons -->
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8; /* Light gray background */
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #0073e6;
            padding: 15px 20px;
            color: white;
        }

        .navbar h1 {
            margin: 0;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            transition: color 0.3s;
        }

        .navbar a:hover {
            color: #e6f7ff; /* Lighter shade for hover effect */
        }

        .dashboard-container {
            text-align: center;
            margin: 40px auto;
            max-width: 800px;
            padding: 20px;
            background-color: #ffffff; /* White background for clean look */
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .dashboard-container h1 {
            color: #0073e6;
            margin-bottom: 30px;
        }

        .nav-links {
            display: flex;
            flex-direction: column;
            gap: 15px;
            text-align: center;
        }

        .nav-links a {
            display: block;
            padding: 12px 0;
            font-size: 18px;
            background-color: #0073e6;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s, transform 0.3s;
        }

        .nav-links a:hover {
            background-color: #005bb5;
            transform: translateY(-3px); /* Lift effect on hover */
        }

        .logout-section {
            margin-top: 30px;
        }

        .logout-section button {
            padding: 12px 20px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        .logout-section button:hover {
            background-color: #e60000;
            transform: translateY(-3px); /* Lift effect on hover */
        }

    </style>
</head>
<body>

    <div class="navbar">
        <h1>Dashboard</h1>
        <div>
            <a href="/jsp/home2">Home</a>
            <a href="/jsp/profile">Profile</a>
            <a href="/jsp/help">Help</a>
        </div>
    </div>

    <div class="dashboard-container">
        <h1>Welcome to Your Dashboard</h1>

        <div class="nav-links">
            <a href="/jsp/createProduct">Add Products</a>
            <a href="/jsp/ProductManager">View Products</a>
            <a href="/jsp/viewOrders">Check Orders</a>
            <a href="/jsp/profile">View Profile</a>
        </div>

        <div class="logout-section">
            <button onclick="window.location.href='/jsp/home2'">Logout</button>
        </div>
    </div>

</body>
</html>
