import java.util.Scanner;
import java.io.File;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner (System.in);
        Evento [] evento = new Evento[150];
        System.out.println("Que es lo que deseas realizar? (1: Mostrar Evento \n 2: Crear Evento \n 3: Actualizar Evento \n 4: Eliminar Evento \n 5: Salir)");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                Evento.mostrarEvento(scanner);
                break;
            case 2:
                Evento.crearEvento(scanner);
                break;
            case 3:
                Evento.actualizarEvento(scanner);
                break;
            case 4:
                Evento.eliminarEvento(scanner);
                break;
            case 5:
                System.out.println("Saliendo del sistema...");
                Evento.guardarEvento(scanner);
                return;
            default:
                System.out.println("Opcion incorrecta. Escoja uno de las opciones:");
        }

    }
}
