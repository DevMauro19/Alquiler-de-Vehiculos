package principal;

public class Coche extends Vehiculo {
	//atributos
    public static final double PRECIO_BASE = 55.0;
    protected int puertas;
    protected String tipoMotor;
    
    //Constructor
    public Coche(String placa, String marca, String modelo, int anio, int puertas, String tipoMotor) {
        super(placa, marca, modelo, anio);
        this.puertas = puertas;
        this.tipoMotor = tipoMotor;
        this.multaTanque = 20;
    }
    
    //metodos para numero de puertas y tipo de motor
    public int getPuertas() { return puertas; }
    public String getTipoMotor() { return tipoMotor; }
    
    //calcularPrecioporDia
    @Override
    public double calcularPrecioPorDia() {
        double precio = PRECIO_BASE;
        if (tipoMotor.equalsIgnoreCase("Electrico") || tipoMotor.equalsIgnoreCase("Hibrido")) {
            precio += 20;
        }
        if (puertas > 4) {
            precio += 10;
        }
        this.precioPorDia = precio;
        return precio;
    }

}