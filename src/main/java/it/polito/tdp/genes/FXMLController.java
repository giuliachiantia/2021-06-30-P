package it.polito.tdp.genes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText(model.trovaPercorso(boxLocalizzazione.getValue()));
    }

    @FXML
    void doStatistiche(ActionEvent event) {
    	txtResult.clear();
    	this.model.creaGrafo();
    	txtResult.appendText("Grafo Creato\n");
    	txtResult.appendText("#Vertici: " +model.nVertici()+"\n");
    	txtResult.appendText("#Archi: "+model.nArchi() +"\n");
    	
    	String c=boxLocalizzazione.getValue();
    	if(c==null) {
    		txtResult.appendText("seleziona una localizzazione");
    		return;
    	}
    	List<Adiacenza> result=this.model.getAdiacenti(c);
    	
    	txtResult.appendText("Adiacenti a: " +c+" \n");
    	for(Adiacenza a: result) {
    		txtResult.appendText(a.toString()+"\n");
    	}
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxLocalizzazione.getItems().addAll(this.model.getLoc());
	}
}
