/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ComboBox"
    private ComboBox<String> ComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtElenco"
    private TextArea txtElenco; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void DoCercaCorsi(ActionEvent event) {
    	try {
    	int matricola = Integer.parseInt(this.txtMatricola.getText());
    	this.txtElenco.appendText(this.model.getCorsiStudenteToString(matricola));
    	}catch(NumberFormatException e ) {
    		e.printStackTrace();
    		this.txtElenco.appendText("Errore: inserire matricola valida\n");
    	}
    }

    @FXML
    void DoCercaIscrittiCorso(ActionEvent event) {
    	
    	String nomeCorso = this.ComboBox.getValue();
    	if(nomeCorso == null) {
    		this.txtElenco.appendText("Errore: selezionare il corso!\n");
    	}
    	else {
    	Corso c = this.model.getCorsoFromNome(nomeCorso);
    	String codins = c.getCodins();
    	
    	this.txtElenco.appendText(this.model.studentiIscrittiToString(codins));
    	}

    }

    @FXML
    void DoCheck(ActionEvent event) {
    	try {
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
    		String tmp[] = this.model.getNomeCognome(matricola);
    		this.txtNome.setText(tmp[0]);
    		this.txtCognome.setText(tmp[1]);
    		
    		
    	}catch(NumberFormatException e ) {
    		e.printStackTrace();
    		this.txtElenco.appendText("Errore: inserire matricola valida\n");
    	}

    }

    @FXML
    void DoIscrivi(ActionEvent event) {
    	try {
    	int matricola = Integer.parseInt(this.txtMatricola.getText());
    	String nomeCorso = this.ComboBox.getValue();
    	
    	if(nomeCorso == null) {
    		this.txtElenco.appendText("Errore: selezionare il corso!\n");
    		return;
    	}
    	
    	Corso c = this.model.getCorsoFromNome(nomeCorso);
    	String codins = c.getCodins();
    	
    	
    
    	if(this.model.getIscrizione(matricola, codins)) {
    		this.txtElenco.appendText("Studente Iscritto!\n");
    		return;
    	}
    	else {
    		this.txtElenco.appendText("Studente non Iscritto!\n");
    		return;
    	}
    		
    	}catch(NumberFormatException e ) {
    		e.printStackTrace();
    		this.txtElenco.appendText("Errore: inserire matricola valida\n");
    	}

    }

    @FXML
    void DoReset(ActionEvent event) {
    	this.txtElenco.clear();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtElenco != null : "fx:id=\"txtElenco\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
		this.ComboBox.getItems().addAll(this.model.getNameAllCourses());
	}
    
    
    
    
}