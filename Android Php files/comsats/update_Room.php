<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	 
	$room_id = $_POST['roomid'];
	$user_id = $_POST['userid'];
	

	
	//Query to insert a movie
	$query = "UPDATE chat_roomuser
			SET status = '0'
			WHERE room_id = '$room_id' and user_id = '$user_id';";
	if(mysqli_query($con,$query))
	{
		echo 'Data Updated  Successfull';
	}
	else
	{
		echo 'Error while Updating Data';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>