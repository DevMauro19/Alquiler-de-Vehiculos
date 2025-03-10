package Exceptions;
//Excepción cuando un vehículo no es encontrado
public class VehiculoNoEncontradoException  extends Exception {
    public VehiculoNoEncontradoException(String placa) {
        super("El vehículo con placa " + placa + " no fue encontrado en el sistema.");
    }
}