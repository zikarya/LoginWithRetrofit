<?php

    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    header("Access-Control-Max-Age: 3600");
    header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    include_once '../config/dbclass.php';
    include_once '../entities/product.php';

    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $product = new Product($connection);
    $data = json_decode(file_get_contents("php://input"));
    $product->name = $data->name;
    $product->price = $data->price;
    $product->notes = $data->notes;

    if($product->create()){
        echo '{';
            echo '"message": "Product was created."';
        echo '}';
    }else{
        echo '{';
            echo '"message": "Unable to create product."';
        echo '}';
    }
?>
