package Exceptions;

public class ArchivoCorruptoException extends Exception {
    public ArchivoCorruptoException(String mensaje) {
        super(mensaje);
    }
}
