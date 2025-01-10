import java.io.*;
import java.util.Scanner;

public class Evento {
    private static final String ARCHIVO = "proyecto/data/eventosSTEAM.csv";
    private static final Evento[] evento = new Evento[150]; //TIENE Q IR EN MAYUS
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
// RETORNA LA DATA
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
        this.publicoObjetivo = publicoObjetivo;
        this.URLregistro = URLregistro;
    }

    public Evento() {
    }

    //METODO PARA CREAR EL EVENTO EN CONSOLA
    public void crearEvento() {
        if(contador > evento.length){
            System.out.println("No hay mas espacios para agregar eventos");
            return;
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el ID del evento a crear:");
        this.id = sc.nextInt();
        sc.nextLine(); //Limpiar

        for (int i = 0; i < contador; i++) { //Verifica para que ningún evento contenga el mismo ID.
            if (evento[i].id == id) {
                System.out.println("Ya existe un evento con el ID ingresado. Intente con otro ID.");
                return;
            }
        }

        //Utiliza la consola para introducir datos y crear evento para STEAM.
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
        sc.nextLine(); //limpiar

        System.out.println("Ingrese la URL del evento a crear:");
        this.URLregistro = sc.nextLine();

       evento[contador++] = new Evento(id, nombre, fecha, horaInicio, horaFin, lugar, publicoObjetivo, URLregistro);

        guardarEvento();
        System.out.println("El evento " + nombre + " ha sido creado con éxito.\n\n"); //Se creó el evento con éxito.
    }

    //METODO PARA MOSTRAR EL EVENTO EN CONSOLA
    public void mostrarEvento() {
        cargarEvento();
        if (contador == 0) { //Si el CSV está vacío mostrará este mensaje y acabará la función.
            System.out.println("No hay eventos para mostrar");
            return;
        }
        System.out.println("Ingrese una opción de búsqueda: \n  1. Buscar eventos en el mismo dia." +
                "\n  2. Buscar evento de la misma semana.\n  3. Buscar eventos en el mismo mes."); //Pregunta sobre qué día/semana/mes quieres ver que tiene eventos

        int opcion = scanner.nextInt();
        scanner.nextLine(); //Limpiar

        System.out.println("Ingrese la fecha de busqueda(DD/MM/AAAA):");
        String fechaBuscar = scanner.nextLine();
        String[] fechaPartes = fechaBuscar.split("/"); //Separa la fecha para compararlos separados.

        String diaFechaBuscar = fechaPartes[0];
        String mesFechaBuscar = fechaPartes[1];
        String anoFechaBuscar = fechaPartes[2];

        boolean encontrado = false; //Mientras este falso ningún evento cumple con la condición de la función.

        String[] partesFechaEvento;


        for (int i =0; i<contador; i++){

            partesFechaEvento = evento[i].fecha.split("/"); //Separa el día mes y año de la fecha de todos los eventos del CSV.

            String diaEvento = partesFechaEvento[0];
            String mesEvento = partesFechaEvento[1];
            String anoEvento = partesFechaEvento[2];

            boolean coincide = false;
            switch (opcion){ //Compara cada caso y si coindicen vuelve "coincide" verdadero y se imprimen los eventos.
                case 1: //Verifica si el dia año y mes son iguales
                    coincide = diaFechaBuscar.equals(diaEvento) && mesFechaBuscar.equals(mesEvento) && anoFechaBuscar.equals(anoEvento);
                    break;

                case 2:
                    coincide = diaFechaBuscar.equals(diaEvento) && mesFechaBuscar.equals(mesEvento);
                    break;

                case 3:
                    coincide = diaFechaBuscar.equals(diaEvento) && anoFechaBuscar.equals(anoEvento);
                    break;

                default:
                    System.out.println("Opcion incorrecta. Coloque una de las opciones mostradas");

            }

            System.out.println("Estos son los eventos encontrados según su búsqueda:\n");

            if (coincide) {
                System.out.println("ID: " + evento[i].id);
                System.out.println("Nombre: " + evento[i].nombre);
                System.out.println("Fecha: " + evento[i].fecha);
                System.out.println("Hora de inicio: " + evento[i].horaInicio);
                System.out.println("Hora de fin: " + evento[i].horaFin);
                System.out.println("Lugar: " + evento[i].lugar);
                System.out.println("Público objetivo: " + evento[i].publicoObjetivo);
                System.out.println("URL de registro: " + evento[i].URLregistro);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se han encontrado eventos para la fecha especificada.");
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
        if(contador == 0){ //Si no hay eventos en el CSV manda este mensaje y la función se detiene.
            System.out.println("No hay eventos para actualizar");
            return;
        }
        System.out.println("Estos los eventos a actualizar");
        for (int i = 0; i < contador; i++) {
            System.out.println("Evento ID: " +evento[i].id+ "| Evento Nombre: " +evento[i].nombre); //Muestra todos los eventos disponibles que se puedan actualizar.
        }
        System.out.println("Ingrese el ID del evento que desee actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {
            if (evento[i].id == id) { //Al evento ser encontrado con el ID, te muestra la información que se pueda actualizar.
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
                    System.out.println("Opción incorrecta. Coloque una de las opciones mostradas");
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
            System.out.println("Evento actualizado con éxito");
        }else {
            System.out.println("Evento no encontrado");

        }
    }

    //METODO PARA ELIMINAR EVENTOS
    public void eliminarEvento() {
        cargarEvento();

        if (contador == 0) {
            System.out.println("No hay eventos disponibles para eliminar."); //Si el CSV no tiene eventos manda este mensaje y deja de correr la función.
            return;
        }
        System.out.println("Eventos disponibles para eliminar:");
        for (int i = 0; i < contador; i++) {
            System.out.println("Evento ID: " + evento[i].id + "| Nombre: " +evento[i].nombre); //Muestra todos los eventos con información util para elegir cuál eliminar.
        }
        System.out.println("Ingrese el ID que desee eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean eliminado = eliminarPorNombre(id);

        if (eliminado) {
            guardarEvento();
            System.out.println("El Evento " + id + " fue eliminado con éxito");
        } else {
            System.out.println("No se encontró un evento con el nombre especificado.");
        }

    }

    public boolean eliminarPorNombre(int id) { //Función para eliminar el evento introducido.
        for (int i = 0; i < contador; i++) {
            if (evento[i].id == id) {
                for (int j = i; j < contador - 1; j++) {
                    evento[j] = evento[j + 1];
                }
                evento[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    public void estadisticasEventos() {

        cargarEvento();

        if(contador == 0) { System.out.println("No hay eventos registrados.\n"); return; }

        //Contador de cantidad de eventos al mes.
        int[] eventosMes = new int[12];

        for(int i = 0; i<contador; i++){
            String[] partesFecha = evento[i].fecha.split("/"); // Divide la fecha en trozos
            int mes = Integer.parseInt(partesFecha[1]) - 1; // Toma el mes del String y lo convierte en un int.
            eventosMes[mes]++;
        }

        int maxEventosMes = 0;
        int mesMasEventos = 0;

        for (int i = 0; i< 12; i++) { //Compara todos los meses con la cantidad de eventos que tiene cada uno.
            if (eventosMes[i] > maxEventosMes) {
                maxEventosMes = eventosMes[i];
                mesMasEventos = i;

            }
        }

        String[] nombreDeMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}; //Array con nombre de todos los meses.

        System.out.println("Mes con más eventos: " + nombreDeMeses[mesMasEventos] + " con " + maxEventosMes + " eventos.\n");

    }
}




