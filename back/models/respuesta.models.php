<?php

header('Content-Type: application/json');


    class respuesta{


        public static function status200($respuesta,$nombreRespuesta){

            http_response_code(200);
            $mostrar[$nombreRespuesta]=$respuesta;
            print json_encode($mostrar,JSON_PRETTY_PRINT);


        }

        public static function status400($respuesta,$nombreRespuesta){

            
            http_response_code(400);
            $mostrar[$nombreRespuesta]=$respuesta;
            print json_encode($mostrar,JSON_PRETTY_PRINT);

        }

        public static function status500($respuesta,$nombreRespuesta){

            http_response_code(500);
            $mostrar[$nombreRespuesta]=$respuesta;
            print json_encode($mostrar,JSON_PRETTY_PRINT);

        }


    }


?>