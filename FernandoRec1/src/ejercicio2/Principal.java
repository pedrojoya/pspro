package ejercicio2;

//FUNCIONA EL C�DIGO, PERO HACES COSAS POCO ELEGANTES:
//REUTILIZAS PAR�METROS COMO VARIABLE LOCAL QUE SE MODIFICA.
//TENER UNA �NICA CLASE CONTROLADORA DE LAS DOS PILAS DE PLATOS
//ES BASTANTE TOSCO A NIVEL DE DISE�O, YA QUE PODR�AS HABER CREADO UNA CLASE
//LLAMADA PILAPLATOS Y HABER CREADO DOS OBJETOS DE DICHA CLASE
//QUE FUERAN COMPARTIDAS LA PRIMERA POR PEDRO-JUAN Y LA SEGUNDA
//POR JUAN-PEPE.

public class Principal {
	public static void main(String[] args) {
		Almacen almacen = new Almacen();
		
		Pedro hiloFregador = new Pedro(almacen);
		Juan hiloSecador = new Juan(almacen);
		Pepe hiloColocador = new Pepe(almacen);
		
		hiloFregador.start();
		hiloSecador.start();
		hiloColocador.start();
	}
}
