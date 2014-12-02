function SubmitComment(post_ID){
	if (VerifyEmail(document.getElementById('Email').value)) {
		 if (window.XMLHttpRequest) {
			xmlHttpObj = new XMLHttpRequest( );
		} else {
			try {
				xmlHttpObj = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					xmlHttpObj = false;
				}
			}
		}

		var userid = document.getElementById('Userid').value;
		alert(userid);
		var author = document.getElementById('Nama').value;
		alert(author);
		var email = document.getElementById('Email').value;
		alert(email);
		var comment = document.getElementById('Komentar').value;
		alert(comment);

		//submit
		var param = "post_ID=" + userid + "&author=" + author + "&email=" + email + "&comment=" + comment;
		xmlHttpObj.open("GET", "Comment.jsp?post_ID=" + userid + "&author=" + author + "&email=" + email + "&comment=" + comment, true);  
//		xmlhttpObj.send(param);
		xmlHttpObj.setRequestHeader("Content-type", "application/x-www-forum-urlencoded");
		alert("sent probably :v");
		xmlHttpObj.onreadystatechange = function() {
			if (xmlHttpObj.readyState == 4 && xmlHttpObj.status == 200) {
				alert("sent probably II :v");
				document.getElementById("yangmaudiajaks").innerHTML = xmlHttpObj.responseText;
			}
			
		}
		alert("sent probably :v");
	//	return false;
	}
	else {
		alert("Invalid comment. Please fix~ :3");
	}
}

function VerifyEmail(email){
	var emailRegex = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/g;
	return (emailRegex.test(email));
}