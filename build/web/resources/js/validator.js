function validateForm(){
    //alert("lala");
    var titlePost = document.inputanpos["inputanpos:Judul"].value;
    var contentPost = document.inputanpos["inputanpos:Konten"].value;
    var datePost = document.inputanpos["inputanpos:Tanggal"].value;
    var dateNow = new Date();
    if(titlePost === null || titlePost === ""){
        alert('Judul pos tidak boleh kosong');
        return false;
    }

    if(datePost === null || datePost === ""){
        alert('Tanggal pos tidak boleh kosong');
        return false;
    }
    else {
        datePost = new Date(document.inputanpos["inputanpos:Tanggal"].value + " 23:59:59");
        if (datePost - dateNow < 0){
            alert('Tanggal pos tidak boleh kurang dari tanggal sekarang.');
            return false;
        }
    }

    if(contentPost === null || contentPost === ""){
        alert('Konten pos tidak boleh kosong');
        return false;
    }

    return true;
}

function validateComment(){
    // validasi komentar
    var komentar = document.inputankomentar["inputankomentar:komentar"].value;
    var email = document.inputankomentar["inputankomentar:email"].value;
    var nama = document.inputankomentar["inputankomentar:nama"].value;
    // validasi komentar kosong
    if(nama === ""){
        alert('nama tidak boleh kosong');
        return false;
    }
    // validasi email tidak kosong
    else if(email === ""){
        alert('isi email anda untuk berkomentar');
        return false;
    }
    // validasi email sesuai kaidah
    else if(!validateEmail(email)){
        alert("alamat email tidak valid");
        return false;
    }
    // validasi komentar kosong
    else if(komentar === ""){
        alert('komentar tidak boleh kosong');
        return false;
    }
    return true;
}

function validateEmail(email){
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validateEdAddUser(){
    // validasi komentar
    var username = document.edadduser["edadduser:username"].value;
    var password = document.edadduser["edadduser:password"].value;
    var email = document.edadduser["edadduser:email"].value;
    // validasi komentar kosong
    if(username === ""){
        alert('username tidak boleh kosong');
        return false;
    }
    // validasi email tidak kosong
    else if(email === ""){
        alert('email tidak boleh kosong');
        return false;
    }
    // validasi email sesuai kaidah
    else if(!validateEmail(email)){
        alert("alamat email tidak valid");
        return false;
    }
    // validasi komentar kosong
    else if(password === ""){
        alert('password tidak boleh kosong');
        return false;
    }
    return true;
}