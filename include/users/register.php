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
    $stmt = $user->read();
    $count = $stmt->rowCount();
    if($count > 0){
        $users = array();
        $users["body"] = array();
        $users["count"] = $count;
        public $name;
        public $company;
        public $number; 
        public $email;
        public $pwd;
        public $access;
        public $ordercount;    
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
        echo json_encode(array("body" => array(), "count" => 0););
    }
?>
