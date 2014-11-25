function submitcomment() {
	if(emailvalidator()) {
		comment_add();
		load_comment();
		document.getElementById("Nama").value = "";
		document.getElementById("Email").value = "";
		document.getElementById("Komentar").value = "";
	} 
	return false;
}

function initAjax(){
	var initAjax;
	if (window.XMLHttpRequest) {
		initAjax = new XMLHttpRequest();
	} else {
		initAjax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return initAjax;
}

function comment_add() {
	var ajax = initAjax();
	if(ajax) {
		var Nama = document.getElementById("Nama").value;
		var Email = document.getElementById("Email").value;
		var Komentar = document.getElementById("Komentar").value;
		var id = document.getElementById("id").value;
			ajax.onreadystatechange = function()
			{
				if(ajax.readyState == 4 && ajax.status == 200)
				{
					load_comment();
				}
			};
		ajax.open("GET","comment_processor.php?mode=add&id="+id+"&Nama="+Nama+"&Email="+Email+"&Komentar="+Komentar,true);
		ajax.send();

	}
}

function load_comment(id) {
    var pos = document.getElementById("comments");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","loadComments.jsp?id="+id,true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            pos.innerHTML = xmlhttp.responseText;
        }
    };
}	

function emailvalidator() {
	var email = document.getElementById("Email").value;
	var at = email.indexOf("@");
	var dot = email.lastIndexOf(".");
	if (at< 1 || dot<at+2 || dot+2>=email.length) {
	alert("Email tidak valid!");
	return false;
	}
	return true;
}

