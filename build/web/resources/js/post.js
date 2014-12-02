function hapus(id)
{
	var konfirmasi = confirm('Apakah anda yakin menghapus post ini?');
	if(konfirmasi)
	{
		var formObjects = document.forms['post'];
		var formElements = formObjects.elements['id'];
		formElements.value = id.substring(1);
		formObjects.action = "deletePost.php";
		formObjects.submit();
	}
}