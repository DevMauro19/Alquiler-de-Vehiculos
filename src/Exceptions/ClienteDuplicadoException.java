package Exceptions;

//Excepción cuando un cliente ya existe en el sistema
public class ClienteDuplicadoException extends Exception {
    public ClienteDuplicadoException(String idCliente) {
        super("El cliente con ID " + idCliente + " ya está registrado en el sistema.");
    }
}
