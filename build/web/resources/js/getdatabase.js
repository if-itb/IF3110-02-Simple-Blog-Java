/* function addcomment(id)
{
	var content = document.getElementById('ajaxcontent');
	var nama = document.getElementById('nama').value;
	var komentar = document.getElementById('komentar').value;
	var email = document.getElementById('email').value;	
	
	xmlhttp.onreadystatechange = function()
	{
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
		{
			content.innerHTML = xmlhttp.responseText;
		}
	};

	xmlhttp.open("GET","addcomment.php?id="+id+"&nama="+nama+"&komentar="+komentar+"&email="+email,true);
	xmlhttp.send();
} */

function ajaxRequest(){
 var activexmodes=["Msxml2.XMLHTTP", "Microsoft.XMLHTTP"] //activeX versions to check for in IE
 if (window.ActiveXObject){ //Test for support for ActiveXObject in IE first (as XMLHttpRequest in IE7 is broken)
  for (var i=0; i<activexmodes.length; i++){
   try{
    return new ActiveXObject(activexmodes[i])
   }
   catch(e){
    //suppress error
   }
  }
 }
 else if (window.XMLHttpRequest) // if Mozilla, Safari etc
  return new XMLHttpRequest()
 else
  return false
}

function loadpost(id)
{
	var xmlhttp = new ajaxRequest();
	var content = document.getElementById('ajaxcontent');
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			content.innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET", "getdatabase.php?id=" + id, true);
	xmlhttp.send();
}

function validatecomment()
{
	var email = document.getElementById('email').value;
	var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(email != "")
	{
		var valid = regex.test(email);
		var errormessage = document.getElementById('errormsg');
		
		if(valid)
		{
			errormessage.innerHTML="";
			var xmlhttp = new ajaxRequest();
			var content = document.getElementById('ajaxcontent');
			var nama = encodeURIComponent(document.getElementById('nama').value);
			var komentar = encodeURIComponent(document.getElementById('komentar').value);
			var id = encodeURIComponent(document.getElementById('pid').value);
			console.log(id);
			var email2 = encodeURIComponent(email);
			xmlhttp.onreadystatechange = function()
			{
				if(xmlhttp.readyState == 4 && ((xmlhttp.status == 200) || (window.location.href.indexOf("http")==-1)))
				{
					content.innerHTML = xmlhttp.responseText;
				}
			};

			xmlhttp.open("POST","addcomment.php",true);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlhttp.send("nama="+nama+"&komentar="+komentar+"&email="+email+"&pid="+id);
			
		}
		else
		{
			errormessage.innerHTML="Format email tidak valid";
		}
	}
}

