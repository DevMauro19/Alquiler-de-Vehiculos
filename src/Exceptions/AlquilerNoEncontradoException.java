package Exceptions;
//Excepción cuando un alquiler no es encontrado
public class AlquilerNoEncontradoException extends Exception {
    public AlquilerNoEncontradoException(int numeroAlquiler) {
        super("No se encontró un alquiler activo para el vehículo con placa " + numeroAlquiler + ".");
    }
}