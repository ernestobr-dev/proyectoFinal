<?php

require_once "conexion/basedatos.php";


class usuario{

    public static function altaUsuario($datosUsuario){


        $consulta = "INSERT INTO usuarios VALUES(?,?,?)";
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute(array($datosUsuario['dni'],$datosUsuario['email'],$datosUsuario['contraseña']));
    
            return $comando;
        } catch (PDOException $e) {
            return false;
        }
        
    }
    
    
    public static function loginUsuarios($email,$contraseña){
    
    
        $consulta = "SELECT dni FROM usuarios WHERE email LIKE '".$email."' AND 
        contrasena LIKE '".$contraseña."'";
    
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute();
    
            $res = $comando->fetchAll(PDO::FETCH_ASSOC);

            
            return $res;
        } catch (PDOException $e) {
            return false;
        }
    
    
    }

    public static function comprobaciones($resultado,$campo,$dato){


        $consulta = "SELECT ".$resultado." FROM usuarios WHERE ".$campo." LIKE '".$dato."'";
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute();
    
            $res = $comando->fetchAll(PDO::FETCH_ASSOC);

            
            return $res;
        } catch (PDOException $e) {
            return false;
        }

    }

}



?>