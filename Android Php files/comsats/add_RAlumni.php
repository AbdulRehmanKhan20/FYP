<?php
include 'db/db_connect.php';
//Check for mandatory parameters
 if($_SERVER['REQUEST_METHOD']=='POST'){
	$name = $_POST['name'];
	$email = $_POST['email'];
	$mobile = $_POST['mobile'];
	$maritalStatus = $_POST['maritalStatus'];
	$profession = $_POST['profession'];
	$address = $_POST['address'];
	$graduation = $_POST['graduation'];
	$graduation1 = "";
	$status = 'PENDING';
	$yearOfPassing = $_POST['yearOfPassing'];
	$dateOfBirth = $_POST['dateOfBirth'];
	$date = date('Y-m-d H:i:s');
	$requestor_id = $_POST['requestor_id'];
	

	
	//Query to insert a movie
	$query = "INSERT INTO alumni_alumni(name,email,phone,year_of_passing,date_of_birth,martial_status,profession,address,graduation,graduation1,status,created_at,last_updated,requestor_id) 
				VALUES ('$name','$email','$mobile','$yearOfPassing','$dateOfBirth','$maritalStatus','$profession','$address','$graduation','$graduation1','$status','$date','$date','$requestor_id')";
	if(mysqli_query($con,$query))
	{
		echo 'Alumni Request Successfull';
	}
	else
	{
		echo 'Error while Requesting Alumni';
	}
	}
	else{
		echo "Check Again";
		}
	 mysqli_close($con);
?>