const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const formSignup = document.getElementById("formSignUp");
const formlogin = document.getElementById("formLogin");

const container = document.getElementById('container');



signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});
signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

formSignup.addEventListener("submit", (e) => e.preventDefault());
formlogin.addEventListener("submit", (e) => e.preventDefault());
