const form = document.getElementById("form");
const submitBtn = document.getElementById("submit");
const fname = document.getElementById("fname");
const nick = document.getElementById("nick");
const country = document.getElementById("country");
const city = document.getElementById("city");
const zip_code = document.getElementById("zip_code");
const privacy_law = document.getElementById("privacy_law");

function nameError() {
  if (fname.value === undefined || fname.value === "") {
    fname.setAttribute("placeholder", "Pole nie może być puste. Wypełnij je.");
    fname.style.border = "1px solid red";
  }
}
function nickError() {
  if (nick.value === undefined || nick.value === "") {
    nick.setAttribute("placeholder", "Pole nie może być puste. Wypełnij je.");
    nick.style.border = "1px solid red";
  }
}
function countryError() {
  if (country.value === undefined || country.value === "") {
    country.setAttribute(
      "placeholder",
      "Pole nie może być puste. Wypełnij je."
    );
    country.style.border = "1px solid red";
  }
}
function cityError() {
  if (city.value === undefined || city.value === "") {
    city.setAttribute("placeholder", "Pole nie może być puste. Wypełnij je.");
    city.style.border = "1px solid red";
  }
}
function zipError() {
  if (zip_code.value === undefined || zip_code.value === "") {
    zip_code.setAttribute(
      "placeholder",
      "Pole nie może być puste. Wypełnij je."
    );
    zip_code.style.border = "1px solid red";
  }
}
function privacyError() {
  if (privacy_law.value === undefined || privacy_law.value === "") {
    privacy_law.setAttribute(
      "placeholder",
      "Pole nie może być puste. Wypełnij je."
    );
    privacy_law.style.border = "1px solid red";
  }
}

function sendForm() {
  if (
    fname?.value.trim() &&
    nick?.value.trim() &&
    country?.value.trim() &&
    city?.value.trim() &&
    zip_code?.value.trim() &&
    privacy_law?.checked
  ) {
    submitBtn.disabled = false;
  } else {
    submitBtn.disabled = true;
  }
}

form?.addEventListener("input", nameError);
form?.addEventListener("input", nickError);
form?.addEventListener("input", countryError);
form?.addEventListener("input", cityError);
form?.addEventListener("input", zipError);
form?.addEventListener("input", privacyError);
form?.addEventListener("change", sendForm);