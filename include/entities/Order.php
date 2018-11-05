<?php
class Order{
    private $connection;
    private $table_name = "transaction";
    public $id;
    public $date;
    public $uid;
    public $items;
    public $notes;

    public function __construct($connection){
        $this->connection = $connection;
    }
    //C
    public function create(){}
    //R
    public function read(){}
    //U
    public function update(){}
    //D
    public function delete(){}    
}