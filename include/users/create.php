<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    include_once '../config/dbclass.php';
    include_once '../entities/user.php';

    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $user = new user($connection);
    $data = json_decode(file_get_contents("php://input"));
    $user->name = $data->name;
    if($user->read()){
        echo '{';echo '"message": "user was created."';echo '}';
    }else{
        echo '{';echo '"message": "Unable to create user."';echo '}';
    }
?>
