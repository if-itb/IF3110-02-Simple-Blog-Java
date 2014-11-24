function Comment(ID) {

	var nama = document.getElementById('Nama').value;
	var email = document.getElementById('Email').value;
	var komentar = document.getElementById('Komentar').value;
	
	if (validate(nama ,email, komentar))
	{
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		} else { // code for IE6, IE5
			
		}
		xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4 && xmlhttp.status==200) {
				document.getElementById("ajaxComment").innerHTML=xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET","comment.php?ID="+ ID +"&Nama="+ nama +"&Komentar=" + komentar,true);
		xmlhttp.send();
	}
	else
	{
		return false;
	}
}

function showComment(ID) {

	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	} else { // code for IE6, IE5
		
	}
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById("ajaxComment").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","show_comment.php?ID=" + ID,true);
	xmlhttp.send();
}

function validate(nama, email, komentar)
{
	if (nama=="")
	{
		alert('Nama harus di isi!');
		return false;
	}
	
	if (komentar == "")
	{
		alert('Komentar harus di isi');
		return false;
	}
	
	if (!validateEmail(email))
	{
		alert('Email tidak valid');
		return false;
	}
	
	document.forms["comment_form"].reset();
	return true;
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 