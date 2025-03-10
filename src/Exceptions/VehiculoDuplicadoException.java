package Exceptions;

//Excepción cuando un vehículo ya existe en el sistema
public class VehiculoDuplicadoException extends Exception {
    public VehiculoDuplicadoException(String placa) {
        super("El vehículo con placa " + placa + " ya está registrado en el sistema.");
    }
}
