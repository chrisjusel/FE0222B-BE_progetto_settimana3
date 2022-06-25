import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Studente {
	private int id;
	private String nome;
	private String cognome;
	private char genere;
	private HashMap<String, ArrayList<Integer>> voti;

	public Studente(String nome, String cognome, char genere) {
		this.id = 0;
		this.nome = nome;
		this.cognome = cognome;
		this.genere = genere;
		this.voti = setVoti();
	}

	public double mediaVotoMateria(String materia) throws MateriaNotFoundException{
		double avg = 0;
		if(voti.get(materia) != null) {
			//se l'array di voti esiste
			ArrayList<Integer> votiPerMateria = voti.get(materia);
			avg = (double)votiPerMateria.stream().reduce(0, (subtotal, element) -> subtotal + element) / voti.get(materia).size();
			//calcolo la media riducendo ad un solo valore i valori dell'array e divido per la dimensione dell'array
		} else {
			//altrimenti lancio un'eccezione
			throw new MateriaNotFoundException("Materia non trovata");
		}
		return avg;
	}
	
	public double mediaTotale() {
		double sommaMedieMaterie = 0;
		for(String materia : voti.keySet()) {
			//per ogni materia che c'è nell'hashmap
			try {
				sommaMedieMaterie += mediaVotoMateria(materia);
				//sommo le medie dei voti dell'arraylist corrispondente alla materia
			} catch (MateriaNotFoundException e) {
				e.printStackTrace();
			}
		}
		return sommaMedieMaterie / voti.size();
		//faccio la media dividendo la somma ottenuta per le materie presenti nell'hashmap
	}
	
	public int votoMiglioreMateria(String materia) throws MateriaNotFoundException{
		int max = 0;
		if(voti.get(materia) != null) {
			//se esiste una materia con quel nome
			ArrayList<Integer> votiPerMateria = voti.get(materia);
			//istanzio un nuovo arraylist che conterrà i voti per quella materia
			max =  votiPerMateria.stream().max(Integer::compare).get();
			//calcolo il massimo con il metodo max
		} else {
			throw new MateriaNotFoundException("Materia non trovata");
		}
		return max;
	}
	
	public boolean promosso() {
		Stream a = voti.keySet().stream().filter(voto -> {
			//per ogni studente filtro le materie in cui lo studente
			//ha media inferore a 6
			try {
				return mediaVotoMateria(voto) < 6;
			} catch (MateriaNotFoundException e) {
				return false;
			}
		});
		return a.count() < 4;
		//conto quanti elementi ci sono nello stream
		//se sono più di 4 lo studente è bocciato
		//altrimenti è promosso
	}
	

	
	
	@Override
	public String toString() {
		String s = "";
		s += "\nid: "+ id + " " + nome + " " + cognome +" (" + genere +")\nVoti:";
		for(String materia : voti.keySet()) {
			ArrayList<Integer> votiMateria = voti.get(materia);
			s += "\n" + materia + ": ";
			for(int voto : votiMateria) {
				s += voto + ", ";
			}
		}
		return s += "\n";
	}
	
	private HashMap<String, ArrayList<Integer>> setVoti(){
		//costruisco una nuova HashMap con chiavi prestabilite
		HashMap<String, ArrayList<Integer>> out = new HashMap<>();
		out.put("Italiano", new ArrayList<Integer>());
		out.put("Storia", new ArrayList<Integer>());
		out.put("Geografia", new ArrayList<Integer>());
		out.put("Matematica", new ArrayList<Integer>());
		out.put("Tecnologia", new ArrayList<Integer>());
		out.put("Inglese", new ArrayList<Integer>());
		out.put("Educazione fisica", new ArrayList<Integer>());
		return out;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public char getGenere() {
		return genere;
	}

	public HashMap<String, ArrayList<Integer>> getVoti() {
		return voti;
	}

	public void setId(int id) {
		this.id = id;
	}

}
