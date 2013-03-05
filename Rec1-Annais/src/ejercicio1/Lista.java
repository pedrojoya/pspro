/**
 * 
 */
package ejercicio1;

/**
 * @author Annais
 *
 */
public class Lista {
	
	int lista[];
	
	Lista(){
		lista=new int[6];
	}
	
	public void contarResult(int dado){
		lista[dado]=(lista[dado])+1;
	}
	
	public void listarLista(){
		for(int i=0;i<6;i++){
			System.out.println("Resultado "+(i+1)+": "+lista[i]);
		}
	}
	
	public void resultado(){
		int sol=0;
		for(int i=0;i<6;i++){
			sol=sol+lista[i];
		}
		System.out.println("En Total: "+sol+" Tiradas de dado.");
	}
}
