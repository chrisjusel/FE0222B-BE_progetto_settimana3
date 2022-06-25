import java.io.File;
import java.util.ArrayList;

public interface Scuola {
	public ArrayList<Studente> getStudenti();
	public ArrayList<Studente> getPromossi();
	public Studente getStudenteMigliore();
	public void salvaStudenti(File file, boolean sovrascrivi);
	public void registraStudente(Studente studente);
	public void registraVoto(Studente studente, String materia, int voto);
}
