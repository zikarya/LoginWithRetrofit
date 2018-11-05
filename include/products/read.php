<?php
    header("Content-Type: application/json; charset=UTF-8");
    include_once '../config/dbclass.php';
    include_once '../entities/product.php';
    $dbclass = new DBClass();
    $connection = $dbclass->getConnection();
    $product = new Product($connection);`
    $stmt = $product->read();
    $count = $stmt->rowCount();
    if($count > 0){
        $products = array();
        $products["body"] = array();
        $products["count"] = $count;
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
            extract($row);
            $p  = array(
                  "id" => $id,
                  "name" => $name,
                  "price" => $price,
                  "notes" => $notes);
            array_push($products["body"], $p);
        }
        echo json_encode($products);
    }else {
        echo json_encode(
            array("body" => array(), "count" => 0););
    }
?>
