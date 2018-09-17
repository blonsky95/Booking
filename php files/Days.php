<?php
/**
 * Representa el la estructura de las Alumnoss
 * almacenadas en la base de datos
 */
require 'Database.php';
class Days
{
    function __construct()
    {
    }
    /**
     * Retorna en la fila especificada de la tabla 'Alumnos'
     *
     * @param $idAlumno Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT * FROM table_365";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();
            return $comando->fetchAll(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            return false;
        }
    }
    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $idAlumno Identificador del alumno
     * @return mixed
     */
    public static function getById($idDay)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT id_day,
							10_12,
							12_2,
							4_6,
							6_8
                             FROM table_365
                             WHERE id_day = ?";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idDay));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;
        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
    /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $idAlumno      identificador
     * @param $nombre      nuevo nombre
     * @param $direccion nueva direccion
     
     */
    public static function update(
        $idDay,
		$slot1,
		$slot2,
		$slot3,
        $slot4
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE table_365" .
            " SET 10_12=?, 12_2=?, 4_6=?, 6_8=? " .
            "WHERE id_day=?";
        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);
        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($slot1, $slot2, $slot3, $slot4, $idDay));
        return $cmd;
    }
    /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insert(
        $slot1,
		$slot2,
		$slot3,
        $slot4
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO table_365 ( " .
            "10_12," .
			" 12_2," .
			" 4_6," .
            " 6_8)" .
            " VALUES( ?,?)";
        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(
            array(
                $slot1,
		$slot2,
		$slot3,
        $slot4
            )
        );
    }
    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idAlumno identificador de la tabla Alumnos
     * @return bool Respuesta de la eliminación
     */
    public static function delete($idDay)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM table_365 WHERE id_day=?";
        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);
        return $sentencia->execute(array($idDay));
    }
}
?>