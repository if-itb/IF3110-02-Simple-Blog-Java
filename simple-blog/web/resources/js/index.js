function ConfirmDelete(ID)
{
	if (confirm("Delete Post?"))
		location.href='delete_post.php?ID=' + ID;
}

function formSubmitEdit(ID)
{
	document.getElementById('postIdEdit').value = ID;
	document.getElementById('hiddenFormEdit').submit();
}

function formSubmitPost(ID)
{
	document.getElementById('postIdPost').value = ID;
	document.getElementById('hiddenFormPost').submit();
}