package ejercicio2;

public class Pedro implements Runnable{
	
	Alacena alacena;
	int MAXPLATOS;
	int cont=0;
	public Pedro(Alacena alacena, int MAXPLATOS) {
		this.alacena = alacena;
		this.MAXPLATOS=MAXPLATOS;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(cont=0;cont<MAXPLATOS;cont++){
				alacena.fregar(cont);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
