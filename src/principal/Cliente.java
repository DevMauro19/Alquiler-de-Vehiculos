package principal;

public class Cliente {
    private String id, nombre, telefono, email; //atributos
    
    //Constructor
    public Cliente(String id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
    //Getters and Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    
    //toString
    @Override
    public String toString() {
    	return "La informacion del cliente es:\nId: "+id+"\nNombre: "+nombre+"\nTelefono: "+telefono+"\nEmail: "+email;
    	}

}