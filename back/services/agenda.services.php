<?php

    require_once 'routes/agenda.routes.php';

    class agendaService{

        public static function rutasAgenda($url){


            $ruta = explode('/',$url);
            
            switch ($ruta[1]) {

                case 'alta':

                    $json = file_get_contents("php://input");
                    $items = json_decode($json,true);

                    agendaRoutes::respuestaAltaAgenda($items);
                    
                    

                    break;

                case 'modificar':

                    $json = file_get_contents("php://input");
                    $items = json_decode($json,true);
                    agendaRoutes::respustaModificarEvento($items);
                    

                    break;

                case 'eliminar':

                    agendaRoutes::repsuestaEliminarEvento($_GET['id']);


                    break;

                case 'buscar':

                    agendaRoutes::respuestaBuscarEvento($_GET['fecha'],$_GET['usuario']);

                    break;
                
                default:
                    # code...
                    break;
            }


        }


    }



?>