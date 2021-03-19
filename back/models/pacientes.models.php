<?php


    require_once 'conexion/basedatos.php';

    class pacientes{


        public static function buscarPacientes(){

            $consulta = "SELECT dni,nombre FROM pacientes";
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