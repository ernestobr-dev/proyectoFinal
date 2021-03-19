<?php

    require_once 'models/pacientes.models.php';
    require_once 'models/respuesta.models.php';

    class pacientesRoutes{


        public static function respuestaBuscarPacientes(){


            $pacientes = pacientes::buscarPacientes();
            if($pacientes){
                respuesta::status200($pacientes,"pacientes");
            }
            else{
                respuesta::status400("ERROR EN LA BASE DE DATOS","pacientes");
            }


        }


    }


?>