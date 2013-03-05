package ejercicio1;

/**
 * @author Enrique
 * @version 1.0
 */

// OK. EL PROGRAMA FUNCIONA PERO HAY UNA COSA EN PROGRAMACIÓN QUE SE LLAMAN ARRAYS
// QUE SON MUCHO MÁS ELEGANTES QUE PONER 6 VARIABLES. Y SI EL DADO FUERA DE ESTOS DE LOS
// JUEGOS DE ROL QUE TIENEN 12 LADOS, PONDRÍAS 12 VARIABLES?

public class Principal {
	public static void main(String[] args) throws InterruptedException {
		//Creo el objeto común
		Pizarra pizarra = new Pizarra();
		
		Thread hilo1 = new Thread(new Tirar(pizarra), "persona1");
		Thread hilo2 = new Thread(new Tirar(pizarra), "persona2");
		Thread hilo3 = new Thread(new Tirar(pizarra), "persona3");
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		hilo1.join();
		hilo2.join();
		hilo3.join();
		
		// SI HUBIERAS USADO ARRAYS TENDRÍAS AQUÍ UN BUCLE.
		
		System.out.println("Valor de los numeros:"+
				"\nDel numero 1: "+pizarra.getNum1()+
				"\nDel numero 2: "+pizarra.getNum2()+
				"\nDel numero 3: "+pizarra.getNum3()+
				"\nDel numero 4: "+pizarra.getNum4()+
				"\nDel numero 5: "+pizarra.getNum5()+
				"\nDel numero 6: "+pizarra.getNum6()+
				"\nTOTAL de tiradas: "+pizarra.total());
	}
}
