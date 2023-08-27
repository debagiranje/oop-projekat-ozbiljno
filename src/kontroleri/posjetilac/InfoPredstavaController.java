package kontroleri.posjetilac;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import kontroleri.PomocniKontroler;
import modeli.IzvodjenjePredstave;
import modeli.OsobljePredstave;
import modeli.Zanr;

public class InfoPredstavaController implements Initializable{

	@FXML
	private Button btnKarte;
	@FXML
	private Button btnPozorista;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnIzloguj;
	@FXML
	private Label lblDatum;
	@FXML
	private Label lblVrijeme;
	@FXML
	private Label lblCijena;
	@FXML
	private Label lblCijena1;
	@FXML
	private Label lblZanr;
	@FXML
	private Label lblAutor;
	@FXML
	private Label lblReziser;
	@FXML
	private Button btnPredstavaNazad;
	@FXML
	private ListView<String> lvGlumci;
	
	private IzvodjenjePredstave ip;

	// Event Listener on Button[#btnKarte].onAction
	@FXML
	public void prebaciKarte(ActionEvent event) throws IOException {
		PomocniKontroler help = new PomocniKontroler();
		help.kartePogledScena(event);
	}
	// Event Listener on Button[#btnPozorista].onAction
	@FXML
	public void prebaciPozorista(ActionEvent event) throws IOException {
		PomocniKontroler help = new PomocniKontroler();
		help.predstavePogledScena(event);
	}
	// Event Listener on Button[#btnOsoblje].onAction
	@FXML
	public void prebaciOsoblje(ActionEvent event) throws IOException {
		
		PomocniKontroler help = new PomocniKontroler();
		help.prebaciOsoblje(event);
	}
	// Event Listener on Button[#btnIzloguj].onAction
	@FXML
	public void izloguj(ActionEvent event) throws IOException {
		PomocniKontroler help = new PomocniKontroler();
		help.izlogujScena(event);
	}
	// Event Listener on Label[#lblAutor].onMouseClicked
	@FXML
	public void oOsoblju(MouseEvent event) {
		// TODO Autogenerated
	}
	
	public void ucitaj() {
		
		lblDatum.setText(ip.getDatum().toString());
		lblVrijeme.setText(ip.getVrijeme().toString());
		
		lblCijena.setText(Double.toString(ip.getCijena()));
		
		lblZanr.setText(Zanr.getString(ip.getPredstava().getZanr().getBroj()));
		
		
		System.out.println(ip.getPredstava().getId() + "--id predstave");
		
		lblAutor.setText(OsobljePredstave.vratiAutora(ip.getPredstava().getId()));
		lblReziser.setText(OsobljePredstave.vratiRezisera(ip.getPredstava().getId()));
		
		lvGlumci.getItems().addAll(OsobljePredstave.vratiGlumce(ip.getPredstava().getId()));
	}
	
	public void initData(IzvodjenjePredstave ip) {
		this.ip = ip;
		
		System.out.println(ip.toRepertoarString() + "ip iz info ucitaj");
		
		ucitaj();
			
	}

	// Event Listener on Button[#btnPredstavaNazad].onAction
	@FXML
	public void nazadPredstave(ActionEvent event) throws IOException {
		PomocniKontroler help = new PomocniKontroler();
		help.predstavePogledScena(event);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
