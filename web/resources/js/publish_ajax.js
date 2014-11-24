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
    xmlhttp.open("GET","publish_post.jsp?id="+id,true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            pos.innerHTML = xmlhttp.responseText;
        }
    };
}