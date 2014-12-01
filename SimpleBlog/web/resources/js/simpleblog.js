function addComment(url, comment, postId) {
  if (!validateComment(comment)) {
    return false;
  }

  var xmlhttp= window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      var loadUrl = url.replace("new", "list") + "/" + postId;
      loadComment(loadUrl);
    }
  }  
          
  var parameters = "postid=" + postId + "&name=" + comment.Nama.value + "&email=" + comment.Email.value + "&content=" + comment.Komentar.value;
  xmlhttp.open("POST", url, true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");    
  xmlhttp.send(parameters);
}

function loadComment(url) {
  var xmlhttp= window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
      document.getElementById("comments").innerHTML = xmlhttp.responseText;
      document.getElementById("Nama").value = '';
      document.getElementById("Email").value = '';
      document.getElementById("Komentar").value = '';
    }
  }
          
  xmlhttp.open("GET", url, true);    
  xmlhttp.send();
}

function validatePost() {
  console.log("validatePost is called");
  judul = document.getElementById("NewPost:Judul");
  tanggal = document.getElementById("NewPost:Tanggal");
  konten = document.getElementById("NewPost:Konten");
    
  if (isBlank(judul.value)) {
    alert("Judul Tidak Boleh Kosong");
    return false;
  }

  if (isBlank(tanggal.value)) {
    alert("Tanggal Tidak Boleh Kosong");
    return false;
  }

  if (isBlank(konten.value)) {
    alert("Konten Tidak Boleh Kosong");
    return false;
  } 

  var regex = /^(\d{1,2})-(\d{1,2})-(\d{4})$/
  if (!regex.test(tanggal.value)) {
    alert("Format Tanggal Harus dd-mm-yyyy");
    return false;
  }

  var dd = tanggal.value.split("-")[0];
  var mm = tanggal.value.split("-")[1];
  var yy = tanggal.value.split("-")[2];
  var date = new Date(yy, mm - 1, dd);
  if ((date.getDate() != dd) || (date.getMonth() + 1 != mm) || (date.getFullYear() != yy)) {
    alert("Angka Yang Dimasukkan Bukanlah Tanggal");
    return false;
  }

  var today = new Date();
  if (today > date) {
    alert("Tanggal Harus Lebih Dari Hari Ini");
    return false;
  }
  
  return true;
}

function validateComment() {
      console.log("validateComment is called");
  nama = document.getElementById("NewComment:Nama");
  tanggal = document.getElementById("NewComment:Email");
  konten = document.getElementById("NewComment:Komentar");
  
  if (isBlank(nama.value)) {
    alert("Nama Tidak Boleh Kosong");
    return false;
  }

  if (isBlank(email.value)) {
    alert("Nama Tidak Boleh Kosong");
    return false;
  }

  if (isBlank(komentar.value)) {
    alert("Komentar Tidak Boleh Kosong");
    return false;
  } 

  var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  if (!regex.test(email.value)) {
    alert("Email Yang Dimasukkan Tidak Valid");
    return false;
  } 
  
  return true;
}

function isBlank(str) {
  return (!str || 0 === str.length || /^\s*$/.test(str));
}