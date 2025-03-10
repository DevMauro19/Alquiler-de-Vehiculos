package Exceptions;

//Excepción cuando un vehículo no está disponible para alquilar
public class VehiculoNoDisponibleException extends Exception {
    public VehiculoNoDisponibleException(String placa) {
        super("El vehículo con placa " + placa + " no está disponible para alquilar.");
    }
}
