package it.polito.tdp.lab04.DAO;

import java.util.*;
import java.sql.*;


import it.polito.tdp.lab04.model.*;

public class StudenteDAO {
	
	
	
	public List<Studente> getAllStudents(){
		
		final String sql = "SELECT matricola,cognome,nome,CDS  FROM studente";
		List<Studente> studenti = new ArrayList<Studente>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				Studente s = new Studente();
				s.setMatricola(matricola);
				s.setCognome(cognome);
				s.setNome(nome);
				s.setCDS(CDS);
				
				studenti.add(s);
				
				
			}
			
			conn.close();
			return studenti;
		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cerca uno studente data la sua matricola
	 * @param matricola
	 * @return
	 */
	
	public Studente getStudentFromID(int matricola) {
		final String sql = "SELECT matricola,cognome,nome,CDS  FROM studente WHERE matricola= ?";
		Studente s = null;
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
			s=new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
			}
			
			
			
			return s;
		
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return s;
	}
	
	
	/**
	 * Restituisce i corsi a cui è iscritto uno studente 
	 * @param matricola
	 * @return
	 */
	public List<Corso> corsiIscrittoFromStudente(int matricola){
		
			CorsoDAO corsoDAO = new CorsoDAO();
			final String sql = "SELECT codins  FROM iscrizione WHERE matricola= ?";
			List<Corso> corsi = new ArrayList<Corso>();
			try {
				
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					String codins = rs.getString("codins");
					corsi.add(corsoDAO.getCorso(codins));
					
				}
				
				return corsi;
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
		return null;
		
	}
	/**
	 * Metodo che controllo se uno studente è iscritto ad un determinato corso
	 * @param matricola
	 * @param codins
	 * @return
	 */
	public boolean checkIscrizione(int matricola,String codins) {
		boolean iscritto = false;
		CorsoDAO corsoDAO = new CorsoDAO();
		final String sql = "Select matricola from iscrizione where matricola ="+matricola+" and codins ='"+codins+"'";
		List<Corso> corsi = new ArrayList<Corso>();
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				iscritto = true;
			}
			else
				iscritto = false;
			
			
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return iscritto;
		
	}
	
	
	
	

}
