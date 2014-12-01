function ConfirmDeletePost()
{
	if (confirm("Delete Post?"))
            return true;
        else return false;
}

function ConfirmDeleteUser(ID)
{
	if (confirm("Delete User?"))
            return true;
        else return false;
}

function ConfirmActionOnDeletedPost()
{
        if (confirm("Are you sure?"))
            return true;
        else return false;
}
