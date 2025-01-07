import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Evento e = new Evento();
        int opcion;
        do {
            System.out.println("Que es lo que deseas realizar? \n 1: Crear Evento \n 2: Mostrar Eventos \n 3: Actualizar Evento \n 4: Eliminar Evento \n 5: Salir\n");
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
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
                    System.out.println("Programa terminado. ¡Hasta Luego!");
                    e.guardarEvento();
                    return;
                default:
                    System.out.println("Opcion incorrecta. Coloque una de las opciones mostradas");
            }
        } while (opcion != 5);
    }
}
