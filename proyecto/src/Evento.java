import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.util.Scanner;

public class Evento {
    private static final String ARCHIVO = "data\\eventosSTEAM.cvs";
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
    public String URLregistro;


    //CONSTRUCTOR DE EVENTOS
    public Evento(int id, String nombre, String fecha, String horaInicio, String horaFin, String lugar, int publicoObjetivo, String URLregistro) {

        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
        this.publicoObjetivo = publicoObjetivo;
        this.URLregistro = URLregistro;

    }

    //METODO PARA CARGAR EL EVENTO EN CONSOLA
    public static void cargarEvento() {
        try (BufferedReader cE = new BufferedReader(new FileReader("data\\eventosSTEAM.csv"))) {
            String linea;
            int numEvento = 1;
            while ((linea = cE.readLine()) != null) {
                String[] parte = linea.split(";");
                int id = Integer.parseInt(parte[0]);
                String nombre = parte[1];
                String fecha = parte[2];
                String horaInicio = parte[3];
                String horaFin = parte[4];
                String lugar = parte[5];
                int publicoObjetivo = Integer.parseInt(parte[6]);
                String URLregisto = parte[7];
                evento[contador++] = new Evento(id, nombre, fecha, horaInicio, horaFin, lugar, publicoObjetivo, URLregisto);
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }

    }

    //METODO PARA GUARDAR LOS EVENTOS EN CONSOLA
    public static guardarEvento() {
        try (BufferedWriter gE = new BufferedWriter(new FileWriter("data\\eventosGUARDADOS.txt"))) {
            for (int i = 0; i < contador; i++) {
                //  gE.write(Evento[i].)
            }
        }
    }

    //METODO PARA CREAR EL EVENTO EN CONSOLA
    public String crearEvento() {
        return id + ";" + fecha + ";" + nombre + ";" + horaInicio + ";" + horaFin + ";" + lugar + ";" + publicoObjetivo + ";" + URLregistro;
    }

    //METODO PARA MOSTRAR EL EVENTO EN CONSOLA
    public void mostrarEvento() {

        System.out.println("ID: " + id);
        System.out.println("NOMBRE: " + nombre);
        System.out.println("FECHA: " + fecha);
        System.out.println("HORA INICIO: " + horaInicio);
        System.out.println("HORA FIN: " + horaFin);
        System.out.println("LUGAR: " + lugar);
        System.out.println("PUBLICO: " + (publicoObjetivo == 1 ? "Estudiantes" : (publicoObjetivo == 2 ? "Profesorado" : "Comunidad Universitaria")));
        System.out.println("URL: " + URLregistro);
    }

    //METODO PARA ACTUALIZAR EL EVENTO EN CONSOLA
    public void actualizarEvento() {
        System.out.println("Ingrese el ID del evento a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < contador; i++) {
            if (evento[i].id == id) {
                System.out.println("Ingrese el nombre del evento a actualizar:");
                String nuevonombre = scanner.nextLine();
                if (nuevonombre.length() > 0) {
                    evento[i].nombre = nuevonombre;
                }
                System.out.println("Ingrese la fecha del evento a actualizar:");
                String nuevafecha = scanner.nextLine();
                if (nuevafecha.length() > 0) {
                    evento[i].fecha = nuevafecha;
                }
                System.out.println("Ingrese la hora de inicio del evento a actualizar:");
                String nuevahoraInicio = scanner.nextLine();
                if (nuevahoraInicio.length() > 0) {
                    evento[i].horaInicio = nuevahoraInicio;
                }
                System.out.println("Ingrese la hora de fin del evento a actualizar:");
                String nuevahoraFin = scanner.nextLine();
                if (nuevahoraFin.length() > 0) {
                    evento[i].horaFin = nuevahoraFin;
                }
                System.out.println("Ingrese el lugar del evento a actualizar:");
                String nuevolugar = scanner.nextLine();
                if (nuevolugar.length() > 0) {
                    evento[i].lugar = nuevolugar;
                }
                System.out.println("Ingrese el publico Objetivo del evento a actualizar: (1: Estudiantes, 2: Profesorado, 3: Comunidad Universitaria");
                int nuevopublicoObjetivo = scanner.nextInt();
                if (nuevopublicoObjetivo >= 1 && nuevopublicoObjetivo <= 3) {
                    evento[i].publicoObjetivo = nuevopublicoObjetivo;
                } else {
                    System.out.println("Opcion incorrecta. Coloque una de las opciones mostradas");
                }
                System.out.println("Ingrese la URL del evento a actualizar:");
                String nuevoURLregistro = scanner.nextLine();
                if (nuevoURLregistro.length() > 0) {
                    evento[i].URLregistro = nuevoURLregistro;
                }
            }
        }
    }
    //METODO PARA ELIMINAR EL EVENTO EN CONSOLA
    public void eliminarEvento() {
        System.out.println("Ingrese el ID del evento que desee eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < contador; i++){
            if (evento[i].id == id{
                evento[i] = evento[contador - 1];
                evento[contador - 1] = null;
                contador--;
                System.out.println("Evento eliminado con exito");
                return;
            }
        }
        System.out.println("ID no encontrado");
    }
}