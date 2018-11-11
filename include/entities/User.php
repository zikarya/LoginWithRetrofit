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
    public $access = "";
    public $ordercount =0;

    public function __construct($connection){
        $this->connection = $connection;
    }
    //C
    public function create(){}
    //R
    public function read($e,$p){
      	$this->email = $e;
      	$this->pwd = $p;
        $query = "select * from " . $this->table_name . " where email='" . $this->email . "' && pwd='" . $this->pwd . "'";
        $stmt = $this->connection->prepare($query);
        $stmt->execute();
        return $stmt;
    }
    public function login($e, $p){
       	$this->email = $e;
      	$this->pwd = $p;
        $query = "select * from " . $this->table_name . " where email='" . $this->email . "' && pwd='" . $this->pwd . "'";
        $stmt = $this->connection->prepare($query);
        $stmt->execute();
        return $stmt;
    }
  
    public function register($u, $c, $n, $e, $p){    
	 	$a="";$o=0;
      try{
	        $query = "INSERT INTO User (name, company, number, email, pwd, acess, ordercount) VALUES (:name, :company, :number, :email, :pwd, :acess, :ordercount)";
       	    $stmt = $this->connection->prepare($query);
      		$stmt->bindParam(':name', $u, PDO::PARAM_STR);
	      	$stmt->bindParam(':company', $c, PDO::PARAM_STR);
    	  	$stmt->bindParam(':number', $n, PDO::PARAM_STR);
      		$stmt->bindParam(':email', $e, PDO::PARAM_STR);
	      	$stmt->bindParam(':pwd', $p, PDO::PARAM_STR);
    	  	$stmt->bindParam(':acess', $a, PDO::PARAM_STR);
      		$stmt->bindParam(':ordercount', $o, PDO::PARAM_INT);
        	echo ($stmt->debugDumpParams);
        	$stmt->execute();
      }catch(PDOException $e){
        echo($e -> getCode());
      }
        return $stmt;
    }
    //U
    public function update(){}
    //D
    public function delete(){}    
}