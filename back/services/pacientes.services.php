<?php

    require_once 'routes/pacientes.routes.php';

    class pacientesServices{

        public static function rutasPacientes($url){


            $ruta = explode('/',$url);
            
            switch ($ruta[1]) {
 
                case 'buscar':

                    pacientesRoutes::respuestaBuscarPacientes();
                    
                    break;

                
                default:
                    # code...
                    break;
            }


        }

        
    }


?>