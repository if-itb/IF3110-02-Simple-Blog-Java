function addComment() {
    var id = document.getElementById("id").innerHTML;
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var content = document.getElementById("content").value;
    if ((name != "") && (email != "") && (content != "")) {
        if (validateEmail(email)) {
            var xmlhttp;
            if (window.XMLHttpRequest) {
                // code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            } else {
                // code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange=function() {
                if (xmlhttp.readyState<4) {
                    document.getElementById("submit").style="display:none";
                    document.getElementById("comment_list").style="display:none";
                } else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    if (xmlhttp.responseText == "true") {
                        loadComment();
                        document.getElementById("comment_form").reset();
                    } else {
                        alert("Fail to process");
                        document.getElementById("submit").style="display:block";
                    }
                }
            }
            xmlhttp.open("get","CommentServlet?" + "id="+id+"&name="+name+"&email="+email+"&content="+content,true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("id="+id+"&nama="+name+"&email="+email+"&content="+content);
        } else {
            alert("Invalid email!");
            document.getElementById("email").focus();
        }
    } else {
        alert("Invalid input");
        document.getElementById("submit").style="display:block";
    }
}
function loadComment() {
    var id = document.getElementById("id").innerHTML;
    var xmlhttp;
    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState<4) {
            document.getElementById("submit").style="display:none";
            document.getElementById("comment_list").style="display:none";
        } else if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            if (xmlhttp.responseText != "zero") {
                var list = JSON.parse(xmlhttp.responseText);
                var inner = "";
                var row;
                var i = 0;
                for (row in list) {
                    inner += '<!-- Comment -->';
                    inner += '      <div class="media">';
                    inner += '          <a class="pull-left" href="#"><img class="media-object" src="http://placehold.it/40x40" alt="" /></a>';
                    inner += '          <div class="media-body">';
                    inner += '              <h4 class="media-heading">'+list[i].name+'<small> at '+list[i].time+'</small></h4>';
                    inner += '              ' + list[i].content;
                    inner += '          </div>';
                    inner += '      </div>';
                    i++;
                }
                document.getElementById("comment_list").innerHTML = inner;
            }
            document.getElementById("submit").style="display:block";
            document.getElementById("comment_list").style="display:block";
        }
    }
    xmlhttp.open("post","CommentServlet",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("id="+id);
}
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
loadComment();