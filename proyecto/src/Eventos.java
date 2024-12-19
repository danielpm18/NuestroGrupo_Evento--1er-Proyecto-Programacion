public class Eventos {

    //VARIABLES DEL EVENTO
    public int id;
    public String nombre;
    public String horaInicio;
    public String horaFin;
    public String lugar;
    public int publicoObjetivo;
    public String urlRegistro;


    //CONSTRUCTOR DE EVENTOS
    public Eventos(int id, String nombre, String horaInicio, String horaFin, String lugar, int publicoObjetivo, String urlRegistro) {

        this.id = id;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
        this.publicoObjetivo = publicoObjetivo;
        this.urlRegistro = urlRegistro;

    }

    public String escribirEvento() {
        return id+";"+nombre+";"+horaInicio+";"+horaFin+";"+lugar+";"+publicoObjetivo+";"+urlRegistro;

    }

    //METODO PARA MOSTRAR EL EVENTO EN CONSOLA
    public void mostrarEvento() {

        System.out.println("ID: " + id);
        System.out.println("NOMBRE: " + nombre);
        System.out.println("HORA INICIO: " + horaInicio);
        System.out.println("HORA FIN: " + horaFin);
        System.out.println("LUGAR: "+ lugar);
        System.out.println("PUBLICO: " + (publicoObjetivo==1 ? "Estudiantes" : (publicoObjetivo==2 ? "Profesres" : "Tod@s en la Universidad")));
        System.out.println("URL: " + urlRegistro);
    }

}
