function getPost()
{
    var pos = document.getElementById("posts");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","load_deletedPost.jsp",true);
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
    xmlhttp.open("GET","update_deletedPost.jsp?id="+id,true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            pos.innerHTML = xmlhttp.responseText;
        }
    };
}