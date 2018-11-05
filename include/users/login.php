<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: GET");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    include_once '../config/dbclass.php';
    include_once '../entities/User.php';

    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $user = new User($connection);
   	if($user->login()){
       	echo '{';echo '"message": "user was loggied in."';echo '}';
   	}else{
       	echo '{';echo '"message": "Unable to login user."';echo '}';
   	}
?>
