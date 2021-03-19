<?php

    require_once 'routes/resultados.routes.php';

    class servicesResultados{


        public static function rutasResultados($url){


            $ruta = explode('/',$url);
            
            switch ($ruta[1]) {

                case 'buscar':

                    resultadosRoutes::respuestaBuscarResultados($_GET['paciente']);
                    
                    break;

                
                default:
                    # code...
                    break;
            }


        }


    }



?>