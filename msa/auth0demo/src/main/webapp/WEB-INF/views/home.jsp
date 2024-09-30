<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - E-commerce</title>
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    body {
      font-family: Arial, sans-serif;
      background-color: #f0f4f8;
    }

    /* Navigation bar styles */
    .navbar {
      width: 100%;
      background-color: #333;
      padding: 15px 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: fixed;
      top: 0;
      left: 0;
      z-index: 1000;
    }

    .navbar h1 {
      color: white;
      font-size: 22px;
    }

    .navbar ul {
      list-style-type: none;
      display: flex;
      margin: 0;
      padding: 0;
    }

    .navbar ul li {
      margin-left: 20px;
    }

    .navbar ul li a {
      color: white;
      text-decoration: none;
      padding: 10px 20px;
      font-size: 16px;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .navbar ul li a:hover {
      background-color: #575757;
    }

    /* Hero section */
    .hero {
      background-image: url('https://via.placeholder.com/1500x600'); /* Placeholder image, replace with your own */
      background-size: cover;
      background-position: center;
      color: white;
      padding: 100px 20px;
      text-align: center;
      margin-top: 65px; /* Account for navbar height */
    }

    .hero h1 {
      font-size: 48px;
      margin-bottom: 20px;
      text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
    }

    .hero p {
      font-size: 20px;
      margin-bottom: 20px;
      text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.5);
    }

    /* Products section */
    .products {
      padding: 40px 20px;
      background-color: #fff;
    }

    .products h2 {
      text-align: center;
      font-size: 36px;
      margin-bottom: 40px;
      color: #333;
    }

    .product-grid {
      display: flex;
      flex-wrap: wrap;
      gap: 30px;
      justify-content: space-around;
    }

    .product {
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      overflow: hidden;
      width: calc(33.333% - 30px);
      text-align: center;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .product:hover {
      transform: translateY(-10px);
      box-shadow: 0 6px 15px rgba(0, 0, 0, 0.15);
    }

    .product img {
      max-width: 100%;
      height: auto;
      transition: transform 0.3s ease;
    }

    .product:hover img {
      transform: scale(1.05);
    }

    .product h3 {
      font-size: 24px;
      color: #0073e6;
      margin: 15px 0;
    }

    .product p {
      font-size: 18px;
      color: #666;
      margin-bottom: 15px;
    }

    .product a {
      display: inline-block;
      padding: 10px 15px;
      background-color: #0073e6;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      margin-bottom: 15px;
      transition: background-color 0.3s ease;
    }

    .product a:hover {
      background-color: #005bb5;
    }

    /* Responsive design */
    @media (max-width: 768px) {
      .product {
        width: calc(50% - 30px);
      }
    }

    @media (max-width: 480px) {
      .product {
        width: 100%;
      }

      .navbar ul {
        flex-direction: column;
        align-items: flex-start;
      }

      .navbar ul li {
        margin-bottom: 10px;
      }
    }

  </style>
</head>
<body>

  <!-- Navigation Bar -->
  <nav class="navbar">
    <h1>E-commerce</h1>
    <ul>
      <li><a href="/home">Home</a></li>
      <li><a href="/categories">Categories</a></li>
      <li><a href="/cart">Cart</a></li>
      <li><a href="/orders">Orders</a></li>
      <li><a href="/profile">My Account</a></li>
      <li><a href="/login">Login</a></li>
      <li><a href="/register">Register</a></li>
    </ul>
  </nav>

  <!-- Hero Section -->
  <section class="hero">
    <h1>Welcome to Our E-commerce Store</h1>
    <p>Shop the latest products across various categories!</p>
  </section>

  <!-- Featured Products Section -->
  <section class="products">
    <h2>Featured Products</h2>
    <div class="product-grid">
      <div class="product">
        <img src="src/main/resources/static/images/redmi.jpeg" alt="Redmi Note 12 Pro+">
        <h3>Redmi Note 12 Pro+</h3>
        <p>Price: $100</p>
        <a href="/product/1">View Details</a>
      </div>
      <div class="product">
        <img src="src/main/resources/static/images/vivo-mobile-phone.jpg" alt="Vivo Y100">
        <h3>Vivo Y100</h3>
        <p>Price: $150</p>
        <a href="/product/2">View Details</a>
      </div>
      <!-- Add more products here -->
    </div>
  </section>

</body>
</html>
