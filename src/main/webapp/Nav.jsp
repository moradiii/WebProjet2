<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="icon" href="img\Logo.png" type="image/png">
<style>
	body {
	  margin: 0;
	  font-family: Arial, Helvetica, sans-serif;
	}
	
	.topnav {
	  overflow: hidden;
	  background-color: #333;
	  position: fixed;
	  top: 0;
	  width: 100%;
	  z-index: 9999;
	}
	
	.topnav a {
	  float: left;
	  color: rgb(2, 157, 224);
	  text-align: center;
	  padding: 16px 16px;
	  text-decoration: none;
	  font-size: 17px;
	  cursor: pointer;
	}
	
	.topnav a:hover {
	  background-color: rgb(2, 157, 224);
	  color: black;
	  cursor: pointer;
	}
	
	.topnav a.active {
	  background-color: rgb(2, 157, 224);
	  color: white;
	}
	
	.topnav a.right {
	  float: right;
	}

	/* Style for the search bar container */
	.search-container {
	  float: left;
	  position: relative; /* For positioning the input and button inside */
	}
	
	/* Style for the search input */
	.search-container input[type=text] {
	  margin-top: 6px;
	  display: none; /* Initially hidden */
	  padding: 10px;
	  font-size: 17px;
	  border: none;
	  background: #f1f1f1;
	  position: absolute;
	  top: 0;
	  left: 0;
	  width: 0; /* Initially width is 0, it will expand on click */
	  transition: width 0.3s ease; /* Add a transition effect */
	}

	/* Style for the search button */
	.search-container button {
	  margin-left: 10px;
	  margin-top: 6px;
	  padding: 10px;
	  font-size: 17px;
	  border: none;
	  background: #f1f1f1;
	  cursor: pointer;
	}
	
	/* Hover effect for the search button */
	.search-container button:hover {
	  background: rgb(2, 157, 224);
	}

	/* Expand the search input when active */
	.search-container.active input[type=text] {
	  display: block;
	  width: 150px; /* Adjust the width as needed */
	}
</style>
<!-- Include Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<form method="post" id="navi">
		<nav id="mynav" style="flex-direction: row;" class="topnav">
			<a onclick="submitForm('Home.jsp')">Home</a>
			<a onclick="submitForm('Login.jsp')">Inventory</a>
			<a onclick="submitForm('Aboutus.jsp')">About us</a>
			<a href="javascript:alert('About was clicked')">Contact us</a>
			<!-- Search bar -->
			<div class="search-container">
				<button type="button" onclick="toggleSearch()">
					<i class="fas fa-search"></i>
				</button>
				<input type="text" placeholder="Search...">
			</div>
		</nav>
	</form>
	
	<script>
	  function submitForm(action) {
	    var form = document.getElementById('navi');
	    form.action = action;
	    form.submit();
	  }

	  function toggleSearch() {
	    var searchContainer = document.querySelector('.search-container');
	    searchContainer.classList.toggle('active');
	  }
	</script>
</body>
</html>
