<?php
class User{
    private $connection;
    private $table_name = "User";
    public $id;
    public $name;
    public $company;
    public $number; 
    public $email;
    public $pwd;
    public $access;
    public $ordercount;

    public function __construct($connection){
        $this->connection = $connection;
    }
    //C
    public function create(){}
    //R
    public function read(){
        $query = "SELECT * FROM 'user'";
        $stmt = $this->connection->prepare($query);
        $stmt->execute();
        return $stmt;
    }
    public function login($username, $pwd){
        $query = "SELECT * FROM user WHERE 'email=admin2@gmail.com'";
        $stmt = $this->connection->prepare($query);
        $stmt->execute();
        return $stmt;
    }
    public function register($xusername, $xpwd, $xcompany, $xemail, $xnumber){
        $query = "INSERT INTO 'user' ('name', 'company', 'number', 'email', 'pwd', 'acess','ordercount' ) VALUES (?,?,?,?,?,?,?)";
        $stmt = $this->connection->prepare($query);
        $stmt->execute([$xusername, $xpwd, $xcompany, $xemail, $xnumber,"",0]);
        return $stmt;
    }
    //U
    public function update(){}
    //D
    public function delete(){}    
}