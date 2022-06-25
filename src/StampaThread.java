
public class StampaThread extends Thread {
	
	private ScuolaImpl scuola;
	
	public StampaThread(ScuolaImpl scuola) {
		this.scuola = scuola;
	}
	
	public void run() {
		for(Studente studente : scuola.getStudenti()) {
			//per ogni studente della scuola
			System.out.println(studente);
			//stampo i suoi dati
			try {
				Thread.sleep(2000);
				//ogni 2 secondi
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
