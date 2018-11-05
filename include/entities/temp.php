<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: GET");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    include_once '../config/DBClass.php';
    include_once '../entities/User.php';

    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $user = new User($connection);
	if (isset($_GET['email'])){
		$userEmail = $_GET['email'];
		$userPwd = $_GET['pwd'];
	    $stmt = $user->read($userEmail, $userPwd);
    	$count = $stmt->rowCount();
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
    }else{ echo json_encode(array("body" => array(), "count" => 0));}
?>
