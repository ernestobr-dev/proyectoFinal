<?php
 
    require_once 'services/usuarios.services.php';
    require_once 'services/agenda.services.php';
    require_once 'services/resultados.services.php';
    require_once 'services/tratamientos.services.php';
    require_once 'services/historial.services.php';
    require_once 'services/pacientes.services.php';


    ini_set('display_errors', 1);
    ini_set('display_startup_errors', 1);
    error_reporting(E_ALL);
    header('Content-Type: application/json');

    $var = $_GET['url'];

    $url = explode('/',$var);


    if($_SERVER["REQUEST_METHOD"]=="GET"){

        switch ($url[0]) {
            
            case 'medico':

                serviceUsuario::rutasUsuario($var);

                break;


            case 'agenda':

                agendaService::rutasAgenda($var);

                break;
            
            case 'resultados':

                servicesResultados::rutasResultados($var);

                break;

            case 'tratamientos':
                
                tratamientosServices::rutasTratamientos($var);
                
                break;


            case 'historial':
                historialServices::rutasHistorial($var);
                break;

            case 'pacientes':

                pacientesServices::rutasPacientes($var);

                break;


          
            default:
                
                

                break;
        }  

    }
    else if ($_SERVER["REQUEST_METHOD"]=="POST") {
        

        switch ($url[0]) {
           
            case 'medico':

                serviceUsuario::rutasUsuario($var);

                break;
    
            case 'agenda':

                agendaService::rutasAgenda($var);

                break;
          
            default:
                
                break;
        }  

    }
    else if ($_SERVER["REQUEST_METHOD"]=="PUT") {
        

        switch ($url[0]) {
           
        
            case 'agenda':

                agendaService::rutasAgenda($var);

                break;
          
            default:
                
                break;
        }  

    }

    else if ($_SERVER["REQUEST_METHOD"]=="DELETE") {
        

        switch ($url[0]) {
           
        
            case 'agenda':

                agendaService::rutasAgenda($var);

                break;
          
            default:
                
                break;
        }  

    }
    

   
    

    

    

?>

