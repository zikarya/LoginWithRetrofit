<?php
    header("Content-Type: application/json; charset=UTF-8");
    include_once '../config/dbclass.php';
    include_once '../entities/User.php';
    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $user = new User($connection);
    $stmt = $user->read();
    $count = $stmt->rowCount();
	$loginemail = $_GET["email"];
    $loginpwd = $_GET["pwd"];
    if($count > 0){
        $users = array();
        $users["body"] = array();
        $users["count"] = $count;
        $name;
        $company;
        $number; 
        $email;
        $pwd;
        $access;
        $ordercount;    
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
            extract($row);
            $p  = array(
                  "id" => $id,
                  "name" => $name,
                  "company" => $company,
                  "number" => $number,
                  "email" => $email,
                  "pwd" => $pwd,
                  "access" => $access,
                  "ordercount" => $ordercount);
            array_push($users["body"], $p);
        }
    	echo json_encode($users);
    }else {
        echo json_encode(array("body" => array(), "count" => 0));
    }
?>
