<?php

    require_once 'models/tratamientos.models.php';
    require_once 'models/respuesta.models.php';

    class tratamientosRoutes{


        public static function respuestaBuscarTratamientos($paciente){


            $tratamiento = tratamientos::buscarTratamientos($paciente);
            if($tratamiento){
                respuesta::status200($tratamiento,"tratamientos");
            }
            else{
                respuesta::status400("NO HAY TRATAMIENTOS PARA ESE PACIENTE","tratamientos");
            }


        }


    }


?>