<?php
function make_password($password) {
    $algorithm = "pbkdf2_sha256";
    $iterations = 180000;

    $newSalt = mcrypt_create_iv(6, MCRYPT_DEV_URANDOM);
    $newSalt = base64_encode($newSalt);

    $hash = hash_pbkdf2("SHA256", $password, $newSalt, $iterations, 0, true);    
    $toDBStr = $algorithm ."$". $iterations ."$". $newSalt ."$". base64_encode($hash);

    // This string is to be saved into DB, just like what Django generate.
    echo $toDBStr;
}

function verify_Password($dbString, $password) {
    $pieces = explode("$", $dbString);
	
	$algorithm = "pbkdf2_sha256";
    $iterations = 180000;
	
    $iterations = $pieces[1];
    $salt = $pieces[2];
    $old_hash = $pieces[3];

    $hash = hash_pbkdf2("SHA256", $password, $salt, $iterations, 0, true);
    $hash = base64_encode($hash);
	
	
	$toDBStr = $algorithm ."$". $iterations ."$". $salt ."$". $hash;


    if (hash_equals($hash,$old_hash)) {
       // login ok.
       return true;
    }
    else {
       //login fail       
       return false; 
    }
}


if($_SERVER['REQUEST_METHOD']=='POST')
	 // if(true)
 {

 include 'db/db_connect.php';
 
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 // $email = "Hamzaaaaaaaj";
 // $password = "Qureshi";
 
 $Sql_Query = "select user_management_user.id,auth_group.name,user_management_user.password from user_management_user,user_management_user_groups,auth_group where user_management_user.username='$email'  and user_management_user.id = user_management_user_groups.user_id and user_management_user_groups.group_id = auth_group.id ";
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 
if(verify_Password($check[2], $password))
{
  echo "Data Matched".'.'. $check[0].','.$check[1];
}
else
{
	echo "Invalid Username or Password Please Try Again";
}
 }else{
 echo "Check Again";
 }
mysqli_close($con);

?>