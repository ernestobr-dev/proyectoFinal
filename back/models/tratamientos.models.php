<?php


    require_once 'conexion/basedatos.php';

    class tratamientos{


        public static function buscarTratamientos($paciente){


            $consulta = "SELECT motivo, tratamiento, tiempo FROM tratamientos WHERE paciente LIKE '".$paciente."'";
    
    
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