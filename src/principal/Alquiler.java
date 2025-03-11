package principal;
import java.util.Date;

public class Alquiler {
    private String placaVehiculo, idCliente;
    private double costoTotal;
    private boolean activo;
    private Date diaEntrega;
    private Date diaDevolucion;
    private int numeroAlquiler;
    
    // Constructor para nuevos alquileres (se inicializa como activo)
    public Alquiler(String placaVehiculo, String idCliente, Date diaEntrega, Date diaDevolucion, double costoTotalBase) {
        this.placaVehiculo = placaVehiculo;
        this.idCliente = idCliente;
        this.diaEntrega = diaEntrega;
        this.diaDevolucion = diaDevolucion;
        this.costoTotal = costoTotalBase;
        this.activo = true; // Siempre activo al crearlo desde la interfaz
    }

    // Nuevo constructor para cargar desde archivos (permite definir activo e id de alquiler)
    public Alquiler(String placaVehiculo, String idCliente, Date diaEntrega, Date diaDevolucion, double costoTotalBase, boolean activo, int numeroAlquiler) {
        this.placaVehiculo = placaVehiculo;
        this.idCliente = idCliente;
        this.diaEntrega = diaEntrega;
        this.diaDevolucion = diaDevolucion;
        this.costoTotal = costoTotalBase;
        this.activo = activo;
        this.numeroAlquiler = numeroAlquiler;
    }

    // Finalizar alquiler
    public void finalizarAlquiler() { this.activo = false; }
    
    // Getters y Setters
    public String getPlacaVehiculo() { return placaVehiculo; }
    public String getIdCliente() { return idCliente; }
    public double getCostoTotal() { return costoTotal; }
    public boolean esActivo() { return activo; }
    public Date getDiaEntrega() { return diaEntrega; }
    public Date getDiaDevolucion() { return diaDevolucion; }
    public int getNumeroAlquiler() { return numeroAlquiler; }
    public void setNumeroAlquiler(int numeroAlquilerNuevo) { this.numeroAlquiler = numeroAlquilerNuevo; }
    
    // toString
    public String toString() {
        return "Alquiler N°: " + numeroAlquiler + "\nPlaca: " + placaVehiculo + "\nId cliente: " + idCliente +
               "\nDía entrega: " + diaEntrega + "\nDía devolución: " + diaDevolucion + 
               "\nActivo: " + activo + "\nCosto: " + costoTotal;
    }
}