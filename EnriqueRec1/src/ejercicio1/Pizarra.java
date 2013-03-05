package ejercicio1;

/**
 * @author Enrique
 * @version 1.0
 */
public class Pizarra {
    // UN ARRAY NO HABRÍA HECHO DAÑO.
	private int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0;
	// OTRO ARRAY PARA LOS CERROJOS TAMPOCO HABRÍA HECHO NINGÚN DAÑO.
	Object O1 = new Object();
	Object O2 = new Object();
	Object O3 = new Object();
	Object O4 = new Object();
	Object O5 = new Object();
	Object O6 = new Object();
	
	public void apuntar(int num){
	    
	    // YA QUE NO UTILIZAS ARRAY PODRÍAS POR LO MENOS HABER UTILIZADO UN SWITCH.
	    
		if(num == 1){
			synchronized(O1){
				num1++;
				System.out.println(Thread.currentThread().getName()+" a sacado 1");
			}
		}else if(num == 2){
			synchronized(O2){
				num2++;
				System.out.println(Thread.currentThread().getName()+" a sacado 2");
			}
		}else if(num == 3){
			synchronized(O3){
				num3++;
				System.out.println(Thread.currentThread().getName()+" a sacado 3");
			}
		}else if(num == 4){
			synchronized(O4){
				num4++;
				System.out.println(Thread.currentThread().getName()+" a sacado 4");
			}
		}else if(num == 5){
			synchronized(O5){
				num5++;
				System.out.println(Thread.currentThread().getName()+" a sacado 5");
			}
		}else{
			synchronized(O6){
				num6++;
				System.out.println(Thread.currentThread().getName()+" a sacado 6");
			}
		}
	}
	
	// PIENSA CUANTAS FUNCIONES TE HABRÍAS AHORRADO CON UN ARRAY.
	
	public int getNum1(){
		return num1;
	}
	public int getNum2(){
		return num2;
	}
	public int getNum3(){
		return num3;
	}
	public int getNum4(){
		return num4;
	}
	public int getNum5(){
		return num5;
	}
	public int getNum6(){
		return num6;
	}
	public int total(){
		int total = num1+num2+num3+num4+num5+num6;
		return total;
	}
}
