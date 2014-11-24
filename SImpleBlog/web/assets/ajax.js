/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function LoadCommentList(PostID){
	var xmlhttp;

	if (window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}else{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			document.getElementById("comment-list").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","loadcomments.jsp?ID=" + PostID,true);
	xmlhttp.send();	
}

