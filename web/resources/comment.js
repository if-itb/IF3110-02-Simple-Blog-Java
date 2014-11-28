function addComment(){
	var xmlhttp = getXMLHTTPObject();
	var post_id = document.forms['commentForm']['post_id'].value;
	var name = document.forms['commentForm']['user_id'].value;
	var email = document.forms['commentForm']['email'].value;
	var content = document.forms['commentForm']['content'].value;
	var param = "post_id="+post_id+"&user_id="+name+"&email="+email+"&content="+content;
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4){
			loadComment();
			resetForm();
		}
	}
	xmlhttp.open("POST", "add_comment.jsp", true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send(param);
}

function getXMLHTTPObject(){
    var xmlhttp;
    if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest();
    }
    else{// code for IE6, IE5
      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

function loadComment(){
	var xmlhttp = getXMLHTTPObject();
    var post_id = document.forms['commentForm']['post_id'].value;
    var param = "post_id="+post_id;
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState == 4){
            document.getElementById("comment").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("POST", "load_comment.jsp", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(param);
}

function validateEmail() {
	var email = document.forms["commentForm"]["email"].value;
    var re = /[\w\d\._-]+@[\w\d]+\.\w+/;
	if (re.test(email)){
		document.getElementById("Email").style.background = "lime";
        document.getElementById("Email").style.color = "white"; return true;
	}
	else{
		document.getElementById("Email").style.background = "red";
		document.getElementById("Email").style.color = "white"; return false;}
}

function resetForm(){
    document.forms['commentForm']['content'].value = "";
}