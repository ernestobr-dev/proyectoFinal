<?php


    require_once 'models/historial.models.php';
    require_once 'models/respuesta.models.php';


    class historialRoutes{


        public static function respuestaBuscarHistorial($paciente){

            $historial = historial::buscarHistoriales($paciente);
            if($historial){
                respuesta::status200($historial,"tratamientos");
            }
            else{
                respuesta::status400("NO HAY TRATAMIENTOS PARA ESE PACIENTE","tratamientos");
            }

        }


    }



?>