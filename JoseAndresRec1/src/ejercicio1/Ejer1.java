package ejercicio1;

import java.sql.Array;

// SI TUVIERA QUE CALIFICAR TU EJERCICIO DIRÍA QUE ESTÁ HECHO POR "FUERZA BRUTA" ;-)
// DEBERÍAS MEJORAR ESOS DETALLES PARA QUE TU CÓDIGO TENGA ASPECTO PROFESIONAL.

public class Ejer1 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Resultado resultados = new Resultado();

		Thread hilo1 = new Thread(new Trabajo(resultados));
		Thread hilo2 = new Thread(new Trabajo(resultados));
		Thread hilo3 = new Thread(new Trabajo(resultados));
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		hilo1.join();
		hilo2.join();
		hilo3.join();
		
		// SI HUBIERAS USADO UN ARRAY PARA LOS RESULTADOS PODRÍAS
		// USAR AQUÍ UN BUCLE.
		System.out.println("Repeticiones: \nNum1 = "+resultados.num1+"\nNum2 = "+resultados.num2+"\nNum3 = "+resultados.num3
				+"\nNum4 = "+resultados.num4+"\nNum5 = "+resultados.num5+"\nNum6 = "+resultados.num6);
	}

}
