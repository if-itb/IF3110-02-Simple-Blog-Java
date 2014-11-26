/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function LoadCommentList(PostID){
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.open("GET","assets/loadcomments.jsp?ID=" + PostID,true);
    xmlhttp.onreadystatechange=function(){
            if (xmlhttp.readyState==4 && xmlhttp.status==200){
                    document.getElementById("comment-list").innerHTML=xmlhttp.responseText;
            }
    }
    xmlhttp.send();	
}

function AddComment(PostID){
    if(IsEmailValid()){
        var xmlhttp = new XMLHttpRequest();
        
        xmlhttp.open("POST","assets/newcomment.jsp?ID=" +PostID + "&Name=" + document.getElementById("Nama").value + "&Email=" + document.getElementById("Email").value + "&Comment=" + document.getElementById("Komentar").value,true);
        xmlhttp.onreadystatechange=function(){
                if (xmlhttp.readyState==4 && xmlhttp.status==200){
                        document.getElementById("comment-list").innerHTML=xmlhttp.responseText;
                }
        }
        xmlhttp.send();	
        return true;
    }else{
        return false;	
    }
}

function AddCommentLoggedIn(PostID){
    var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.open("POST","assets/newcomment.jsp?ID=" +PostID + "&Name=" + document.getElementById("Nama2").value + "&Email=" + document.getElementById("Email2").value + "&Comment=" + document.getElementById("Komentar2").value,true);
    xmlhttp.onreadystatechange=function(){
            if (xmlhttp.readyState==4 && xmlhttp.status==200){
                    document.getElementById("comment-list").innerHTML=xmlhttp.responseText;
            }
    }	
    xmlhttp.send();
}

function IsEmailValid(){
    var regex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
    var emailInput = document.forms["Form"]["Email"].value;

    if(!regex.test(emailInput)){
            alert("Invalid email");
            return false;	
    }else{
            return true;	
    }
}

function IsEmailValidForRegistry(){
    var regex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
    var emailInput = document.forms["Form"]["Form:Email"].value;

    if(!regex.test(emailInput)){
            alert("Invalid email");
            return false;	
    }else{
            return true;	
    }
}
