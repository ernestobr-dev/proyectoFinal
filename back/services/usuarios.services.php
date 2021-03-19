<?php

    require_once 'routes/usuarios.routes.php';

    class serviceUsuario{


        public static function rutasUsuario($url){


            $ruta = explode('/',$url);
            
            switch ($ruta[1]) {

                case 'alta':

                    $json = file_get_contents("php://input");
                    $items = json_decode($json,true);

                    usuarioRoutes::respuestaAltaUsuario($items);
                    
                    

                    break;

                case 'login':

                    usuarioRoutes::respuestaLoginUsuario($_GET['email'],$_GET['contraseña']);
                    

                    break;
                
                default:
                    # code...
                    break;
            }


        }


    }



?>