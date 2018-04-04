package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.*;
import java.util.*;
import it.polito.tdp.lab04.model.*;


public class CorsoDAO {
	
	
	

	/**
	 * ottengo tutti i corsi salvati sul db
	 * @return corsi 
	 */
	public List<Corso> getTuttiICorsi() {
		
		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Corso c = new Corso();
				c.setCodins(codins);
				c.setCrediti(numeroCrediti);
				c.setNome(nome);
				c.setPd(periodoDidattico);
				
				corsi.add(c);
			}
			
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

		
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codins) {
		final String sql = "SELECT codins,crediti,nome,pd FROM corso WHERE codins="+"'"+codins+"'";

		Corso c = null;

		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
			}
			
			return c;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	
	

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String codins) {
		
		StudenteDAO studenteDAO = new StudenteDAO();
		List<Studente> studenti = new ArrayList<Studente>();
		final String sql = "SELECT matricola FROM iscrizione WHERE codins="+"'"+codins+"'";
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				studenti.add(studenteDAO.getStudentFromID(rs.getInt("matricola")));
			}
			
			
			
			return studenti;
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		
		return studenti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
