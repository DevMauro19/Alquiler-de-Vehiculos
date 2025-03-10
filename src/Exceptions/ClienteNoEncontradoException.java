package Exceptions;

//Excepción cuando un cliente no es encontrado
 public class ClienteNoEncontradoException extends Exception {
 public ClienteNoEncontradoException(String idCliente) {
     super("El cliente con ID " + idCliente + " no fue encontrado en el sistema.");
 }
}
