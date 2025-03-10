package principal;
import java.util.Date;

public class Alquiler {
	//Atributos 
    private String placaVehiculo, idCliente;
    private double costoTotal;
    private boolean activo = true;
    private Date diaEntrega;
    private Date diaDevolucion;
    private int numeroAlquiler;
    
    //Constructor
    public Alquiler(String placaVehiculo, String idCliente, Date diaEntrega, Date diaDevolucion, double costoTotalBase) {
        this.placaVehiculo = placaVehiculo;
        this.idCliente = idCliente;
        this.diaEntrega = diaEntrega;
        this.diaDevolucion = diaDevolucion;
        this.costoTotal = costoTotalBase;
    }
    //Finalizar alquiler
    public void finalizarAlquiler() { this.activo = false; }
    
    //Getters and Setters
    public String getPlacaVehiculo() { return placaVehiculo; }
    public String getIdCliente() { return idCliente; }
    public double getCostoTotal() { return costoTotal; }
    public boolean esActivo() { return activo; }
    public Date getDiaEntrega() { return diaEntrega; }
    public Date getDiaDevolucion() { return diaDevolucion; }
    public int getNumeroAlquiler() { return numeroAlquiler; }
    public void setNumeroAlquiler(int numeroAlquilerNuevo) {numeroAlquiler = numeroAlquilerNuevo;}
    
    //toString
    public String toString() {
    	return "La informacion del alquiler es:\nPlaca: "+placaVehiculo+"\nId cliente: "+idCliente+"\nDia entrega: "+diaEntrega+"\nDia devolucion: "+diaDevolucion+"\nEsta activo: "+activo+"\nNumero alquiler: "+numeroAlquiler;
    }


}