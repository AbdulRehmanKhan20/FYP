<?php

function make_password($password) {
    $algorithm = "pbkdf2_sha256";
    $iterations = 180000;

    $newSalt = random_bytes(12);
    $newSalt = base64_encode($newSalt);

    $hash = hash_pbkdf2("SHA256", $password, $newSalt, $iterations, 0, true);    
    $toDBStr = $algorithm ."$". $iterations ."$". $newSalt ."$". base64_encode($hash);

    // This string is to be saved into DB, just like what Django generate.
    return $toDBStr;
}


 if($_SERVER['REQUEST_METHOD']=='POST')
	 // if(true)
{

include 'db/db_connect.php';


 $username = $_POST['username'];
 $type = $_POST['type'];
 $email = $_POST['email'];
 $password = $_POST['password'];
 $password = make_password($password);
 $firstName = $_POST['firstName'];
 $lastName = $_POST['lastName'];
 
 // $username = "Hamzaaaaaaaj";
 // $type = "Admin";
 // $email = "mnh11aaaasdj124@gmail.com";
 // $password = "Qureshi";
 // $password = make_password($password);
 // echo "after ".$password;
 // $firstName = "Hamza";
 // $lastName = "Qureshi";
 
 
$date = date('Y-m-d H:i:s');

 $CheckSQL = "SELECT * FROM user_management_user WHERE email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){
 echo 'Email Already Exist';
 }
else{ 

 $Sql = "SELECT id FROM auth_group WHERE name='$type'";
 
 $checkU = mysqli_fetch_array(mysqli_query($con,$Sql));
 if(isset($checkU)){
	 echo "after".print_r($checkU[0]);
	$Address ="Random Address";
	$Sql_Query = "INSERT INTO user_management_user (password,is_superuser,username,last_login,first_name,last_name,email,is_staff,is_active,date_joined,address) values ('$password','0','$username','$date','$firstName','$lastName','$email','1','1','$date','$Address')";
	mysqli_query($con,$Sql_Query); 
	$id = mysqli_insert_id($con);
	echo "after".$id;
	$SqlQ = "INSERT INTO user_management_user_groups (group_id,user_id)  values ('$checkU[0]','$id')";

 if(mysqli_query($con,$SqlQ))
{
 echo 'Registration Successfully';
}
else
{
 echo 'Something went wrong';
 }
}
else{ 
 echo 'Selected Type Doesnot Exist In Database';
}


 }
}
 mysqli_close($con);
?>