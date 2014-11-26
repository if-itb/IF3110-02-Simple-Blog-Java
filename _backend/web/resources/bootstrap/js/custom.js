function loginValidation(){
    var email = document.form1["form1:Email"];
    var password = document.form1["form1:Password"];
    alert(email);
    if((email.length == 0) && (password.length == 0)){
        alert("Form masih kosong");
        return false;
    }
    else{
        return true;
    }
}

function emailValidation(email){
    var pattern = /[\w.-]+@[\w.-]+\.\w+/g;
    var result = pattern.test(email);
    if(result){
        return true;
    }
    else{
        return false;
    }
}