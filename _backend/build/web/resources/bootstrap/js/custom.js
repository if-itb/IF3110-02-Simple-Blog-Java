function loginValidation(){
    if((document.form1["form1:Email"].value == "") || (document.form1["form1:Password"].value == "")){
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