window.onload = get_comments

function get_comments() {
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("comments_part").innerHTML = xmlhttp.responseText;
		}
	}

	var getter = "CommentGetter?id=";
	getter = getter.concat(document.getElementById("inputID").value);
	xmlhttp.open("GET", getter, true);
	xmlhttp.send();
}

function checkEmail() {
	var email = document.getElementById("email_area").value;
	var email_patt = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var result = email_patt.test(email);

	return result;
}

function myfunction() {
	var isEmailValid = checkEmail();
	var errorDOM = document.getElementById("error_area");

	if (isEmailValid) {
		errorDOM.innerHTML = "";

		// konek ajax ke server
		var id_tag = document.getElementById("inputID")
		var name_tag = document.getElementById("inputNama");
		var email_tag = document.getElementById("email_area");
		var content_tag = document.getElementById("inputKomentar");

		var id_v = encodeURIComponent(id_tag.value);
		var name_v = encodeURIComponent(name_tag.value);
		var email_v = encodeURIComponent(email_tag.value);
		var content_v = encodeURIComponent(content_tag.value);

		name_tag.value = "";
		email_tag.value = "";
		content_tag.value = "";

		var to_send = "id=" + id_v + "&name=" + name_v + "&email=" + email_v
				+ "&content=" + content_v;

		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText === "SUCCESS") {
					get_comments();
				}
			}
		}

		xmlhttp.open("POST", "CommentPoster", true);
		xmlhttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

		xmlhttp.send(to_send);

	} else {
		errorDOM.innerHTML = "Email anda tidak valid";
	}
}

function konfirmasi() {
	var tanya = confirm('Apakah anda yakin menghapus post ini?');
	if (tanya == true)
		return true;
	else
		return false;
}