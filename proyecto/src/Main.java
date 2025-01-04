import java.util.Scanner;
import java.io.File;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Evento e = new Evento();


        Evento [] evento = new Evento[150];
        System.out.print("ID:");

        System.out.println("Que es lo que deseas realizar? (1: Registrar Evento \n 2: Mostrar Eventos \n 3: Quitar Evento \n 4:Salir)");

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

            }

        } while (opcion != 4);
    }
}
