<?php
include 'db/db_connect.php';
//Check for mandatory parameters
if($_SERVER['REQUEST_METHOD']=='POST'){
	$reason = $_POST['reason'];
	$startDate = $_POST['startDate'];
	$endDate = $_POST['endDate'];
	$date = date('Y-m-d H:i:s');
	$requestor_id = $_POST['requestor_id'];

	// $reason = "Yoo";
	// $startDate = date('Y-m-d H:i:s');
	// $endDate = date('Y-m-d H:i:s');
	// $date = date('Y-m-d H:i:s');
	// $requestor_id = 1;


	
	echo $requestor_id;
	
	//Query to insert a movie
	$query = "INSERT INTO auditorium_auditoriumreservation(reason,start_time,end_time,status,created_at,last_updated,requestor_id) VALUES ('$reason','$startDate','$endDate','PENDING','$date','$date','$requestor_id')";
	if(mysqli_query($con,$query))
	{
		echo 'Reservation Successfully';
	}
	else
	{
		echo 'Error while Reserving Auditorioum';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>