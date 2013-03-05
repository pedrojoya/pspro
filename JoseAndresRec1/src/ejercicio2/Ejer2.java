package ejercicio2;

// FUNCIONA EL CÓDIGO, PERO LO HACES ALGO LIOSO.
// COSAS RARAS:
// EL CONTROL DEL NÚMERO DE PLATO QUE SE ESTÁ MANEJANDO EN
// CADA MOMENTO ES BASTANTE ARTESANAL.
// TENER UNA ÚNICA CLASE CONTROLADORA DE LAS DOS PILAS DE PLATOS
// ES BASTANTE INEFICIENTE, YA QUE PODRÍAS HABER CREADO UNA CLASE
// LLAMADA PILAPLATOS Y HABER CREADO DOS OBJETOS DE DICHA CLASE
// QUE FUERAN COMPARTIDAS LA PRIMERA POR PEDRO-JUAN Y LA SEGUNDA
// POR JUAN-PEPE.
// DE HECHO TIENES UNA ÚNICA FUNCIÓN DE COLOCAR, CUANDO EN CUALQUIER CASO
// PODRÍAS HABER USADO TRES DISTINTAS DEPENDIENDO DE LA ACTIVIDAD: FREGAR, SECAR, COLOCAR.

public class Ejer2 {

    
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Alacena alacena = new Alacena();
		int MAXPLATOS=25;
		Pedro pedro = new Pedro(alacena, MAXPLATOS);
		Juan juan = new Juan(alacena, MAXPLATOS);
		Pepe pepe = new Pepe(alacena, MAXPLATOS);
		
		Thread hiloPedro=new Thread(pedro);
		Thread hiloJuan=new Thread(juan);
		Thread hiloPepe=new Thread(pepe);
		
		hiloPedro.start();
		hiloJuan.start();
		hiloPepe.start();
		
		hiloPedro.join();
		hiloJuan.join();
		hiloPepe.join();
		
	}

}
