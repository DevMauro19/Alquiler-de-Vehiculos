package principal;
import java.util.Arrays; //acceso a metodos como copyof o Arraycopy
import java.util.Date; //acceso a las variables de tipo Date
import Exceptions.AlquilerNoEncontradoException; //excepciones personalizadas
import Exceptions.ArchivoCorruptoException;
import Exceptions.ClienteDuplicadoException;
import Exceptions.ClienteNoEncontradoException;
import Exceptions.FechaInvalidaException;
import Exceptions.NumeroInvalidoException;
import Exceptions.ParametroNuloException;
import Exceptions.VehiculoDuplicadoException;
import Exceptions.VehiculoNoDisponibleException;
import Exceptions.VehiculoNoEncontradoException;
import java.io.*; 	//clase que nos permite usar ficheros 
import java.nio.file.Files; //clases que nos permiten sobreescribir sobre archivos txt, copiar escribir.
import java.nio.file.StandardCopyOption;

//clase principal
public class Agencia {
    private Vehiculo[] vehiculos = new Vehiculo[0];
    private Cliente[] clientes = new Cliente[0];
    private Alquiler[] alquileres = new Alquiler[0];
    private static int contadorNumeroAlquiler = 0;

    
    private static final String CARPETA_DATOS = "datos";
    private static final String ARCHIVO_VEHICULOS = CARPETA_DATOS + "/vehiculos.txt";
    private static final String ARCHIVO_CLIENTES = CARPETA_DATOS + "/clientes.txt";
    private static final String ARCHIVO_ALQUILERES = CARPETA_DATOS + "/alquileres.txt";
    
    //metodo agencia
    public Agencia() {
        verificarEstructuraArchivos(); 
        try {
            cargarVehiculos(); //cargar los ficheros
            cargarClientes();
            cargarAlquileres();
        } catch (ArchivoCorruptoException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
    
    
    // Métodos para agregar vehículos, clientes y alquiler
    public void agregarCoche(String placa, String marca, String modelo, int anio, int puertas, String tipoMotor) 
            throws VehiculoDuplicadoException, ParametroNuloException, NumeroInvalidoException {
    	
    	//llamado de metodos de validación 
        validarParametrosVehiculo(placa, marca, modelo, anio);
        validarParametrosCoche(puertas);
        
        //validación para revisar que el vehiculo no este registrado
        if (buscarVehiculo(placa) != -1) throw new VehiculoDuplicadoException(placa); //si se lanza la excepcion el vehiculo no se registrara
        
        
        Vehiculo v = new Coche(placa, marca, modelo, anio, puertas, tipoMotor); //creación del objeto 
        vehiculos = Arrays.copyOf(vehiculos, vehiculos.length + 1); //modificar el tamaño del arreglo de vehiculos
        vehiculos[vehiculos.length - 1] = v;
        
        guardarVehiculos(); //llamado al metodo de guardar vehiculos
    }

    //Agregar motocicleta
    public void agregarMotocicleta(String placa, String marca, String modelo, int anio, int cilindrada)
            throws VehiculoDuplicadoException, ParametroNuloException, NumeroInvalidoException {
    	//llamado de metodos de validación
        validarParametrosVehiculo(placa, marca, modelo, anio);
        validarParametrosMotocicleta(cilindrada);
        
      //validación para revisar que el vehiculo no este registrado
        if (buscarVehiculo(placa) != -1) throw new VehiculoDuplicadoException(placa);

        
        Vehiculo v = new Motocicleta(placa, marca, modelo, anio, cilindrada);//creación del objeto 
        vehiculos = Arrays.copyOf(vehiculos, vehiculos.length + 1);//modificar el tamaño del arreglo de vehiculos
        vehiculos[vehiculos.length - 1] = v;
        
        guardarVehiculos(); //llamado al metodo de guardar vehiculos
    }
    //Agregar Camioneta
    public void agregarCamioneta(String placa, String marca, String modelo, int anio, int puertas, 
    		String tipoMotor, double capacidadCarga)
            throws VehiculoDuplicadoException, ParametroNuloException, NumeroInvalidoException {
    	
    	//llamado de metodos de validación
        validarParametrosVehiculo(placa, marca, modelo, anio);
        validarParametrosCoche(puertas);
        
        //validación para revisar que el vehiculo no este registrado
        if (buscarVehiculo(placa) != -1) throw new VehiculoDuplicadoException(placa);

        Vehiculo v = new Camioneta(placa, marca, modelo, anio, puertas, tipoMotor, capacidadCarga);
        vehiculos = Arrays.copyOf(vehiculos, vehiculos.length + 1);
        vehiculos[vehiculos.length - 1] = v;
        
        guardarVehiculos(); //llamado al metoddo de guardar vehiculos
    }
    //Registrar Clientes
    public void registrarCliente(String id, String nombre, String telefono, String email) 
    		throws ClienteDuplicadoException, NumeroInvalidoException {
    	//validar que el cliente no este duplicado
        if (buscarCliente(id) != -1) {
            throw new ClienteDuplicadoException(id);
        }
      
        //validación de Telefono
        if (!telefono.matches("\\d+")) { //  "\\d+" validación de formato, para recibir solo numeros.
            throw new NumeroInvalidoException("El teléfono debe contener solo números.");
        }
        if (!email.contains("@")) { //validación sencilla para que la cadena ingresada contenga el @
            throw new IllegalArgumentException("El correo debe ser válido.");
        }

        Cliente c = new Cliente(id, nombre, telefono, email);
        clientes = Arrays.copyOf(clientes, clientes.length + 1);
        clientes[clientes.length - 1] = c;
        
        guardarClientes();
    }

    // Métodos para gestionar un alquiler
    public double realizarAlquiler(String placa, String idCliente, 
    		Date fechaEntrega, Date fechaDevolucion) 
        throws VehiculoNoEncontradoException, ClienteNoEncontradoException, 
        VehiculoNoDisponibleException, FechaInvalidaException {
    	
    	contadorNumeroAlquiler++; //para llevar un conteo de los alquileres realizados
    	
    	//metodos para validar si se encuentra el vehiculo
        int vehiculoIndex = buscarVehiculo(placa);
        if (vehiculoIndex == -1) throw new VehiculoNoEncontradoException(placa); //esto no se puede poner para que directamente extienda el metodo de buscar vehiculo??
        																		 // y lanzar la excepción en el metodo para que sea solo llamarlo	
        int clienteIndex = buscarCliente(idCliente);
        if (clienteIndex == -1) throw new ClienteNoEncontradoException(idCliente);
        
        if (!vehiculos[vehiculoIndex].estaDisponible()) {
            throw new VehiculoNoDisponibleException(placa);
        }
        //convertir dates a numero de dias
long milisegundosDiferencia = fechaDevolucion.getTime() - fechaEntrega.getTime();
        int dias = (int) (milisegundosDiferencia / (24 * 60 * 60 * 1000));

        if (dias <= 0) throw new FechaInvalidaException();
        
        //llamado de metodos necesarios para calcular precio del alquiler
        double costo = vehiculos[vehiculoIndex].calcularPrecioPorDia() * dias;
        Alquiler nuevo = new Alquiler(placa, idCliente, fechaEntrega, fechaDevolucion, costo);
        nuevo.setNumeroAlquiler(contadorNumeroAlquiler);
        alquileres = Arrays.copyOf(alquileres, alquileres.length + 1);
        alquileres[alquileres.length - 1] = nuevo;
        vehiculos[vehiculoIndex].cambiarDisponibilidad(false);
        
        guardarAlquileres();
        return costo;
    }

    
    
    //metodo de buscar un alquiler inactivo
    public Alquiler buscarAlquilerPorNumero (int numeroAlquiler) throws ParametroNuloException, NumeroInvalidoException{
    	if (numeroAlquiler==0) {
    		throw new ParametroNuloException ();
    	}
    	if (numeroAlquiler > alquileres.length) {
    		throw new NumeroInvalidoException ("No hay ningun alquiler con el numero marcado asignado");
    	}
    	int i =0;
    	while (numeroAlquiler > alquileres[i].getNumeroAlquiler()) {
    		i++;
    	}
    	return alquileres[i];
    	
    }
    
    public int cantidadDeAlquilereHistoria () {
    	return contadorNumeroAlquiler;
    }
    //devolver vehiculos
    public double devolverVehiculo(String placa, boolean tanqueEstaLleno, 
    		Date fechaEntregaTeorica, Date fechaEntregaPractica, int numeroAlquiler) 
        throws VehiculoNoEncontradoException, AlquilerNoEncontradoException {
    	
    	//buscar el alquiler
        int indexAlquiler = buscarAlquilerActivo(placa);
        if (indexAlquiler == -1) throw new AlquilerNoEncontradoException(numeroAlquiler);
        
        //buscar el vehiculo
        int indexVehiculo = buscarVehiculo(placa);
        if (indexVehiculo == -1) throw new VehiculoNoEncontradoException(placa);
        
        Alquiler alquiler = alquileres[indexAlquiler];
        Vehiculo vehiculo = vehiculos[indexVehiculo];

        alquiler.finalizarAlquiler();
        	
        //validación si el coche esta disponible
        if (!vehiculo.estaDisponible()) {
            vehiculo.cambiarDisponibilidad(true);
        }
        //conversión de Long a tiempoe Real
        long milisegundosExcedidos = fechaEntregaPractica.getTime() - fechaEntregaTeorica.getTime();
        int diasExcedidos = (int) (milisegundosExcedidos / (24 * 60 * 60 * 1000));
        
        //si no vay diasExcedidos
        if (diasExcedidos < 0) diasExcedidos = 0;

        double excesoTotal = 0.0;
        //en el que caso de que los días excedidos sean mayores a cero
        if (diasExcedidos > 0) {
            excesoTotal += diasExcedidos * (vehiculo.calcularPrecioPorDia() * 1.10);
        }
        //si el tanque no esta vacío se llamara al metodo MultaTanque
        if (!tanqueEstaLleno) {
            excesoTotal += vehiculo.getMultaTanque();
            vehiculo.setTanqueLleno(true);
        }
        
        guardarAlquileres();

        return excesoTotal; //precio a pagar
    }
    
 // Métodos para eliminar vehículos, clientes y alquileres
    public void eliminarCliente(String id) throws ClienteNoEncontradoException {
        //buscar cliente
    	int index = buscarCliente(id);
        if (index == -1) throw new ClienteNoEncontradoException(id);

        //copia el arreglo desde clientes, apartir index +1, lo voy a copiar en el mismo arreglo, de acuerdo al iterador(index), hasta clientes.length-index-1
        
        System.arraycopy(clientes, index + 1, clientes, index, clientes.length - index - 1);
        clientes = Arrays.copyOf(clientes, clientes.length - 1);
        
        guardarClientes();
    }
    //revisar
    public void eliminarAlquiler(int numeroAlquiler) throws AlquilerNoEncontradoException {
        int index = numeroAlquiler-1;
        if (index == -1) throw new AlquilerNoEncontradoException(numeroAlquiler);

        System.arraycopy(alquileres, index + 1, alquileres, index, alquileres.length - index - 1);
        alquileres = Arrays.copyOf(alquileres, alquileres.length - 1);
        
        guardarAlquileres();
    }
    
    public void eliminarVehiculo(String placa) throws VehiculoNoEncontradoException {
        int index = buscarVehiculo(placa);
        if (index == -1) {
            throw new VehiculoNoEncontradoException(placa);
        }
        System.arraycopy(vehiculos, index + 1, vehiculos, index, vehiculos.length - index - 1);
        vehiculos = Arrays.copyOf(vehiculos, vehiculos.length - 1);
        
        guardarVehiculos();
    }
    

    // Métodos para actualizar información de vehículos, clientes y alquileres
    public void actualizarCoche(String placa, String Nplaca, String marca, String modelo, int anio,
    		int puertas, String tipoMotor)
            throws VehiculoNoEncontradoException {
        int index = buscarVehiculo(placa);
        if (index == -1) throw new VehiculoNoEncontradoException(placa);

        Coche nCoche = new Coche(Nplaca, marca, modelo, anio, puertas, tipoMotor);
        vehiculos[index] = nCoche;
        
        guardarVehiculos();
    }

    public void actualizarMotocicleta(String placa, String Nplaca, String marca, String modelo,
    		int anio, int cilindrada) //parametros recibidos
            throws VehiculoNoEncontradoException {  
        int index = buscarVehiculo(placa);
        if (index == -1) throw new VehiculoNoEncontradoException(placa);

        Motocicleta nMoto = new Motocicleta(Nplaca, marca, modelo, anio, cilindrada);
        vehiculos[index] = nMoto;
        
        guardarVehiculos();
    }

    public void actualizarCamioneta(String placa, String Nplaca, String marca, String modelo,
    		int anio, int puertas, String tipoMotor, double capacidadCarga)
            throws VehiculoNoEncontradoException {
        int index = buscarVehiculo(placa);
        if (index == -1) throw new VehiculoNoEncontradoException(placa);

        Camioneta nCamioneta = new Camioneta(Nplaca, marca, modelo, anio, puertas, tipoMotor,
        		capacidadCarga);
        vehiculos[index] = nCamioneta;
        
        guardarVehiculos();
    }

    public void actualizarCliente(String id, String nId, String nombre, String telefono, String email)
            throws ClienteNoEncontradoException {
        int index = buscarCliente(id);
        if (index == -1) throw new ClienteNoEncontradoException(id);

        Cliente nCliente = new Cliente(nId, nombre, telefono, email);
        clientes[index] = nCliente;
        
        guardarClientes();
    }

    public void actualizarAlquiler(int contadorNumeroAlquiler, String nPlaca, String idCliente, Date diaEntrega,
    		Date diaDevolucion, double costoTotalBase)
            throws AlquilerNoEncontradoException, ParametroNuloException, NumeroInvalidoException{
        int index = buscarVehiculo((buscarAlquilerPorNumero(contadorNumeroAlquiler)).getPlacaVehiculo());
        if (index == -1) throw new AlquilerNoEncontradoException(contadorNumeroAlquiler);

        Alquiler nAlq = new Alquiler(nPlaca, idCliente, diaEntrega, diaDevolucion, costoTotalBase);
        alquileres[index] = nAlq;
        
        guardarAlquileres();
    }
    
    
    
    // Métodos para listar vehículos, clientes y alquileres
    public void listarVehiculos() {
        for (Vehiculo v : vehiculos) {
            System.out.println(v.getPlaca() + " - " + v.marca + " - " + v.modelo 
            		+ " - " + v.anio + " - Disponible: " + v.estaDisponible());
        }
    }

    public void listarClientes() {
        for (Cliente c : clientes) {
            System.out.println(c.getId() + " - " + c.getNombre());
        }
    }

    public void listarAlquileres() {
        for (Alquiler a : alquileres) {
            System.out.println(a.getPlacaVehiculo() + " - " + a.getIdCliente() 
            + " - Activo: " + a.esActivo());
        }
    }
    
    // Métodos para buscar vehículos, clientes y alquileres
    private int buscarVehiculo(String placa) {
        int i = 0;
        while (i < vehiculos.length && !(vehiculos[i].getPlaca().equals(placa))) {
            i++;
        }
        return (i < vehiculos.length) ? i : -1;
    }
    
    private int buscarCliente(String id) {
        int i = 0;
        while (i < clientes.length && !(clientes[i].getId().equals(id))) {
            i++;
        }
        return (i < clientes.length) ? i : -1;
    }

    private int buscarAlquilerActivo(String placa) {
        int i = 0;
        while (i < alquileres.length && !(alquileres[i].getPlacaVehiculo().equals(placa) 
        		&& alquileres[i].esActivo())) {
            i++;
        }
        return (i < alquileres.length) ? i : -1;
    }
    
    //Metodos para validar los parametros de los vehiculos
    private void validarParametrosVehiculo(String placa, String marca, String modelo, int anio)
            throws ParametroNuloException, NumeroInvalidoException {
        if (placa == null || marca == null || modelo == null) {
            throw new ParametroNuloException();
        }
        if (anio < 2000) {
            throw new NumeroInvalidoException("El vehículo debe ser del año 2000 en adelante");
        }
    }
    
    private void validarParametrosCoche(int puertas) throws NumeroInvalidoException {
        if (puertas < 2) {
            throw new NumeroInvalidoException("El coche debe tener por lo menos 2 puertas");
        }
    }

    private void validarParametrosMotocicleta(int cilindrada) throws NumeroInvalidoException {
        if (cilindrada < 50) {
            throw new NumeroInvalidoException("La motocicleta debe tener un cilindraje mayor a 50");
        }
    }
    
    
    // Métodos para cargar y guardar información en archivos
    private void verificarEstructuraArchivos() {
        File carpeta = new File(CARPETA_DATOS);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crea la carpeta si no existe
        }

        verificarOCrearArchivo(ARCHIVO_VEHICULOS);
        verificarOCrearArchivo(ARCHIVO_CLIENTES);
        verificarOCrearArchivo(ARCHIVO_ALQUILERES);
    }

    private void verificarOCrearArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + ruta + " - " + e.getMessage());
            }
        }
    }
    
    private void guardarVehiculos() {
        File archivoTemporal = new File(ARCHIVO_VEHICULOS + ".tmp"); 
        File archivoOriginal = new File(ARCHIVO_VEHICULOS);
        /* Se crea un archivo temporal, para escribir la nueva versión del archivo de datos sin 
          sobrescribir el original hasta que la operación se haya completado con éxito, para evitar que el archivo
          quede corrupto */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            for (Vehiculo v : vehiculos) {
                if (v instanceof Coche && !(v instanceof Camioneta)) {
                    Coche c = (Coche) v;
                    bw.write("Coche," + c.getPlaca() + "," + c.marca + "," + c.modelo + "," + 
                             c.anio + "," + c.getPuertas() + "," + c.getTipoMotor());
                } else if (v instanceof Motocicleta) {
                    Motocicleta m = (Motocicleta) v;
                    bw.write("Motocicleta," + m.getPlaca() + "," + m.marca + "," + m.modelo + "," + 
                             m.anio + "," + m.getCilindrada());
                } else if (v instanceof Camioneta) {
                    Camioneta ca = (Camioneta) v;
                    bw.write("Camioneta," + ca.getPlaca() + "," + ca.marca + "," + ca.modelo + "," + 
                             ca.anio + "," + ca.getPuertas() + "," + ca.getTipoMotor() + "," + 
                             ca.getCapacidadCarga());
                }
                bw.newLine();
            }
            bw.close();
            archivoTemporal.renameTo(new File(ARCHIVO_VEHICULOS));  // Reemplaza el archivo original
        } catch (IOException e) {
            System.out.println("Error al guardar vehículos: " + e.getMessage());
            return;//evitar que se escriba sobre el archivo si algo sale mal
        }
        try {
            Files.move(archivoTemporal.toPath(), archivoOriginal.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error al reemplazar archivo de vehículos: " + e.getMessage());
        }
    }
    
    //escribir sobre el archivo txt de clientes
    private void guardarClientes() {
        File archivoTemporal = new File(ARCHIVO_CLIENTES + ".tmp");
        File archivoOriginal = new File(ARCHIVO_CLIENTES);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            for (Cliente c : clientes) {
                bw.write(c.getId() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
            return; // Evita reemplazar el archivo original si hubo error al escribir
        }

        try {
            Files.move(archivoTemporal.toPath(), archivoOriginal.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error al reemplazar archivo de clientes: " + e.getMessage());
        }
    }
    
    //escribir sobre el archivo txt de Alquileres
    private void guardarAlquileres() {
        File archivoTemporal = new File(ARCHIVO_ALQUILERES + ".tmp");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            for (Alquiler a : alquileres) {
                bw.write(a.getPlacaVehiculo() + "," + a.getIdCliente() + "," + 
                         a.getDiaEntrega().getTime() + "," + 
                         a.getDiaDevolucion().getTime() + "," + 
                         a.getCostoTotal());
                bw.newLine();
            }
            bw.close();
            archivoTemporal.renameTo(new File(ARCHIVO_ALQUILERES));
        } catch (IOException e) {
            System.out.println("Error al guardar alquileres: " + e.getMessage());
        }
    }
    
    
 // Métodos para cargar información desde archivos
    private void cargarVehiculos() throws ArchivoCorruptoException {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VEHICULOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                

                if (datos.length < 5) {
                    throw new ArchivoCorruptoException("Error en el archivo de vehículos. Línea incorrecta: " + linea);
                }

                String tipo = datos[0];
                String placa = datos[1];
                String marca = datos[2];
                String modelo = datos[3];
                int anio = Integer.parseInt(datos[4]);

                Vehiculo v;
                if ("Coche".equals(tipo)) {
                    int puertas = Integer.parseInt(datos[5]);
                    String tipoMotor = datos[6];
                    v = new Coche(placa, marca, modelo, anio, puertas, tipoMotor);
                } else if ("Motocicleta".equals(tipo)) {
                    int cilindrada = Integer.parseInt(datos[5]);
                    v = new Motocicleta(placa, marca, modelo, anio, cilindrada);
                } else if ("Camioneta".equals(tipo)) {
                    int puertas = Integer.parseInt(datos[5]);
                    String tipoMotor = datos[6];
                    double capacidadCarga = Double.parseDouble(datos[7]);
                    v = new Camioneta(placa, marca, modelo, anio, puertas, tipoMotor, capacidadCarga);
                } else {
                    throw new ArchivoCorruptoException("Tipo de vehículo desconocido en línea: " + linea);
                }

                vehiculos = Arrays.copyOf(vehiculos, vehiculos.length + 1);
                vehiculos[vehiculos.length - 1] = v;
            }
        } catch (IOException e) {
            throw new ArchivoCorruptoException("No se pudo leer el archivo de vehículos: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new ArchivoCorruptoException("Formato incorrecto en los datos numéricos del archivo de vehículos.");
        }
    }

    //cargar datos del cliente 
    private void cargarClientes() throws ArchivoCorruptoException {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");


                if (datos.length < 4) {
                    throw new ArchivoCorruptoException("Error en el archivo de clientes. Línea incorrecta: " + linea);
                }

                String id = datos[0];
                String nombre = datos[1];
                String telefono = datos[2];
                String email = datos[3];

                Cliente c = new Cliente(id, nombre, telefono, email);
                clientes = Arrays.copyOf(clientes, clientes.length + 1);
                clientes[clientes.length - 1] = c;
            }
        } catch (IOException e) {
            throw new ArchivoCorruptoException("No se pudo leer el archivo de clientes: " + e.getMessage());
        }
    }

    //cargar datos de Alquileres
    private void cargarAlquileres() throws ArchivoCorruptoException {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ALQUILERES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos.length < 5) {
                    throw new ArchivoCorruptoException("Error en el archivo de alquileres. Línea incorrecta: " + linea);
                }

                String placaVehiculo = datos[0];
                String idCliente = datos[1];
                long fechaEntregaMillis = Long.parseLong(datos[2]);
                long fechaDevolucionMillis = Long.parseLong(datos[3]);
                double costoTotal = Double.parseDouble(datos[4]);

                Date fechaEntrega = new Date(fechaEntregaMillis);
                Date fechaDevolucion = new Date(fechaDevolucionMillis);

                Alquiler alquiler = new Alquiler(placaVehiculo, idCliente, fechaEntrega, fechaDevolucion, costoTotal);
                alquileres = Arrays.copyOf(alquileres, alquileres.length + 1);
                alquileres[alquileres.length - 1] = alquiler;
            }
        } catch (IOException e) {
            throw new ArchivoCorruptoException("No se pudo leer el archivo de alquileres: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new ArchivoCorruptoException("Formato incorrecto en los datos numéricos del archivo de alquileres.");
        }
    }
  //METODOS SOLO PARA LA INTERFAZ, usados para escribir en un apartado la una lista de elementos
    public String listarAlquileres2() {
        if (alquileres.length == 0) {				
            return "No hay alquileres registrados.";
        }
        String resultado = "";
        for (Alquiler a : alquileres) {
            resultado += a.toString() + "\n--------------------\n"; // 
        }
        return resultado;
    }
    
    public String listarVehiculos2() {
        if (alquileres.length == 0) {
            return "No hay Vehiculos registrados.";
        }
        String resultado = "";
        for (Alquiler a : alquileres) {
            resultado += a.toString() + "\n--------------------\n"; // 
        }
        return resultado;
    }
    
    public String listarClientes2() {
        if (alquileres.length == 0) {
            return "No hay Clientes registrados.";
        }
        String resultado = "";
        for (Alquiler a : alquileres) {
            resultado += a.toString() + "\n--------------------\n"; // 
        }
        return resultado;
    }
    
}