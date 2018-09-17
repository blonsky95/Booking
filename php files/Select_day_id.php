<?php
/**
 * Obtiene el detalle de un alumno especificado por
 * su identificador "idalumno"
 */
require 'Days.php';
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if (isset($_GET['id_day'])) {
        // Obtener parámetro idalumno
        $parametro = $_GET['id_day'];
        // Tratar retorno
        $retorno = Days::getById($parametro);
        if ($retorno) {
            $day["estado"] = 1;		// cambio "1" a 1 porque no coge bien la cadena.
            $day["day"] = $retorno;
            // Enviar objeto json del alumno
            print json_encode($day);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }
    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}