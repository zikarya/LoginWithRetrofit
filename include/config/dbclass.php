<?php
class DBClass {
    private $host = 'db759501955.hosting-data.io';
    private $username = "dbo759501955";
    private $password = "db759501955";
    private $database = "P@55word";

    public $connection;

    // get the database connection
    public function getConnection(){

        $this->connection = null;

        try{
            $this->connection = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->database, $this->username, $this->password);
            $this->connection->exec("set names utf8");
        }catch(PDOException $exception){
            echo "Error: " . $exception->getMessage();
        }

        return $this->connection;
    }
}
?>