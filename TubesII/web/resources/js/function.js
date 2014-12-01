function format_validation() {
    var pattern = /^\d{1,2}\/\d{1.2}\/\d{4}$/;
    if(!postarea.Tanggal.value.match(pattern)) {
        alert("Invalid date format");
        postarea.Tanggal.focus();
        return false;
    }
    return true;            
}

var data = new Date();

function convert_to_date() {
	var my_date = document.getElementById('postarea:Tanggal').value;
	var parts = my_date.split('/');
	var result = new Date(parts[2],parts[1]-1,parts[0],data.getHours(),data.getMinutes(),data.getSeconds());
    
	if (result.setHours(0,0,0,0) >= data.setHours(0,0,0,0)) {
		return true;
	}
	else {
		alert("Format tanggal salah, tanggal lebih kecil dari tanggal hari ini");
		return false;
	}
}

function validateEmail() {
   var email = document.getElementById('comment_input:email').value;
   var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

   if (filter.test(email)) {
       return true;
   }
   else {
       alert("Format email salah");
       return false;
   }
} 

function load_comment() {
    if (validateEmail()) {
                    var xmlhttp;
                    if (window.XMLHttpRequest) {
                        xmlhttp = new XMLHttpRequest();
                    }
                    else if (window.ActiveXObject) {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }

                    xmlhttp.onreadystatechange=function() {
                        if (xmlhttp.status == 200){
                            var s = xmlhttp.responseText;
                            var div = document.createElement('div');
                            div.innerHTML = s;                
                            var pos = document.getElementById("load");

                            if(pos.firstChild) {
                                pos.insertBefore(div,pos.firstChild);
                            }
                            else {
                                pos.appendChild(div);
                            }
                        }
                    }

                    xmlhttp.open("GET","load_comment",true);
                    xmlhttp.send();
    }
    else {
        alert ("Format email salah");
    }
}

function add_comment() {
        var nama = document.getElementById("Nama").value;
        var email = document.getElementById("Email").value;
        var comment = document.getElementById("Komentar").value;
        var id = document.getElementById("id-post").value;
        var date = new Date();
        var now = new Date(Date.UTC(date.getFullYear(),date.getMonth(),date.getDate(),date.getHours(),date.getMinutes(),date.getSeconds())).toISOString().slice(0, 19).replace('T', ' ');
        //date = date.getUTCFullYear() + '-' + ('00' + date.getUTCMonth()+1)).slice(-2) + '-' + ('00' + date.getUTCDate()).slice(-2) + ' ' + ('00' + date.getUTCHours()).slice(-2) + ':' + ('00' + date.getUTCMinutes()).slice(-2) + ':' + ('00' + date.getUTCSeconds()).slice(-2);
        if (validateEmail(email)==true) {
            var xhr;
            if (window.XMLHttpRequest) {
                xhr = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            }

            //var data1 = "nama=" + nama + "&email=" + email + "&komentar=" + comment;
            xhr.onreadystatechange=function() {
                    if ((xhr.status == 200) && (xhr.readyState == 4)){
                    	var s = xhr.responseText;
    					var div = document.createElement('div');
    					div.innerHTML = s;                
                        var pos = document.getElementById("comment");

                        if(pos.firstChild) {
                        	pos.insertBefore(div,pos.firstChild);
                        }
                        else {
                        	pos.appendChild(div);
                        }
                    }
                }
                xhr.open("POST","comment.php",true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.send("id=" + id + "&nama=" + nama + "&email=" + email + "&komentar=" + comment + "&tanggal=" + now);
        }
        else {
            alert("Email tidak sesuai format");
            return false;
        }
}
