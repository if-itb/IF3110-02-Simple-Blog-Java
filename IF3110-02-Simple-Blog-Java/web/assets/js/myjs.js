function validateEmail() {
	var email = document.getElementById("email").value;
	var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var result = pattern.test(email);   
	
	if (!result) {      
		document.getElementById("email").style.backgroundColor = "#ffaaaa";
		document.getElementById("submitKomentar").disabled = true;
	} else {
		document.getElementById("email").style.backgroundColor = "#aaffaa";
		document.getElementById("submitKomentar").disabled = false;
	}
	return result;
}

function sendComment() {
	var idPost = encodeURIComponent(document.getElementById("id").value);
	var nama = encodeURIComponent(document.getElementById("nama").value);
	var email = encodeURIComponent(document.getElementById("email").value);
	var komentar = encodeURIComponent(document.getElementById("komentar").value);   
	
	if (!validateKomentar(nama, email, komentar)) {
		return false;
	}

	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			existingComment = document.getElementById("komentar-placeholder").innerHTML;
			document.getElementById("komentar-placeholder").innerHTML = xmlhttp.responseText;
			
                        if (!document.getElementById("nama").disabled)
                            document.getElementById("nama").value = '';
                        if (!document.getElementById("email").disabled)
                            document.getElementById("email").value = '';
			document.getElementById("komentar").value = '';
		}
	}
					
	var parameters = "idpost=" + idPost + "&nama=" + nama + "&email=" + email + "&komentar=" + komentar;
	xmlhttp.open("POST", "SaveComment", true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");    
	xmlhttp.send(parameters);
}

window.onload = loadComment();

function loadComment() {
	var idPost = encodeURIComponent(document.getElementById("id").value);

	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById("komentar-placeholder").innerHTML = xmlhttp.responseText;
		}
	}
					
	var parameters = "idpost=" + idPost;
	xmlhttp.open("GET", "LoadComment?" + parameters, true);    
	xmlhttp.send(null);
}

function validateKomentar(nama, email, komentar) {
	if (isEmpty(nama)) {
		alert('Kolom nama tidak boleh kosong');
		return false;
	}
	if (isEmpty(email)) {
		alert('Kolom email tidak boleh kosong');
		return false;
	}
	if (isEmpty(komentar)) {
		alert('Kolom komentar tidak boleh kosong');
		return false;
	}
	if (!validateEmail()) {
		alert('Email tidak valid');
		return false; 
	}
	
	return true;
}

function confirmDeletion(postId) {
	var confirm = window.confirm("Apakah Anda yakin menghapus post ini secara permanen?");
	if (confirm == true) {
		window.location = "DeletePost?id=" + postId;
	} else {
		return false;
	}
}

function confirmSoftDeletion(postId) {
	var confirm = window.confirm("Apakah Anda yakin menghapus post ini?");
	if (confirm == true) {
		window.location = "SoftDelete?id=" + postId;
	} else {
		return false;
	}
}

function confirmRemoveImagePost(postId) {
	var confirm = window.confirm("Apakah Anda yakin menghapus image post ini?");
	if (confirm == true) {
		window.location = "RemoveImagePost?id=" + postId;
	} else {
		return false;
	}
}

function validate() {
	var judul = document.getElementById("judul").value;        
	var content = document.getElementById("konten").value;
	var validDateFormat = true;
	var rawDate, tanggal;
	try {
		rawDate = document.getElementById("tanggal").value.split("/");
		if (rawDate[2] < 1990 || rawDate[2] > 2099 || rawDate[0] < 0 || rawDate[0] > 12 || rawDate[1] < 0 || rawDate[1] > 31) {
			validDateFormat = false;
		} else {
			tanggal = new Date(rawDate[2], rawDate[0] - 1, rawDate[1]);
		}
	} catch(exception) {
		validDateFormat = false;
	}
	var today = new Date();

	if (isEmpty(judul)) {
		alert("Judul tidak boleh kosong");
		return false;            
	}
	if (validDateFormat) {            
		if (!compareDate(tanggal, today)) {
			alert("Format tanggal tidak valid");
			return false;
		}
	} else {
		alert("Format tanggal tidak valid");        
		return false;
	}
	if (isEmpty(content)) {
		alert("Konten tidak boleh kosong");  
		return false;
	}

	return true;
}

function compareDate(tanggal, today) {        
	if (tanggal.getFullYear() > today.getFullYear())
		return true;
	if (tanggal.getFullYear() < today.getFullYear())
		return false;
	if (tanggal.getMonth() > today.getMonth())
		return true;
	if (tanggal.getMonth() < today.getMonth())
		return false;
	if (tanggal.getDate() >= today.getDate())
		return true;
	if (tanggal.getDate() < today.getDate())
		return false;
}

function isEmpty(val){
	return (val === undefined || val == null || val.length <= 0) ? true : false;
}