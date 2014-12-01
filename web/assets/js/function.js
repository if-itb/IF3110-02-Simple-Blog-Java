function confirmDelete() {
    var result = confirm('Apakah Anda yakin menghapus post ini?');
    if (result==true) {
        return true;
    }
    else {
        return false;
    }
}

function comment() {
  var nameField = document.forms["commentForm"]["Nama"].value;
  var emailField = document.forms["commentForm"]["Email"].value;
  var commentField = document.forms["commentForm"]["Komentar"].value;
  if ((nameField=="")||(nameField==null)||(emailField=="")||(emailField==null)||(commentField=="")||(commentField==null)) {
    alert("Error : Form harus diisi lengkap!");
    return false;
  }
  else {
    if (validateEmail()){
      var idPost = document.getElementById('PostId').value;
      saveComment(idPost);
      document.forms["commentForm"]["Komentar"].value="";
      return false;
    }
    else {
      return false;
    }
  }
}

function validateEmail() {
    var x = document.forms["commentForm"]["Email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
    else {
        return true;
    }
}

function IsValidDate() {
    var f = document.forms["Post"]["Tanggal"].value;
var string = f.split("-");
    var now = new Date();
    var date = new Date();
    date.setFullYear(string[0],string[1]-1,string[2]);
    if(date < now){
        alert("Input tanggal tidak valid! (harus >= now)");
        return false;
    }
    else{
        return true;
    }
}

function loadComment(str) {
  if (window.XMLHttpRequest) {
    xmlhttp=new XMLHttpRequest();
  } else {
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function() {
    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
      document.getElementById("Comment").innerHTML=xmlhttp.responseText;
    }
  }
  xmlhttp.open("GET","comment.jsp?id="+str,true);
  xmlhttp.send();
}

function saveComment(str) {
  var xmlhttp;
  var Nama = document.forms["commentForm"]["Nama"].value;
  var Email = document.forms["commentForm"]["Email"].value;
  var Komentar = document.forms["commentForm"]["Komentar"].value;
  if (window.XMLHttpRequest) {
    xmlhttp=new XMLHttpRequest();
  } else {
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function() {
    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
        var idPost = document.getElementById('PostId').value;
        loadComment(idPost);
    }
  }
  xmlhttp.open("POST","insertcomment.jsp",true);
  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
  xmlhttp.send("id="+str+"&nama="+Nama+"&email="+Email+"&komentar="+Komentar);
}