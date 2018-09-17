<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */
require 'Days.php';
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    // Manejar petición GET
    $days = Days::getAll();
    if ($days) {
        $datos["estado"] = 1;
        $datos["days"] = $days;
        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}