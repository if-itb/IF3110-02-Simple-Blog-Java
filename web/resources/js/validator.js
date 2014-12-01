function emailValidator()
{
    email = document.getElementById("form-register:register_email").value;
    if(email.length > 0)
    {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var result = re.test(email);
        var ganti = document.getElementById("ganti-error-message");
        if(!result)
        {
            ganti.innerHTML= '<div class="alert alert-danger">wrong email format</div>';
        }
        else
        {
            ganti.innerHTML = "";
        }
        return result;
    }
    else
    {
        return false;
    }
}
