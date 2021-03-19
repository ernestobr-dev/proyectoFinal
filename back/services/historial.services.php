<?php

    require_once 'routes/historial.routes.php';

    class historialServices{


        public static function rutasHistorial($url){


            $ruta = explode('/',$url);
            
            switch ($ruta[1]) {

                case 'buscar':

                    historialRoutes::respuestaBuscarHistorial($_GET['paciente']);
                    
                    break;

                
                default:
                    # code...
                    break;
            }


        }


    }



?>