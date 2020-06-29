<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	$title = $_POST['title'];
	


	$body = $_POST['body'];
	 htmlspecialchars($body, ENT_QUOTES);
	$date = date('Y-m-d H:i:s');
	$author_id = $_POST['author_id'];

	//Query to insert a journals_journal
	$query = "INSERT INTO journals_journal(title,body,status,published_at,created_at,last_updated,author_id) 
				VALUES ('$title','$body','PENDING','','$date','$date','$author_id')";
	if(mysqli_query($con,$query))
	{
		echo 'Journal Added Successful';
	}
	else
	{
		echo 'Error While Adding Journal';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>