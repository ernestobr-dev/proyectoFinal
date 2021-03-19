<?php

    require_once 'models/resultados.models.php';
    require_once 'models/respuesta.models.php';


    class resultadosRoutes{

        public static function respuestaBuscarResultados($paciente){

            $resultados = resultados::buscarResultados($paciente);
            if($resultados){

                respuesta::status200($resultados,"resultados");

            }
            else{
                respuesta::status400("NO HAY RESULTADOS PARA EL PACIENTE","resultados");

            }


            
        }

    }
    


?>