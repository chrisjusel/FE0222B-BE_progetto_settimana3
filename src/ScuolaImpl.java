import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public class ScuolaImpl implements Scuola {
	private ArrayList<Studente> studenti;

	public ScuolaImpl() {
		studenti = new ArrayList<Studente>();
	}

	public ArrayList<Studente> getStudenti() {
		ArrayList<Studente> tmp = null;
		//inizializzo un arraylist a null
		if(studenti != null) {
			//se l'arraylist di studenti non è null
			tmp = (ArrayList<Studente>)studenti.stream().collect(Collectors.toList());
			//faccio una copia di studenti in tmp
		}
		return tmp;
	}
	
	public ArrayList<Studente> getPromossi(){
		List<Studente> studentiPromossi = null;
		if(studenti != null) {
			studentiPromossi = studenti.stream().filter(studente -> studente.promosso()).collect(Collectors.toCollection(ArrayList::new));
			//inizializzo studenti promossi con la collezione derivata da tutti gli elementi di studenti promossi
		}
		return (ArrayList<Studente>)studentiPromossi;
	}
	
	public Studente getStudenteMigliore() {
		Studente best = null;
		if(studenti != null) {
			best = studenti.stream().max(Comparator.comparingDouble(Studente::mediaTotale)).get();
			//inizializzo il miglior studente con lo studente che ha media totale dei voti più alta
		}
		return best;
	}
	
	public void salvaStudenti(File file, boolean sovrascrivi) {
		String s = "";
		for(Studente studente : getStudenti()) {
			//per ogni studente presente nell'arraylist di studenti
			s += studente;
			//concateno i suoi dati ad una stringa
		}
		if(file.exists()) {
			//se il file passato in input esiste
			try {
				FileUtils.writeStringToFile(file, s, "UTF-8", sovrascrivi);
				//scrivo la stringa su file
			} catch (IOException e) {}
		}
	}
	
	public void registraStudente(Studente studente) {
		studenti.add(studente);
		//aggiungo lo studente nella lista di studenti
		studente.setId(studenti.size());
		//inizializzo il suo id con la lunghezza della lista di studenti
		
	}
	
	public void registraVoto(Studente studente, String materia, int voto){
		if(studente.getVoti().containsKey(materia)) {
			//se esiste la materia di cui voglio aggiungere il voto dello studente
			studente.getVoti().get(materia).add(voto);
			//aggiungo il voto dello studente nell'arraylist corrispettivo alla chiave della materia
		}
	}
}
