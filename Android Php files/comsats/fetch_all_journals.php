<?php
include 'db/db_connect.php';
//Query to select Hospital id and Hospital name
$query = "SELECT journals_journal.id,journals_journal.title,user_management_user.username,journals_journal.body from journals_journal,user_management_user where journals_journal.author_id = user_management_user.id ";
$result = array();
$roomArray = array();
$room_name ="" ;
$response = array();
//Prepare the query
if($stmt = $con->prepare($query)){
	$stmt->execute();
	//Bind the fetched data to $name
	$stmt->bind_result($id,$title,$username,$body);
	//Fetch 1 row at a time					
	while($stmt->fetch()){
		
		//Populate the movie array
		$roomArray["id"] = $id;
		$roomArray["title"] = $title;
		$roomArray["username"] = $username;
		$roomArray["body"] = $body;
		$result[]=$roomArray;
	}
	$stmt->close();
	$response["success"] = 1;
	$response["data"] = $result;
}else{
	//Some error while fetching data
	$response["success"] = 0;
	$response["message"] = mysqli_error($con);
}
//Display JSON response
echo json_encode($response);
 
?>