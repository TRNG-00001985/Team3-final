<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - E-commerce</title>
  <style>
	body {
	  font-family: Arial, sans-serif;
	  margin: 0;
	  padding: 0;
	}

	.navbar {
	    position: absolute;
	    top: 0;
	    right: 0;
	    width: 100%;
	    background-color: #333;
	    padding: 10px;
	}

	.navbar ul {
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    display: flex;
	    flex-direction: row;
		justify-content: flex-end
	}

	.navbar ul li {
	    margin-left: 20px;
	}

	.navbar ul li a {
	    text-decoration: none;
	    color: white;
	    padding: 10px;
	    font-size: 16px;
	}

	.navbar ul li a:hover {
	    background-color: #575757;
	    border-radius: 5px;
	}


	.hero {
	  background-color: #f4f4f4;
	  padding: 20px;
	  text-align: center;
	}

	.products {
	  padding: 20px;
	}

	.product-grid {
	  display: flex;
	  gap: 20px;
	  justify-content: space-around;
	}

	.product {
	  border: 1px solid #ddd;
	  padding: 10px;
	  text-align: center;
	}

	.product img {
	  width: 150px;
	  height: 250px;
	}

	
  </style>
  </head>
<body>
	<nav class="navbar">
	    <ul>
	        <li><a href="/home1">Home</a></li>
	        <li><a href="/categories">Categories</a></li>
	        <li><a href="/cart">Cart</a></li>
	        <li><a href="/orders">Orders</a></li>
	        <li><a href="/profile">My Account</a></li>
			<li><a href="/logout">Logout</a></li>
	     </ul>
	</nav>


  <section class="hero">
    <h1>Welcome to Our E-commerce Store</h1>
    <p>Shop the latest products across various categories!</p>
  </section>

  <section class="products">
    <h2>Featured Products</h2>
    <div class="product-grid">
		<div class="product">
		  <img src="https://www.google.com/imgres?q=mobile%20images&imgurl=https%3A%2F%2Fimg.freepik.com%2Fpremium-photo%2Fsmartphone-balancing-with-pink-background_23-2150271746.jpg%3Fsize%3D338%26ext%3Djpg%26ga%3DGA1.1.2008272138.1726876800%26semt%3Dais_hybrid&imgrefurl=https%3A%2F%2Fwww.freepik.com%2Fphotos%2Fphone&docid=DpALy7rORqn4PM&tbnid=w2TE6wNeV19SjM&vet=12ahUKEwjAu92-0dWIAxUdUGwGHbtWGcQQM3oFCIABEAA..i&w=338&h=338&hcb=2&ved=2ahUKEwjAu92-0dWIAxUdUGwGHbtWGcQQM3oFCIABEAA" alt="Redmi Note 12 Pro+">
		  <h3>Redmi Note 12 Pro+</h3>
		  <p>Price: $100</p>
		  <a href="/product/1">View Details</a>
		</div>
		<div class="product">
		  <img src="https://www.google.com/imgres?q=mobile%20images&imgurl=https%3A%2F%2Fimg.etimg.com%2Fphoto%2Fmsid-99080556%2Cimgsize-32858%2FVivoY56BlackEngine.jpg&imgrefurl=https%3A%2F%2Fm.economictimes.com%2Ftop-trending-products%2Fmobile-phones%2F6-best-mobile-phones-with-128gb-memory-for-immense-storage%2Farticleshow%2F99079687.cms&docid=m3sg0rO8txy-CM&tbnid=52gsZKOZ-eeQcM&vet=12ahUKEwjAu92-0dWIAxUdUGwGHbtWGcQQM3oFCIEBEAA..i&w=1200&h=1200&hcb=2&ved=2ahUKEwjAu92-0dWIAxUdUGwGHbtWGcQQM3oFCIEBEAA" alt="Vivo Y100">
		  <h3>Vivo Y100</h3>
		  <p>Price: $150</p>
		  <a href="/product/2">View Details</a>
		</div>
		<div class="product">
		  <img src="https://m.media-amazon.com/images/I/71geVdy6-OS._AC_SL1500_.jpg" alt="iPhone 13">
		  <h3>iPhone 13</h3>
		  <p>Price: $800</p>
		  <a href="/product/3">View Details</a>
		</div>
		<div class="product">
		  <img src="https://m.media-amazon.com/images/I/81S-y95yJzL._AC_SL1500_.jpg" alt="Samsung Galaxy S21">
		  <h3>Samsung Galaxy S21</h3>
		  <p>Price: $600</p>
		  <a href="/product/4">View Details</a>
		</div>
		<div class="product">
		  <img src="https://m.media-amazon.com/images/I/61fmQ0jueAL._AC_SL1500_.jpg" alt="OnePlus Nord">
		  <h3>OnePlus Nord</h3>
		  <p>Price: $350</p>
		  <a href="/product/5">View Details</a>
		</div>
    </div>
  </section>

</body>
</html>
