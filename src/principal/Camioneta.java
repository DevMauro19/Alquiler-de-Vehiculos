package principal;

public class Camioneta extends Coche {
    public static final double PRECIO_BASE = 70.0;
    private double capacidadCarga;
    
    //Constructor
    public Camioneta(String placa, String marca, String modelo, int anio, int puertas, String tipoMotor, double capacidadCarga) {
        super(placa, marca, modelo, anio, puertas, tipoMotor);
        this.capacidadCarga = capacidadCarga;
        this.multaTanque = 30;
    }

    //Getter
    public double getCapacidadCarga() { return capacidadCarga; }
    
    //metodo calcular Precio por dia
    @Override
    public double calcularPrecioPorDia() {
        double precio = PRECIO_BASE;
        if (capacidadCarga >= 1000) {
            precio += 25;
        }
        if (tipoMotor.equalsIgnoreCase("Electrico") || tipoMotor.equalsIgnoreCase("Hibrido")) {
            precio += 10;
        }
        if (puertas > 4) {
            precio += 10;
        }
        this.precioPorDia = precio;
        return precio;
    }

}