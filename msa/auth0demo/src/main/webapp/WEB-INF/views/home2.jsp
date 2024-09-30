<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Flippy - Welcome</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f0f0f0;
    }

    /* Navbar styling */
    .navbar {
      background-color: #4B0082;
      padding: 15px;
      position: fixed;
      width: 100%;
      top: 0;
      z-index: 1000;
    }

    .navbar ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: flex-end;
    }

    .navbar ul li {
      margin-left: 20px;
    }

    .navbar ul li a {
      text-decoration: none;
      color: white;
      font-size: 16px;
      padding: 10px 20px;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .navbar ul li a:hover {
      background-color: #6A0DAD;
    }

    /* Hero section styling */
    .hero {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100vh;
      text-align: center;
      background-color: #E6E6FA;
      padding: 20px;
    }

    .hero h1 {
      font-size: 60px;
      color: #4B0082;
      margin-bottom: 20px;
    }

    .hero p {
      font-size: 24px;
      color: #333;
    }

    /* Buttons for login/register */
    .button-container {
      margin-top: 40px;
    }

    .button-container a {
      text-decoration: none;
      background-color: #4B0082;
      color: white;
      padding: 15px 30px;
      border-radius: 5px;
      margin: 0 15px;
      font-size: 18px;
      transition: background-color 0.3s ease;
    }

    .button-container a:hover {
      background-color: #6A0DAD;
    }

  </style>
</head>
<body>

  <!-- Navbar -->
  <nav class="navbar">
    <ul>
      <li><a href="/login">Login</a></li>
      <li><a href="/login">Register</a></li>
    </ul>
  </nav>

  <!-- Hero Section -->
  <section class="hero">
    <h1>Welcome to Flippy</h1>
    <p>Your favorite place to shop online!</p>
    <div class="button-container">
      <a href="/login">Login</a>
      <a href="/login">Register</a>
    </div>
  </section>

</body>
</html>
