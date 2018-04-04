package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	private CorsoDAO corsoDAO=null;
	private StudenteDAO studenteDAO=null;
	
	public Model() {
		corsoDAO= new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getAllCourses(){
		List<Corso> corsi;
		corsi = new ArrayList<Corso>(this.corsoDAO.getTuttiICorsi());
		return corsi;
	}
	
	public List<String> getNameAllCourses(){
		List<String> nomeCorsi= new ArrayList<String>();
		List<Corso> corsi = new ArrayList<Corso>(this.corsoDAO.getTuttiICorsi());
		for(Corso c : corsi) {
			nomeCorsi.add(c.getNome());
		}
		return nomeCorsi;
	}
	
	public Studente getStudenteFromID(int matricola) {
		return this.studenteDAO.getStudentFromID(matricola);
	}
	
	public String[] getNomeCognome(int matricola) {
		Studente s = this.getStudenteFromID(matricola);
		String tmp[] = new String[2];
		tmp[0]=s.getNome();
		tmp[1]=s.getCognome();
		return tmp;
	}
	
	public Corso getCorsoFromCodins(String codins) {
		return this.corsoDAO.getCorso(codins);
	}
	
	public List<Studente> getStudentiIscrittiCorso(String codins){
		return this.corsoDAO.getStudentiIscrittiAlCorso(codins);
	}
	
	public String studentiIscrittiToString(String codins) {
		StringBuilder sb = new StringBuilder();
		for(Studente s : this.getStudentiIscrittiCorso(codins)) {
			sb.append(s.toString()+"\n");
		}
		return sb.toString();
	}
	
	public Corso getCorsoFromNome(String nome) {
		List<Corso> corsi = new ArrayList(this.getAllCourses());
		for(Corso c : corsi) {
			if(c.getNome().toLowerCase().equals(nome.toLowerCase())){
				return c;
			}
		}
		return null;
		
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return this.studenteDAO.corsiIscrittoFromStudente(matricola);
		
	}
	
	public String getCorsiStudenteToString(int matricola) {
		StringBuilder sb = new StringBuilder();
		for(Corso c : this.getCorsiStudente(matricola)) {
			sb.append(c.toString()+"\n");
		}
		return sb.toString();
		
	}
	
	public boolean getIscrizione(int matricola, String codins) {
		return this.studenteDAO.checkIscrizione(matricola, codins);
	}

 	
	
	

}
