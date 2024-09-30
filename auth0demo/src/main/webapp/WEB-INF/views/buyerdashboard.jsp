

<div class="dashboard-container">
    <h1>Welcome to Your Dashboard</h1>
    <p>Manage your account, view products, and check your orders below.</p>

    <div class="dashboard-grid">
        <div class="dashboard-item">
            <h2>Available Products</h2>
            <button onclick="window.location.href='/jsp/viewAllProducts'">View Products</button>
        </div>
        
        <div class="dashboard-item">
            <h2>Categories</h2>
            <button onclick="window.location.href='/jsp/viewCategories'">View Categories</button>
        </div>
        <div class="dashboard-item">
            <h2>Your Cart</h2>
            <button onclick="window.location.href='/jsp/viewCart'">View Cart</button>
        </div>
        <div class="dashboard-item">
            <h2>View Orders</h2>
            <button onclick="window.location.href='/jsp/viewOrders'">Check Orders</button>
        </div>
        <div class="dashboard-item">
            <h2>View Profile</h2>
            <button onclick="window.location.href='/jsp/profile'">Edit Profile</button>
        </div>
    </div>

    <div class="logout-section">
        <button onclick="window.location.href='/jsp/home2'">Logout</button>
    </div>
</div>


<style>
    .dashboard-container {
        text-align: center;
        margin: 0 auto;
        max-width: 1200px;
        padding: 20px;
        background-color: #e6f7ff; /* Light blue background */
    }

    .dashboard-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
    }

    .dashboard-item {
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
    }

    .dashboard-item h2 {
        margin-bottom: 15px;
        color: #0073e6; /* Blue heading color */
    }

    .dashboard-item button {
        padding: 10px 20px;
        background-color: #0073e6;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .dashboard-item button:hover {
        background-color: #005bb5;
    }

    .logout-section {
        margin-top: 20px;
    }

    .logout-section button {
        padding: 10px 20px;
        background-color: #ff4d4d;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .logout-section button:hover {
        background-color: #e60000;
    }
</style>
