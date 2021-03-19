<?php

require_once "conexion/basedatos.php";


class resultados{

   public static function buscarResultados($paciente){


     $consulta = "SELECT archivo,fecha FROM resultados WHERE paciente LIKE '".$paciente."'";
    
    
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