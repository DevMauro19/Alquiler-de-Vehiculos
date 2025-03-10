package Exceptions;

//Excepción cuando la fecha de devolución es incorrecta
public class FechaInvalidaException extends Exception {
    public FechaInvalidaException() {
        super("La fecha de devolución debe ser posterior a la fecha de entrega.");
    }
}
