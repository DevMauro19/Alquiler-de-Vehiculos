package principal;

public class Motocicleta extends Vehiculo {
	//atributos
    public static final double PRECIO_BASE = 35.0;
    private int cilindrada;
    
    //Constructor
    public Motocicleta(String placa, String marca, String modelo, int anio, int cilindrada) {
        super(placa, marca, modelo, anio);
        this.cilindrada = cilindrada;
        this.multaTanque = 10;
    }
    //cantidad de cilindraje
    public int getCilindrada() { return cilindrada; }
    
    //CalcularPrecioPorDia
    @Override
    public double calcularPrecioPorDia() {
        double precio = PRECIO_BASE;
        if (cilindrada > 500) {
            precio += 15;
        }
        this.precioPorDia = precio;
        return precio;
    }


}