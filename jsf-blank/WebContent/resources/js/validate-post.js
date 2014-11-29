function validateDateFormat(inputDate) {
	if (inputDate.length == 10) {
		var i;
		for (i=0; i<4; i++) {
			if (inputDate.charAt(i) < '0' || inputDate.charAt(i) > '9') {
				return false;
			}
		}
		if (inputDate.charAt(4) != '-') {
			alert ("karakter ke " + i + " salah!");
			return false;
		}
		for (i=5; i<6; i++) {
			if (inputDate.charAt(i) < '0' || inputDate.charAt(i) > '9') {
				alert ("karakter ke " + i + " salah!");
				return false;
			}
		}
		if (inputDate.charAt(7) != '-') {
			alert ("karakter ke " + i + " salah!");
			return false;
		}
		for (i=8; i<9; i++) {
			if (inputDate.charAt(i) < '0' || inputDate.charAt(i) > '9') {
				alert ("karakter ke " + i + " salah!");
				return false;
			}
		}
		return true;
	} else {
		return false;
	}
}
function isKabisat(tahun) {
	if (tahun % 400 == 0) {
		alert("kabisat");
		return true;
	} else if (tahun % 100 == 0) {
		alert("bukan kabisat");
		return false;
	} else if (tahun % 4 == 0) {
		alert("kabisat");
		return true;
	} else {
		alert("bukan kabisat");
		return false;
	}
}
function validateDateConstraints(inputDate) {
	var arr = inputDate.split("-");
	var tanggal = Number(arr[2]);
	var bulan = Number(arr[1]);
	var tahun = Number(arr[0]);
	
	if (bulan < 1 || bulan > 12) {
		return false;
	}
	if (bulan == 2) {
		var max;
		if (isKabisat(tahun) == true) {
			max = 29;
		} else {
			max = 28;
		}
		if (tanggal < 1 || tanggal > max) {
			return false;
		}
	} else if (bulan == 4 || bulan == 6 || bulan == 9 || bulan == 11) {
		if (tanggal < 1 || tanggal > 30) {
			return false;
		}
	} else {
		if (tanggal < 1 || tanggal > 31) {
			return false;
		}
	}
	return true;
}
function validateDateNow(inputDate, tanggal) {
	var dateNow = new Date();
	
	var tglPost = inputDate.substr(8,2);
	
	var blnPost = inputDate.substr(5,2);
	blnPost = parseInt(blnPost, 10);
	blnPost -= 1;
	
	var thnPost = inputDate.substr(0,4);
	var datePost = new Date();
	
	datePost.setFullYear(thnPost, blnPost, tglPost);
	
	if (datePost < dateNow) {
		alert("Tanggal tidak boleh kurang dari sekarang!");
		document.getElementById(tanggal).focus();
		return false;
	} else {
		return true;
	}
}
function validateDate(inputDate, tanggal) {
	inputDate = inputDate.trim();
	if (validateDateFormat(inputDate) == true) {
		if (validateDateConstraints(inputDate) == false) {
			alert ("Tanggal salah!");
			document.getElementById(tanggal).focus();
			return false;
		} else {
			return validateDateNow(inputDate, tanggal);			
		}
	} else {
		alert ("Format tanggal (YYYY-MM-DD) ");
		document.getElementById(tanggal).focus();
		return false;
	}
}
function validatePost(judul, konten, tanggal) {	
	var judulPost = document.getElementById(judul).value;
	if (judulPost == "") {
		alert("Judul tidak boleh kosong!");
		document.getElementById(judul).focus();
		return false;
	}
	
	var kontenPost = document.getElementById(konten).value;
	if (kontenPost == "") {
		alert("Konten tidak boleh kosong!");
		document.getElementById(konten).focus();
		return false;
	}
	
	var inputDate = document.getElementById(tanggal).value;
	return validateDate(inputDate, tanggal);
}