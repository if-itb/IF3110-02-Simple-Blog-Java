function getPost()
{
    var pos = document.getElementById("posts");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","loadPost.jsp",true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            pos.innerHTML = xmlhttp.responseText;
        }
    };
}

function updatePost(id)
{
    var pos = document.getElementById("posts");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","update_publish.jsp?id="+id,true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            pos.innerHTML = xmlhttp.responseText;
        }
    };
}

function deleteUser(username)
{
    var cookie = readCookie("username");
    if(cookie != username)
    {
        if(confirm("Apakah anda yakin ingin menghapus user ini?"))
        {
            var pos = document.getElementById("list_user");
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("GET","edit_user.jsp?username="+username,true);
            xmlhttp.send();
            xmlhttp.onreadystatechange=function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    pos.innerHTML = xmlhttp.responseText;
                }
            };
        }
    }
    else
    {
        alert("tidak dapat menghapus diri sendiri");
    }
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}