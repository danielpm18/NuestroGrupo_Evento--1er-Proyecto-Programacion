import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Evento {
    private static final String ARCHIVO = "eventos.txt";
    private static final Evento[] evento = new Evento[150];
    private static int contador = 0;
    Scanner scanner = new Scanner(System.in);

    //VARIABLES DEL EVENTO
    public int id;
    public String nombre;
    public String fecha;
    public String horaInicio;
    public String horaFin;
    public String lugar;
    public int publicoObjetivo;
    public String urlRegistro;


    //CONSTRUCTOR DE EVENTOS
    public Evento(int id, String nombre,String fecha, String horaInicio, String horaFin, String lugar, int publicoObjetivo, String urlRegistro) {

        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
        this.publicoObjetivo = publicoObjetivo;
        this.urlRegistro = urlRegistro;

    }

    //METODO PARA CARGAR EL EVENTO EN CONSOLA
    public static void cargarEvento() {
        try(BufferedReader br = new BufferedReader(new FileReader("eventos.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
              //  evento[contador++] = Evento.fromString(Linea);
            }
        }catch (Exception e){
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }

    }
    //METODO PARA CREAR EL EVENTO EN CONSOLA
    public String escribirEvento() {
        return id+";"+fecha+";"+nombre+";"+horaInicio+";"+horaFin+";"+lugar+";"+publicoObjetivo+";"+urlRegistro;
    }

    //METODO PARA MOSTRAR EL EVENTO EN CONSOLA
    public void mostrarEvento() {

        System.out.println("ID: " + id);
        System.out.println("NOMBRE: " + nombre);
        System.out.println("FECHA: " + fecha);
        System.out.println("HORA INICIO: " + horaInicio);
        System.out.println("HORA FIN: " + horaFin);
        System.out.println("LUGAR: "+ lugar);
        System.out.println("PUBLICO: " + (publicoObjetivo==1 ? "Estudiantes" : (publicoObjetivo==2 ? "Profesres" : "Tod@s en la Universidad")));
        System.out.println("URL: " + urlRegistro);
    }
    //METODO PARA ACTUALIZAR EL EVENTO EN CONSOLA
    public void actualizarEvento() {
        System.out.println("Ingrese el ID del evento a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombre del evento a actualizar:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la fecha del evento a actualizar:");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese la hora de inicio del evento a actualizar:");
        String horaInicio = scanner.nextLine();
        System.out.println("Ingrese la hora de fin del evento a actualizar:");
        String horaFin = scanner.nextLine();
        System.out.println("Ingrese el lugar del evento a actualizar:");
        String lugar = scanner.nextLine();
        System.out.println("Ingrese el objetivo del evento a actualizar:");
        int publicoObjetivo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la URL del evento a actualizar:");
        String urlRegistro = scanner.nextLine();
    }


}