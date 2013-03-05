package ejercicio2;

public class Pepe implements Runnable{
	Alacena alacena;
	int cont;
	int MAXPLATOS;
	
	public Pepe(Alacena alacena, int MAXPLATOS) {
		this.MAXPLATOS=MAXPLATOS;
		this.alacena = alacena;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(cont=0;cont<MAXPLATOS;cont++){
				alacena.colocar(cont);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
