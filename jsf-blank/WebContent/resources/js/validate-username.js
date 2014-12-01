function validateUsername(usernameID) {
	var username = document.getElementById(usernameID).value;
	if (username.length <= 0) {
		alert("Username tidak boleh kosong!");
		return false;
	}
	if(!cekString(username)) {
		alert("Username hanya bisa menggunakan alfabet dan angka");
		return false;
	}
	else {
		return true;
	}
}

function cekString(username) {	
	for (var i=0; i<username.length; i++) {
		var cek = username.charCodeAt(i);
		if ( !((cek >= 48 && cek <= 80) || (cek >=65 && cek <= 90) || (cek >= 97 && cek <= 122)) )
			return false;			
	}
	return true;	
} 