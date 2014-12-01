function loginValidation(){
    if((document.form1["form1:Email"].value == "") || (document.form1["form1:Password"].value == "")){
        alert("Form masih kosong");
        return false;
    }
    else{
        return true;
    }
}

function userValidation(stat){
    if(document.userform["userform:email"].value == "" || document.userform["userform:name"].value == "" ||
            document.userform["userform:password"].value == "" || document.userform["userform:role"].value == ""){
        alert("Semua isian form harus diisi");
        return false;
    } else if(!emailValidation(document.userform["userform:email"].value)){
        return false;
    }else{
        if (stat==1)
            alert("User berhasil ditambahkan");
        else
            alert("User berhasil diedit");
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
        alert("Masukan email tidak valid");
        return false;
    }
}

function formAddValidation(stat){
    if(document.addform["addform:judul"].value == "" || document.addform["addform:tanggal"].value == "" ||
            document.addform["addform:konten"].value == ""){
        alert("Semua isian form harus diisi");
        return false;
    } else if(!validasiTanggal(document.addform["addform:tanggal"].value)){
        return false;
    }else{
        if (stat==1)
            alert("Post berhasil ditambahkan");
        else
            alert("Post berhasil diedit");
        return true;
    }
}

function validasiTanggal(input){
    var valid;
    var pat = /[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}/g;
    var result = pat.test(input);    
    if (!result){
        alert ("Format tanggal harus (YYYY-MM-DD)");
        return false;
    } else{
        var today = new Date();
        var tdate = parseInt(today.getDate());
        var tmonth = parseInt(today.getMonth()) + 1;
        var tyear = parseInt(today.getFullYear());
        
        //memisahkan tanggal
        var dpat = /[0-9]{1,2}$/g;
        var date = input.match(dpat);
        
        //memisahkan bulan
        var mpat = /-[0-9]{1,2}-/g;
        var month = input.match(mpat);
        month = "" + month;
        month = month.substr(1);
        month = month.substring(0, month.length-1);
        month = parseInt(month);
        //memisahkan tahun
        var ypat = /^[0-9]{1,4}/g;
        var year = input.match(ypat);

        if (!IsTanggalValid(month, date, year)){
            alert("Masukan tanggal tidak valid.");
            return false;
        } else{
            if(year<tyear){
                alert("Masukan tanggal harus lebih besar dari tanggal hari ini.");
                return false;
            } else{
                if(year>tyear){
                    return true;
                } else{
                    if(month>tmonth){
                        return true;
                    } else if(month==tmonth){
                        if(date<tdate){
                            alert("Masukan tanggal harus lebih besar dari tanggal hari ini.");
                            return false;
                        } else{
                            return true;
                        }
                    } else{
                        alert("Masukan tanggal harus lebih besar dari tanggal hari ini.");
                        return false;
                    }
                }
            }
        }
    }
    return valid;
}

function IsTanggalValid(month, date, year){
    if(month>12 || month<1 || date>31 || date<1 || year>9999 || year<1){
        return false;
    } else if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
        if(date<=31){
            return true;
        } else{
            return false;
        }
    } else if(month==4 || month==6 || month==9 || month==11){
        if(date<=30){
            return true;
        } else{
            return false;
        }
    } else{
        if(((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) && date<=29){
            return true;
        } else if(date<=28){
            return true;
        } else{
            return false;
        }
    }
}