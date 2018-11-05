<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    include_once '../config/dbclass.php';
    include_once '../entities/transaction.php';
    
    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $transaction = new transaction($connection);
    $data = json_decode(file_get_contents("php://input"));
    $transaction->name = $data->name;
    $transaction->date = $data->date;
    $transaction->uid = $data->uid;
    $transaction->notes = $data->notes;
    if($transaction->create()){
        echo '{';echo '"message": "transaction was created."'; echo '}';
    }else{
        echo '{';echo '"message": "Unable to create transaction."';echo '}';
    }
?>
