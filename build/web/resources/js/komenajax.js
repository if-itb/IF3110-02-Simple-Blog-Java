function submitcomment(id) {
	if(emailValidatorKomen()) {
		comment_add(id);
		load_comment(id);
		document.getElementById("nama").value = "";
		document.getElementById("email").value = "";
		document.getElementById("komentar").value = "";
	} 
	return false;
}

function comment_add(id) {
                var Nama = document.getElementById("nama").value;
		var Email = document.getElementById("email").value;
		var Komentar = document.getElementById("komentar").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET","postComment.jsp?id="+id+"&Nama="+Nama+"&Email="+Email+"&Komentar="+Komentar,true);
		xmlhttp.send();
			xmlhttp.onreadystatechange = function()
			{
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
				{
					load_comment(id);
				}
			};
		

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

function emailValidatorKomen()
{
    email = document.getElementById("email").value;
    if(email.length > 0)
    {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var result = re.test(email);
        var ganti = document.getElementById("ganti-error-message");
        if(!result)
        {
            ganti.innerHTML= '<div class="alert alert-danger">wrong email format</div>';
        }
        else
        {
            ganti.innerHTML = "";
        }
        return result;
    }
    else
    {
        return false;
    }
}