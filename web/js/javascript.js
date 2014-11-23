function LoginAppear() {
    document.getElementById("logindiv").style.display= "block";
}

function LoginDisappear() {
    document.getElementById("logindiv").style.display= "none";
}

function LoginValidate() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username === "" || password === "") {
        alert("Username or Password doesn't match");
    } else {
        LoginDisappear();
    }
}