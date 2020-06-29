<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$name = $_POST['name'];
	$date = date('Y-m-d H:i:s');
	$description = $_POST['description'];

	//Query to insert a journals_journal
	$query = "INSERT INTO chat_room(name,description,status,created_at,last_updated) 
				VALUES ('$name','$description',1,'$date','$date')";
	if(mysqli_query($con,$query))
	{
		echo 'Room Added Successful';
	}
	else
	{
		echo 'Error While Adding Room';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>