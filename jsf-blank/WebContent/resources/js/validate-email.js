function hitungAt(email) {
	var i;
	var count=0;
	for (i=0; i < email.length; i++) {
		if (email.charAt(i) == '@') {
			count++;
		}
	}
	return count;
}
function cekEmail(email) {
	var at = email.indexOf("@");
	var dot = email.lastIndexOf(".");
	if (hitungAt(email) > 1 || at < 1 || dot < at+2 || dot+2 > email.length) {
		return false;
	}
	return true;
}
function validateEmail(elementID) {
	var email = document.getElementById(elementID).value;
	if(email.length > 0) {
		var b = cekEmail(email);
		if(!b) {
			alert("Email salah!");
		}
		return b;
	} else {
		return false;
	}
}
function tes() {
	alert("Oi!");
	return false;
}