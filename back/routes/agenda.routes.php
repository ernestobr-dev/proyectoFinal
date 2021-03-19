<?php

    require_once 'models/agenda.models.php';
    require_once 'models/respuesta.models.php';


    class agendaRoutes{

        public static function respuestaAltaAgenda($nuevoEvento){
            
            $alta = agenda::altaAgenda($nuevoEvento);
                   
            if($alta){
           
                respuesta::status200("NUEVO EVENTO AÑADIDO","altaAgenda");
           
            }
            else{
                respuesta::status400("ERROR: NO SE HA PODIDO AÑADIR EL EVENTO","altaAgenda");
            }


        }


      public static function respustaModificarEvento($datos){

        $update = agenda::modificarAgenda($datos);
        if($update){

            respuesta::status200("EVENTO MODIFICADO CORRECTAMENTE","modificarEvento");

        }

        else{
            respuesta::status400("EL EVENTO NO HA PODIDO SER MODIFICADO","modificarEvento");

        }

      }

      public static function repsuestaEliminarEvento($idEvento){


        $eliminar = agenda::eliminarAgenda($idEvento);

        if($eliminar){

            respuesta::status200("EVENTO ELIMINADO CORRECTAMENTE","eliminarEvento");            

        }

        else{
            
            respuesta::status400("EL EVENTO NO HA PODIDO SER ELIMINADO","eliminarEvento");        

        }


      }


      public static function respuestaBuscarEvento($fecha,$idUsuario){


        $datos = agenda::buscarAgenda($fecha,$idUsuario);

        if($datos){

            respuesta::status200($datos,"buscarEvento");            

        }

        else{
            
            respuesta::status400("NO HAY DATOS PARA LA FECHA INTRODUCIDA","buscarEvento");        

        }

      }

        


    }
    


?>