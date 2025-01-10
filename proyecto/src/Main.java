import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Evento e = new Evento();
        int opcion;
        do {
            System.out.println("-----------------------------------------------");
            System.out.println("Que es lo que deseas realizar? \n 1: Crear Evento \n 2: Mostrar Eventos \n 3: Actualizar Evento " +
                    "\n 4: Eliminar Evento \n 5: Mostrar Mes con mas Eventos \n 6: Salir.");
            System.out.println("-----------------------------------------------");

            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) { //bucle con todas las opciones
                case 1:
                    e.crearEvento();
                    break;
                case 2:
                    e.mostrarEvento();
                    break;
                case 3:
                    e.actualizarEvento();
                    break;
                case 4:
                    e.eliminarEvento();
                    break;
                case 5:
                    e.estadisticasEventos();
                    break;
                case 6:
                    System.out.println("Programa terminado. ¡Hasta Luego!");
                    Evento.guardarEvento();
                    return;
                default:
                    System.out.println("Opcion incorrecta. Coloque una de las opciones mostradas");
            }
        } while (opcion != 6); //repetir hasta que utilize la opcion de salida.
    }
}
