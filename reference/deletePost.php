<?php 	
	
	//establish connection to database
	$con = mysqli_connect('localhost', 'kevhnmay94', "", 'simpleblog');
	$id = mysqli_real_escape_string($con, $_POST['id']);
	
	//query for deleting entry
	$query = "DELETE FROM `posts` 
			  WHERE `id` = $id";
			  
	if(!mysqli_query($con, $query))
	{
		die('Error ' . mysqli_errno($con));
	}
	
	echo "<br>entry has been deleted.";
	
	//close connection with database
	mysqli_close($con);
	header('Location: index.php');
?>