import java.util.Scanner;
import java.io.File;
public class Main {
    private static final String ARCHIVO = "eventos.txt";
    private static Evento[] evento = new Evento[150];
    private static int contador = 0;

    public static void main(String[] args) {
        //cargareventos();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-Bienvenido a nuestro calendario de Eventos-\n");
            System.out.println("¿Que es lo que deseas realizar? \n 1: Registrar Evento \n 2: Mostrar Eventos \n 3: Quitar Evento \n 4:Salir ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

            }

        }
    }
        }


