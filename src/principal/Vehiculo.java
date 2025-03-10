package principal;

public abstract class Vehiculo {
	//atributos
    protected String placa, marca, modelo;
    protected int anio;
    protected double precioPorDia;
    protected boolean disponible = true;
    protected boolean tanqueLleno = true;
    protected int multaTanque;
    
    //constructor
    public Vehiculo(String placa, String marca, String modelo, int anio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }
    //Getters and Setters
    public String getPlaca() { return placa; }
    public double getPrecioPorDia() { return precioPorDia; }
    public boolean estaDisponible() { return disponible; }
    protected void cambiarDisponibilidad(boolean estado) { this.disponible = estado; }
    public abstract double calcularPrecioPorDia();
    public boolean getTanqueLleno() { return tanqueLleno; }
    public void setTanqueLleno(boolean estado) { this.tanqueLleno = estado; }
    public int getMultaTanque() { return multaTanque; }
    
    //toString
    @Override
    public String toString() {
    	return "La informacion del Vehiculo es:\nPlaca: "+placa+"\nMarca: "+marca+"\nModelo: "+modelo+"\nAño: "+anio;
    	}   
}