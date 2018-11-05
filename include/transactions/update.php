<?php

header("Content-Type: application/json; charset=UTF-8");

include_once '../config/dbclass.php';
include_once '../entities/transaction.php';

$dbclass = new DBClass();
$connection = $dbclass->getConnection();

$transaction = new transaction($connection);

$stmt = $transaction->read();
$count = $stmt->rowCount();

if($count > 0){


    $transactions = array();
    $transactions["body"] = array();
    $transactions["count"] = $count;

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){

        extract($row);

        $p  = array(
              "id" => $id,
              "sku" => $sku,
              "barcode" => $barcode,
              "name" => $name,
              "price" => $price,
              "unit" => $unit,
              "quantity" => $quantity,
              "minquantity" => $minquantity,
              "createdAt" => $createdAt,
              "createdAt" => $createdAt,
              "updatedAt" => $updatedAt,
              "family_id" => $family_id,
              "location_id" => $location_id
        );

        array_push($transactions["body"], $p);
    }

    echo json_encode($transactions);
}

else {

    echo json_encode(
        array("body" => array(), "count" => 0);
    );
}
?>
