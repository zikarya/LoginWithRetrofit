<?php
class Product{
    private $connection;
    private $table_name = "product";
    public $id;
    public $name;
    public $price;
    public $notes;
    public function __construct($connection){
        $this->connection = $connection;
    }
    public function create(){}
    public function read(){
    }
    //U
    public function update(){}
    //D
    public function delete(){}
}