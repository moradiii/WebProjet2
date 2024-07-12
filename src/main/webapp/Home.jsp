<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
<link rel="icon" href="img\Logo.png" type="image/png">
<link rel="stylesheet" href="css/stylesheet.css">
<style>
	h2 {
		cursor: pointer;
	}
</style>
</head>
<body>
<jsp:include page="Nav.jsp" />
  <div class="slideshow-container" style="background-color: black;">
    <div class="slide">
      <img src="img/product1.jpg" alt="Image 1">
    </div>
    <div class="slide">
      <img src="img/product2.jpg" alt="Image 2">
    </div>
    <div class="slide">
      <img src="img/product4.jpg" alt="Image 3">
    </div>
    <div class="text"><center><h2>We pride ourselves with<br>our top tier products and<br> design ideas.</h2></div>
  </div>
  
  <div>
  	<div>
  		<center>
  		<h1 class="title">EXPLORE MORE WITH STRUGAL</h1>
  	</div>
  	<br>
  	<div class="container1">
  		 <img class="img" src="img/container1.jpg" alt="pic1">
  		 <center>
  		 <h2 class="hov" id="architecture">ARCHITECTURE</h2>
  		 <p>Innovative solutions for building and<br>
  		  construction. We are a benchmark in aluminum<br>
  		  and PVC carpentry systems, facades, doors and<br>
  		  sun protection for architects and developers.</p>
  	</div>
  	
  	<div class="container3">
  		<img class="img" src="img/container2.jpg" alt="pic2">
  		 <center>
  		 <h2 class="hov" id="industry">INDUSTRY</h2>
  		 <p>Tailor-made solutions that guarantee<br>
  		  exclusivity. Our R+D+i team designs aluminum<br>
  		  profiles in collaboration with each client to<br>
  		  satisfy the needs of each project.</p>
  	</div>
  	
  	<div class="container2">
  		<img class="img" src="img/container3.jpg" alt="pic3">
  		 <center>
  		 <h2 class="hov" id="individuals">INDIVIDUALS</h2>
  		 <p>Aluminum windows, PVC windows and<br>
  		 aluminum doors ready to install. Finished<br>
  		 products that guarantee design and comfort<br>
  		 at home.<br>
  		 100% Strugal Guarantee.</p>
  	</div>
  </div>
  <script src="js/script.js"></script>
  <script>
	document.getElementById("architecture").addEventListener("click", function() {
	    window.location.href = "https://www.strugal.com/en/architecture";
	});
	
	document.getElementById("industry").addEventListener("click", function() {
	    window.location.href = "https://www.strugal.com/en/industry";
	});
	
	document.getElementById("individuals").addEventListener("click", function() {
	    window.location.href = "https://www.strugal.com/en/individuals";
	});
  </script>
  
</body>
</html>