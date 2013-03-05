package ejercicio1;

import java.util.Random;

public class Resultado {
    // PUEDES CREAR UN ARRAY DE OBJECTOS Y QUEDA MÁS ELEGANTE !!!!
	Object c1 = new Object();
	Object c2 = new Object();
	Object c3 = new Object();
	Object c4 = new Object();
	Object c5 = new Object();
	Object c6 = new Object();
	int resultados[] = new int[6];
	
	public Resultado() {
		this.resultados = resultados;
	}
	
	// PARA QUÉ HAS DEFINIDO EL ARRAY resultados? ÚSALO !!!
	int num1=0, num2=0, num3=0, num4=0, num5=0, num6=0;
	
	synchronized public void tirada(int numero){	
	    
	    // COMO MÍNIMO USA UN SWITCH !!!!
	    // SI HUBIERAS USADO ARRAYS PARA LOS CERROJOS Y LOS NÚMEROS
	    // PODRÍAS USAR numero COMO ÍNDICE DE ELLOS. FÍJATE:
	    /*
	     synchronized(cerrojos[numero]) {
	         resultados[numero]
	     }
	    */
		
		if(numero==0){
			synchronized (c1){
				num1++;
			}
		}
		else if(numero==1){
			synchronized (c2){
				num2++;
			}
		}
		else if(numero==2){
			synchronized (c3){
				num3++;
			}
		}
		else if(numero==3){
			synchronized (c4){
				num4++;
			}
		}
		else if(numero==4){
			synchronized (c5){
				num5++;
			}
		}
		else if(numero==5){
			synchronized (c6){
				num6++;
			}
		}
	}
}
