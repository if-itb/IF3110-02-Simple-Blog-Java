function deleteConfirmation(id) {
	var isDelete = confirm("Apakah Anda yakin menghapus post ini?");
        var base_url = window.location.origin;
	if (isDelete) {
		window.location.href = base_url+"/SimpleBlog/delete_post?id="+id;
	} 
}

function deleteUserConfirmation(id) {
	var isDelete = confirm("Apakah Anda yakin menghapus user ini?");
        var base_url = window.location.origin;
	if (isDelete) {
		window.location.href = base_url+"/SimpleBlog/delete_user?id="+id;
	} 
}

function validateEmail() {
	var filter = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var email = document.getElementById("Email").value;
	if (filter.test(email)) {
		return true;
	} else {
		return false;
	}
}

function loadComments() {
	var postID = location.search.split('id=')[1];
        var base_url = window.location.origin;
	var url = base_url+"/SimpleBlog/load_comments?id_post="+postID;

	var httpRequest;
	if (window.XMLHttpRequest) { // Mozilla, Safari, ...
	    httpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE 8 and older
	    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}

	httpRequest.onreadystatechange = function(){
	    if (httpRequest.readyState==4 && httpRequest.status==200) {
	    	document.getElementById("comments-area").innerHTML = httpRequest.responseText;
	    };
	};

	httpRequest.open('GET', url);
    httpRequest.send();
}

function addComment() {
	var id_post = encodeURIComponent(document.getElementById("id_post").value);
	var nama = encodeURIComponent(document.getElementById("Nama").value);
	var email = encodeURIComponent(document.getElementById("Email").value);
	var komentar = encodeURIComponent(document.getElementById("Komentar").value);

	var namaerror = document.getElementById("namaerror");
	var emailerror = document.getElementById("emailerror");
	var komentarerror = document.getElementById("komentarerror");

	if (nama == '' || email == '' || komentar == '' || (!validateEmail(email))) {
		var namavalidation = true;
		var emailvalidation = true;
		var komentarvalidation = true;

		var namaerror = document.getElementById("namaerror");
		var emailerror = document.getElementById("emailerror");
		var komentarerror = document.getElementById("komentarerror");

		if (nama == '') {			
			namaerror.innerHTML = "Nama tidak boleh kosong";
			namavalidation = false;
			namaerror.removeAttribute("class");
			namaerror.setAttribute("class","error");
		} else { namaerror.innerHTML = ""; namaerror.removeAttribute("class");}
		if (email == '') {
			emailerror.innerHTML = "Email tidak boleh kosong";
			emailvalidation = false;
			emailerror.removeAttribute("class");
			emailerror.setAttribute("class","error");
		} else if (!validateEmail(email)) {
			emailerror.innerHTML = "Email tidak valid";
			emailvalidation = false;
			emailerror.removeAttribute("class");
			emailerror.setAttribute("class","error");
		} else { emailerror.innerHTML = ""; emailerror.removeAttribute("class");}
		if (komentar == '') {
			komentarerror.innerHTML = "Komentar tidak boleh kosong";
			komentarvalidation = false;
			komentarerror.removeAttribute("class");
			komentarerror.setAttribute("class","error");
		} else { komentarerror.innerHTML = ""; komentarerror.removeAttribute("class");}

		//focus
		if (!namavalidation) {
			document.getElementById("Nama").focus();
		} else if (!emailvalidation) {
			document.getElementById("Email").focus();
		} else if (!komentarvalidation) {
			document.getElementById("Komentar").focus();
		}

	} else {
		namaerror.innerHTML = ""; namaerror.removeAttribute("class");
		emailerror.innerHTML = ""; emailerror.removeAttribute("class");
		komentarerror.innerHTML = ""; komentarerror.removeAttribute("class");

		var data = "id_post="+id_post+"&nama="+nama+"&email="+email+"&komentar="+komentar;
		var base_url = window.location.origin;
                var url = base_url+"/SimpleBlog/add_comment";

		var httpRequest;
		if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		    httpRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE 8 and older
		    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}

		httpRequest.onreadystatechange = function(){
		    if (httpRequest.readyState==4 && httpRequest.status==200) {
		    	document.getElementById("Nama").value = '';
				document.getElementById("Email").value = '';
				document.getElementById("Komentar").value = '';
				document.getElementById("namaerror").innerHTML = "";
				document.getElementById("namaerror").removeAttribute("class");
				document.getElementById("emailerror").innerHTML = "";
				document.getElementById("emailerror").removeAttribute("class");
				document.getElementById("komentarerror").innerHTML = "";
				document.getElementById("komentarerror").removeAttribute("class");
				
		    	var comments = document.getElementById('comments-area');
				comments.innerHTML = httpRequest.responseText + comments.innerHTML;
		    };
		};

		httpRequest.open('POST', url);
	    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    httpRequest.send(data);
	}

	return false;
}

function altAddComment() {
	var id_post = encodeURIComponent(document.getElementById("id_post").value);
	var nama = encodeURIComponent(document.getElementById("Nama").value);
	var email = encodeURIComponent(document.getElementById("Email").value);
	var komentar = encodeURIComponent(document.getElementById("Komentar").value);

	var komentarerror = document.getElementById("komentarerror");

	if (komentar == '') {
		var komentarvalidation = true;
		var komentarerror = document.getElementById("komentarerror");
                
		if (komentar == '') {
			komentarerror.innerHTML = "Komentar tidak boleh kosong";
			komentarvalidation = false;
			komentarerror.removeAttribute("class");
			komentarerror.setAttribute("class","error");
		} else { komentarerror.innerHTML = ""; komentarerror.removeAttribute("class");}

		//focus
                if (!komentarvalidation) {
			document.getElementById("Komentar").focus();
		}

	} else {
		
		komentarerror.innerHTML = ""; komentarerror.removeAttribute("class");

		var data = "id_post="+id_post+"&nama="+nama+"&email="+email+"&komentar="+komentar;
		var base_url = window.location.origin;
                var url = base_url+"/SimpleBlog/add_comment";

		var httpRequest;
		if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		    httpRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE 8 and older
		    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}

		httpRequest.onreadystatechange = function(){
		    if (httpRequest.readyState==4 && httpRequest.status==200) {
		    	
				document.getElementById("Komentar").value = '';

				document.getElementById("komentarerror").innerHTML = "";
				document.getElementById("komentarerror").removeAttribute("class");
				
		    	var comments = document.getElementById('comments-area');
				comments.innerHTML = httpRequest.responseText + comments.innerHTML;
		    };
		};

		httpRequest.open('POST', url);
	    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    httpRequest.send(data);
	}

	return false;
}

function parseDate(date) {
	var valid = date.match(/^(\d{1,2})-(\d{1,2})-(\d{4})$/);
	return valid;
}

function validatePost() {
	var judul = document.getElementById("Judul").value;        
	var tanggal = document.getElementById("Tanggal").value;
	var konten = document.getElementById("Konten").value;
	
 	if (judul == "") {
 		alert("Judul tidak boleh kosong");
 		return false;
 	}
 	if (tanggal == "") {
 		alert("Tanggal tidak boleh kosong");
 		return false;
 	} else {
 		if (parseDate(tanggal)) {
	 		var date = tanggal.split("-");
	 		var postDate = new Date(date[2],date[1]-1,date[0]);
	 		var today = new Date();

	 		var isGreaterThanToday = true;

	 		if (postDate.getFullYear() < today.getFullYear()) {
	 			isGreaterThanToday = false;
	 		} else if (postDate.getFullYear() == today.getFullYear()) {
		 		if (postDate.getMonth() < today.getMonth()) {
		 			isGreaterThanToday = false;
		 		} else if (postDate.getMonth() == today.getMonth()) {
		 			if (postDate.getDate() < today.getDate()) {
		 				isGreaterThanToday = false;
		 			}
		 		}
		 	}

	 		if (!isGreaterThanToday) {
	 			alert("Tanggal harus lebih besar atau sama dengan tanggal sekarang");
	 			return false;
	 		}
	 	} else {
	 		alert("Format tanggal harus DD-MM-YYYY");
	 		return false;
	 	}
 	}
 	if (konten == "") {
 		alert("Konten tidak boleh kosong");
 		return false;
 	}


 	return true;
}

function altValidatePost(form) {
	var judul = form[form.id + ":Judul"].value;        
	var tanggal = form[form.id + ":Tanggal"].value;
	var konten = form[form.id + ":Konten"].value;
	
 	if (judul == "") {
 		alert("Judul tidak boleh kosong");
 		return false;
 	}
 	if (tanggal == "") {
 		alert("Tanggal tidak boleh kosong");
 		return false;
 	} else {
 		if (parseDate(tanggal)) {
	 		var date = tanggal.split("-");
	 		var postDate = new Date(date[2],date[1]-1,date[0]);
	 		var today = new Date();

	 		var isGreaterThanToday = true;

	 		if (postDate.getFullYear() < today.getFullYear()) {
	 			isGreaterThanToday = false;
	 		} else if (postDate.getFullYear() == today.getFullYear()) {
		 		if (postDate.getMonth() < today.getMonth()) {
		 			isGreaterThanToday = false;
		 		} else if (postDate.getMonth() == today.getMonth()) {
		 			if (postDate.getDate() < today.getDate()) {
		 				isGreaterThanToday = false;
		 			}
		 		}
		 	}

	 		if (!isGreaterThanToday) {
	 			alert("Tanggal harus lebih besar atau sama dengan tanggal sekarang");
	 			return false;
	 		}
	 	} else {
	 		alert("Format tanggal harus DD-MM-YYYY");
	 		return false;
	 	}
 	}
 	if (konten == "") {
 		alert("Konten tidak boleh kosong");
 		return false;
 	}


 	return true;
}