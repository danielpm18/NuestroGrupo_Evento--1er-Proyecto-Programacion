import java.io.*;
import java.util.Scanner;

public class Evento {
    private static final String ARCHIVO = "proyecto/data/eventosSTEAM.csv";
    private static final Evento[] evento = new Evento[150];
    private static int contador = 0;
    Scanner scanner = new Scanner(System.in);

    //VARIABLES DEL EVENTO
    private int id;
    private String nombre;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private String lugar;
    private int publicoObjetivo;
    private String URLregistro;

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

    public String formatearEvento() {
        return id + ";" + nombre + ";" + fecha + ";" + horaInicio + ";" + horaFin + ";" + lugar + ";" + publicoObjetivo + ";" + URLregistro;
    }

    public Evento() {
    }

    //METODO PARA CREAR EL EVENTO EN CONSOLA
    public void crearEvento() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el ID del evento a crear:");
        this.id = sc.nextInt();
        sc.nextLine(); //Limpiar

        for (int i = 0; i < contador; i++) {
            if (evento[i].id == id) {
                System.out.println("Ya existe un evento con el ID ingresado. Intente con otro ID.");
                return;
            }
        }

        System.out.println("Ingrese el nombre del evento a crear:");
        this.nombre = sc.nextLine();

        System.out.println("Ingrese la fecha del evento a crear(DD/MM/AAAA):");
        this.fecha = sc.nextLine();

        System.out.println("Ingrese la hora de inicio del evento a crear: (HH:MM)");
        this.horaInicio = sc.nextLine();

        System.out.println("Ingrese la hora de fin del evento a crear: (HH:MM)");
        this.horaFin = sc.nextLine();

        System.out.println("Ingrese el lugar del evento a crear:");
        this.lugar = sc.nextLine();

        System.out.println("Ingrese el publico Objetivo del evento a crear: (1: Estudiantes, 2: Profesorado, 3: Comunidad Universitaria");
        int publico = sc.nextInt();
        while (publico < 1 || publico > 3) {
            System.out.println("El publico debe ser un numero entre el 1-3");
            publico = sc.nextInt();
        }
        this.publicoObjetivo = publico;
        sc.nextLine();

        System.out.println("Ingrese la URL del evento a crear:");
        this.URLregistro = sc.nextLine();

        if (contador < evento.length) { //verificar que haya espacio y crear otra línea
            evento[contador++] = new Evento(id, nombre, fecha, horaInicio, horaFin, lugar, publicoObjetivo, URLregistro);
        } else {
            System.out.println("No hay espacio en el arreglo");
        }
        guardarEvento();
        System.out.println("El evento " + nombre + "  ha sido creado con exito.\n\n");
    }

    //METODO PARA MOSTRAR EL EVENTO EN CONSOLA
    public void mostrarEvento() {
        cargarEvento();
        if(contador == 0){
            System.out.println("No hay eventos para mostrar");
            return;
        }
        System.out.println("Estos son los eventos: ");
        for (int i = 0; i < contador; i++){
            System.out.println("Evento: " +evento[i].nombre);
        }
        System.out.print("Ingrese el nombre a mostrar: ");
        String nombre = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (evento[i].nombre.equalsIgnoreCase(nombre)) {
                Evento evento1 = evento[i];
                System.out.println("ID: " + evento1.id);
                System.out.println("Nombre: " + evento1.nombre);
                System.out.println("Fecha: " + evento1.fecha);
                System.out.println("Hora de inicio: " + evento1.horaInicio);
                System.out.println("Hora de fin: " + evento1.horaFin);
                System.out.println("Lugar: " + evento1.lugar);
                System.out.println("Público objetivo: " + evento1.publicoObjetivo);
                System.out.println("URL de registro: " + evento1.URLregistro);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron eventos con el nombre indicado.");
        }
    }

    //METODO PARA CARGAR EL EVENTO EN CONSOLA
    public static void cargarEvento() {
        try (BufferedReader cE = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            contador = 0;
            while ((linea = cE.readLine()) != null) {
                String[] parte = linea.split(";");
                if (parte.length != 8) {
                    System.out.println("Línea inválida en el archivo: " + linea);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parte[0]);
                    String nombre = parte[1];
                    String fecha = parte[2];
                    String horaInicio = parte[3];
                    String horaFin = parte[4];
                    String lugar = parte[5];
                    int publicoObjetivo = Integer.parseInt(parte[6]);
                    String URLregisto = parte[7];

                    evento[contador++] = new Evento(id, nombre, fecha, horaInicio, horaFin, lugar, publicoObjetivo, URLregisto);
                } catch (NumberFormatException e) {
                    System.out.println("Error al parsear línea: " + linea + " (" + e.getMessage() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }

    //METODO PARA GUARDAR LOS EVENTOS EN CONSOLA
//    public void guardarEvento() throws IOException {
//        try (BufferedWriter gE = new BufferedWriter(new FileWriter(ARCHIVO, false))) {
//            for (int i = 0; i < contador; i++) {
//                gE.write(
//                        evento[i].id + ";" +
//                                evento[i].nombre + ";" +
//                                evento[i].fecha + ";" +
//                                evento[i].horaInicio + ";" +
//                                evento[i].horaFin + ";" +
//                                evento[i].lugar + ";" +
//                                evento[i].publicoObjetivo + ";" +
//                                evento[i].URLregistro + ";"
//                );
//                gE.newLine();
//            }
//        } catch (Exception e) {
//            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
//        }
//    }
    public static void guardarEvento() {
        try (BufferedWriter gE = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < contador; i++) {
                Evento ev = evento[i];
                String linea = ev.id + ";" + ev.nombre + ";" + ev.fecha + ";" + ev.horaInicio + ";" + ev.horaFin + ";" + ev.lugar + ";" + ev.publicoObjetivo + ";" + ev.URLregistro;
                gE.write(linea);
                gE.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de eventos: " + e.getMessage());
        }
    }


    //METODO PARA ACTUALIZAR EL EVENTO EN CONSOLA
    public void actualizarEvento() {
        cargarEvento();
        if(contador == 0){
            System.out.println("No hay eventos para actualizar");
            return;
        }
        System.out.println("Estos los eventos a actualizar");
        for (int i = 0; i < contador; i++){
            System.out.println("Evento: " +evento[i].nombre);
        }
        System.out.println("Ingrese el nombre del evento a actualizar:");
        String nombre = scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {
            if (evento[i].nombre.equalsIgnoreCase(nombre)) {
                Evento evento2 = evento[i];
                System.out.println("Detalles actuales del evento:");
                System.out.println("ID: " + evento2.id);
                System.out.println("Nombre: " + evento2.nombre);
                System.out.println("Fecha: " + evento2.fecha);
                System.out.println("Hora de inicio: " + evento2.horaInicio);
                System.out.println("Hora de fin: " + evento2.horaFin);
                System.out.println("Lugar: " + evento2.lugar);
                System.out.println("Público objetivo: " + evento2.publicoObjetivo);
                System.out.println("URL de registro: " + evento2.URLregistro);

                System.out.println("Ingrese el nombre del evento a actualizar:");
                String nuevonombre = scanner.nextLine();
                if (nuevonombre.length() > 0) { //Verificar si el evento introducido tiene nombre y horas reales
                    evento2.nombre = nuevonombre;
                }
                System.out.println("Ingrese la fecha del evento a actualizar:");
                String nuevafecha = scanner.nextLine();
                if (nuevafecha.length() > 0) {
                    evento2.fecha = nuevafecha;
                }
                System.out.println("Ingrese la hora de inicio del evento a actualizar:");
                String nuevahoraInicio = scanner.nextLine();
                if (nuevahoraInicio.length() > 0) {
                    evento2.horaInicio = nuevahoraInicio;
                }
                System.out.println("Ingrese la hora de fin del evento a actualizar:");
                String nuevahoraFin = scanner.nextLine();
                if (nuevahoraFin.length() > 0) {
                    evento2.horaFin = nuevahoraFin;
                }
                System.out.println("Ingrese el lugar del evento a actualizar:");
                String nuevolugar = scanner.nextLine();
                if (nuevolugar.length() > 0) {
                    evento2.lugar = nuevolugar;
                }
                System.out.println("Ingrese el publico Objetivo del evento a actualizar: (1: Estudiantes, 2: Profesorado, 3: Comunidad Universitaria");
                int nuevopublicoObjetivo = scanner.nextInt();
                if (nuevopublicoObjetivo >= 1 && nuevopublicoObjetivo <= 3) {
                    evento2.publicoObjetivo = nuevopublicoObjetivo;
                } else {
                    System.out.println("Opcion incorrecta. Coloque una de las opciones mostradas");
                }
                System.out.println("Ingrese la URL del evento a actualizar:");
                String nuevoURLregistro = scanner.nextLine();
                scanner.nextLine();
                if (nuevoURLregistro.length() > 0) {
                    evento2.URLregistro = nuevoURLregistro;
                }
                encontrado = true;
                break;
            }
        }
        if (encontrado){
            guardarEvento();
            System.out.println("Evento actualizado con éxtio");
        }else {
            System.out.println("Evento no encontrado");

        }
    }

    //METODO PARA ELIMINAR EL EVENTO EN CONSOLA
    public void eliminarEvento() {
        cargarEvento();
        if (contador == 0) {
            System.out.println("No hay eventos disponibles para eliminar.");
            return;
        }
        System.out.println("Eventos disponibles para eliminar:");
        for (int i = 0; i < contador; i++) {
            System.out.println("Evento: " + evento[i].nombre);
        }
        System.out.println("Ingrese el nombre que desee eliminar: ");
        String nombre = scanner.nextLine();

        boolean eliminado = eliminarPorNombre(nombre);

        if (eliminado) {
            guardarEvento();
            System.out.println("El Evento" +nombre+ " fue eliminado con exito");
        }else{
            System.out.println("No se encontró un evento con el nombre especificado.");
        }

    }

    public boolean eliminarPorNombre(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (evento[i].nombre.equalsIgnoreCase(nombre)) {
                // Desplazar los eventos hacia la izquierda para llenar el vacío
                for (int j = i; j < contador - 1; j++) {
                    evento[j] = evento[j + 1];
                }
                evento[contador - 1] = null; // Limpia la última posición
                contador--; // Reduce el contador de eventos
                return true; // Indica que se eliminó el evento
            }
        }
        return false; // Indica que no se encontró el evento
    }
}




