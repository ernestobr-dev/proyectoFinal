<?php


    require_once 'conexion/basedatos.php';

    class historial{


        public static function buscarHistoriales($paciente){

            
            $consulta = "SELECT motivo, fecha FROM historial WHERE paciente LIKE '".$paciente."'";
    
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