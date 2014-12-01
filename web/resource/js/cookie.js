/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function greet(){
    var cvalue=getCookie("user");
    if (cvalue!=""){
        document.getElementById("user").innerHTML= "Howdy, " + cvalue;
        document.getElementById("log_stat").href= "Logout";
        document.getElementById("log_stat").innerHTML= "Logout";
        if (cvalue!="admin"){
            document.getElementById("user").innerHTML= "";
        }
    }else{
        //document.getElementById("user").innerHTML = cvalue;
    }
}

    function checkCookie() {
    var username=getCookie("role");
    if (username!="") {
        alert("Welcome again "+ username);
    }else{
        alert("no cokie");
        }
    }
    
    function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}