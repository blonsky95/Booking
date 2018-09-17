<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */
require 'Days.php';
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);
    // Insertar Alumno
    $retorno = Days::insert(
        $body['10_12'],
		$body['12_2'],
        $body['4_6'],
        $body['6_8']);
    if ($retorno) {
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Creacion correcta"));
		echo $json_string;
    } else {
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se creo el registro"));
		echo $json_string;
    }
}
?>