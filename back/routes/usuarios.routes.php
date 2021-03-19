<?php

    require_once 'models/usuarios.models.php';
    require_once 'models/respuesta.models.php';


    class usuarioRoutes{

        public static function respuestaAltaUsuario($nuevoUsuario){
            
            if(!usuario::comprobaciones("email","dni",$nuevoUsuario['dni']) &&
               !usuario::comprobaciones("dni","email",$nuevoUsuario['email'])){
                   
                   $alta = usuario::altaUsuario($nuevoUsuario);
                   
                   if($alta){
           
                       respuesta::status200("ALTA CORRECTA","altaUsuario");
           
                   }
                   else{
                       respuesta::status400("ERROR EN EL ALTA","altaUsuario");
                   }
                

            }
            else if(usuario::comprobaciones("email","dni",$nuevoUsuario['dni'])){
                respuesta::status400("EL DNI YA ESTÁ REGISTRADO","altaUsuario");
            }
            else if(usuario::comprobaciones("dni","email",$nuevoUsuario['email'])){
                respuesta::status400("EL EMAIL YA ESTÁ REGISTRADO","altaUsuario");
            }



        }


        public static function respuestaLoginUsuario($email,$contrasena){

            if(usuario::comprobaciones("contrasena","email",$email) &&
            usuario::comprobaciones("email","contrasena",$contrasena)){

                $comprobacionLogin = usuario::loginUsuarios($email,$contrasena);
                if($comprobacionLogin){
                    $mostrar['email']=$comprobacionLogin;
                    respuesta::status200($mostrar['email'],"altaUsuario");
    
    
                }
                else{
                    respuesta::status400("ERROR EN EL LOGIN","loginUsuario");
    
                }
                

            }
            else if(!usuario::comprobaciones("contrasena","email",$email)){
                respuesta::status400("CONTRASEÑA INCORRECTA","loginUsuario");

            }
            else if(!usuario::comprobaciones("email","contrasena",$contrasena)){
                respuesta::status400("EMAIL INCORRECTO","loginUsuario");

            }

          


        }

        


    }
    


?>