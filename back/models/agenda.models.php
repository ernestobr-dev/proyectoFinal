<?php

require_once "conexion/basedatos.php";


class agenda{

    public static function altaAgenda($datosEvento){


        $consulta = "INSERT INTO agenda VALUES(null,?,?,?,?)";
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute(array($datosEvento['tipo'],$datosEvento['nombre'],$datosEvento['fecha'],$datosEvento['usuario']));
    
            return $comando;
        } catch (PDOException $e) {
            return false;
        }
        
    }
    
    
    public static function buscarAgenda($fecha,$usuario){
    
    
        $consulta = "SELECT tipo,nombre FROM agenda WHERE fecha LIKE '".$fecha."' AND 
        usuario LIKE '".$usuario."'";
    
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute();
    
            $res = $comando->fetchAll(PDO::FETCH_ASSOC);

            
            return $res;
        } catch (PDOException $e) {
            return false;
        }
    
    
    }

    public static function modificarAgenda($datosEvento){

        $consulta = "UPDATE agenda SET fecha = ?, nombre = ? WHERE id = ?";
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute(array($datosEvento['fecha'],$datosEvento['nombre'],$datosEvento['id']));
    
            return $comando;
        } catch (PDOException $e) {
            return false;
        }

    }

    public static function eliminarAgenda($eliminar){

        $consulta = "DELETE FROM agenda WHERE id = ?";
    
        try {
            
            $comando = database::getInstance()->getDb()->prepare($consulta);
            
            $comando->execute(array($eliminar));
    
            return $comando;
        } catch (PDOException $e) {
            return false;
        }


    }

}



?>