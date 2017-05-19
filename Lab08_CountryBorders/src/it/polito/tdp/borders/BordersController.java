/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model m;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
	public void setModel(Model model) {
		this.m=model;
		
	}

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		String anno=txtAnno.getText();
		
		if(anno.matches("[a-zA-Z]*")){
			txtResult.setText("Inserire solo caratteri numerici");
			return;}
		
		m.creaGrafo(Integer.parseInt(anno));
		
		m.calcolaConfini(Integer.parseInt(anno));
		
		txtResult.setText(m.calcolaConfini(Integer.parseInt(anno))+"\n"+m.calcolaConnessioni());
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}

	
}
