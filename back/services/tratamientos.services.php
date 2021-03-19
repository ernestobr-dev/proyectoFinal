<?php


    require_once 'routes/tratamientos.routes.php';

    class tratamientosServices{


        public static function rutasTratamientos($url){


            $ruta = explode('/',$url);
            
            switch ($ruta[1]) {
 
                case 'buscar':

                    restultadosRoutes::respuestaBuscarResultados($_GET['paciente']);
                    
                    break;

                
                default:
                    # code...
                    break;
            }


        }


    }



?>