const slides = document.querySelector(".slide");
const gallery = document.querySelector(".image-gallery ul");
let slideIndex = 0;
let intervalId = null;

document.addEventListener("DOMContentLoaded", initializeSlider);
slides.innerHTML = `<img class="displaySlide" src="./Images/Coffee0.jpg">`;

for (let i = 0; i < 10; i++) {
  gallery.innerHTML += `<img onclick="imageClick(${i})" src="./Images/Coffee${i}.jpg">`;
}

const galleryImage = document.querySelectorAll(".image-gallery img");

function initializeSlider() {
  if (galleryImage.length > 0) {
    galleryImage[slideIndex].classList.add("displaySlide");
    intervalId = setInterval(nextClick, 2000);
  }
}

function showSlide(index) {
  if (index >= galleryImage.length) {
    slideIndex = 0;
  } else if (index < 0) {
    slideIndex = galleryImage.length - 1;
  }

  galleryImage.forEach((slide) => {
    slide.classList.remove("displaySlide");
  });
  galleryImage[slideIndex].classList.add("displaySlide");
  slides.innerHTML = `<img class="displaySlide" src="./Images/Coffee${slideIndex}.jpg">`;
}

imageClick = (index) => {
  slideIndex = index;
  slides.innerHTML = `<img class="displaySlide" src="./Images/Coffee${index}.jpg">`;
};

function prevClick() {
  slideIndex--;
  showSlide(slideIndex);
}

function nextClick() {
  slideIndex++;
  showSlide(slideIndex);
}
