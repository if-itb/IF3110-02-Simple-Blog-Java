function validateEmail() {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var length_of_name = document.getElementById("name").value.length;
    var email = document.getElementById("email").value;
    if (length_of_name > 30) {
        alert("Name is too long, max 30 char");
    }
    if (re.test(email)) {
        return true;
    } else {
        alert("Wrong email format");
        return false;
    }
}