import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Evento e = new Evento();

        Evento [] evento = new Evento[150];

        System.out.println("Que es lo que deseas realizar? \n 1: Registrar Evento \n 2: Mostrar Eventos \n 3: Quitar Evento \n 4: Salir");

        int opcion;
        do {
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> e.crearEvento();
                case 2 -> e.mostrarEvento();
                case 3 -> e.eliminarEvento();
                case 4 -> System.out.println("Programa terminado. ¡Hasta Luego!");
                default -> System.out.println("Opcion incorrecta. Coloque una de las opciones mostradas");

            }

        } while (opcion != 4);
    }
}
