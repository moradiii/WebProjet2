/**
 * 
 */
const slides = document.querySelectorAll('.slide');
	  let currentSlide = 0;
	
	  function showSlide(n) {
	    slides[currentSlide].classList.remove('active');
	    currentSlide = (n + slides.length) % slides.length;
	    slides[currentSlide].classList.add('active');
	  }
	
	  function nextSlide() {
	    showSlide(currentSlide + 1);
	  }
	
	  // Start the slideshow
	  showSlide(currentSlide);
	  setInterval(nextSlide, 4000);