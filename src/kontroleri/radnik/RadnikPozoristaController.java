package kontroleri.radnik;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import kontroleri.PomocniKontroler;
import modeli.IzvodjenjePredstave;
import modeli.Osoblje;
import modeli.OsobljePredstave;
import modeli.Pozoriste;
import modeli.Predstava;
import modeli.RadnikPozorista;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.MenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.MenuButton;

import javafx.scene.control.DatePicker;

public class RadnikPozoristaController implements Initializable{
	@FXML
	private Button btnPredstave;
	@FXML
	private Button btnIzvodjenja;
	@FXML
	private Button btnOsoblje;
	@FXML
	private Button btnIzloguj;
	@FXML
	private Label nazivPozorista;
	@FXML
	private Button btnRezervacije;
	@FXML
	private Button btnKreiraj;
	@FXML
	private Button btnOmeni;
	@FXML
	private ListView<String> lvPredstave;
	@FXML
	private Button btnDodajPredstavu;
	@FXML
	private TextField tbNaziv;
	@FXML
	private ComboBox<String> cbAutor;
	@FXML
	private ComboBox<String> cbReziser;
	@FXML
	private MenuButton mbGlumci;
	@FXML
	private MenuItem glumacMenuItem;
	@FXML
	private ComboBox<String> cbZanr;
	@FXML
	private TextField tbCijena;
	@FXML
	private DatePicker dpDatum;
	@FXML
	private TextField tbVrijeme;
	@FXML
	private Button btnDodajTermin;
	@FXML
	private Button btnDodajOsoblje;

	private static ArrayList<String> selectedItems = new ArrayList<>();
	
	
	// Event Listener on Button[#btnOmeni].onAction
		@FXML
		public void oMeni(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.oMeniScena(event);
		}
		// Event Listener on Button[#btnKreiraj].onAction
		@FXML
		public void KreirajScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.kreirajScena(event);
		}
		// Event Listener on Button[#btnOsoblje].onAction
		@FXML
		public void osobljeScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.osobljeScena(event);
		}
		// Event Listener on Button[#btnRezervacije].onAction
		@FXML
		public void rezervacijeScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.rezervacijaScena(event);
		}
		// Event Listener on Button[#btnPredstave].onAction
		@FXML
		public void predstaveScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.predstaveScena(event);
		}
		// Event Listener on Button[#btnIzvodjenja].onAction
		@FXML
		public void izvodjenjaScena(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.izvodjenjaScena(event);
		}
		@FXML
		public void izloguj(ActionEvent event) throws IOException {
			PomocniKontroler help = new PomocniKontroler();
			help.izlogujScena(event);
		}
		


	
	// Event Listener on Button[#btnDodajPredstavu].onAction
	@FXML
	public void dodajPredstavu(ActionEvent event) {
		
		if(cbZanr.getValue().isBlank()|| tbNaziv.getText().isBlank())
			System.out.println("Unesite podatke");
		else
		{
			if(Predstava.unikatanNaziv(tbNaziv.getText()))
			Predstava.dodajPredstavu(tbNaziv.getText(), cbZanr.getValue());
			else
				tbNaziv.clear();
		}
		
		lvPredstave.getItems().setAll(Predstava.vratiPredstavu());
		tbNaziv.clear();
		
	}
	// Event Listener on Button[#btnDodajTermin].onAction
	@FXML
	public void dodajTermin(ActionEvent event) {
		
		//int flag = 0;
		int cijena = -1; 
		
		try {
		    cijena = Integer.parseInt(tbCijena.getText());
		    if(cijena < 0) // predstava moze biti besplatna:)
		    {
		    	PomocniKontroler.errorAlert("Greska pri unosu", "Molim Vas, unesite pozitivan broj");
		    	tbCijena.setText("");
		    }
		} catch(Exception e) {
		    System.out.println("Non-numeric character exist");
		    PomocniKontroler.errorAlert("Greska pri unosu", "Molim Vas, unesite numericku vrijednost");
		    tbCijena.setText("");
		}
		
		System.out.println(cijena);
		if(!PomocniKontroler.isValidTime(tbVrijeme.getText()))
		{
			PomocniKontroler.errorAlert("Greska pri unosu", "Molim Vas, unesite ispravno vrijeme, formata HH:MM ili HH:MM:SS");
	    	tbVrijeme.setText("");
		}
		else
		{
			if(dpDatum.getValue().toString().isEmpty())
				PomocniKontroler.errorAlert("Greska pri unosu", "Molim Vas, unesite datum");
			else {
		
			LocalDateTime datumcic = LocalDateTime.of(LocalDate.parse(dpDatum.getValue().toString()), LocalTime.parse(tbVrijeme.getText()));
			
			System.out.println(datumcic.toString());
			System.out.println(tbVrijeme.getText());
			
			IzvodjenjePredstave.dodaj(Predstava.vratiPoStringu(lvPredstave.getSelectionModel().getSelectedItem()).getId(), RadnikPozorista.trenutni.getPozoristeID().getId(),  cijena, datumcic);
		}}
		
		
	}
	
	
	// Event Listener on Button[#btnDodajOsoblje].onAction
	/*
	 * Metoda kojom dodajemo osoblje predstave 
	 * Provjeravamo da li su izabrani autori vec pridruzeni izvodjenju
	 * Ako jesu - zamijeni postojeceg sa novoizabranim
	 * inace, dodaj novog
	 * 
	 * za glumce... hm
	 * 
	 */
	@FXML
	public void dodajOsoblje(ActionEvent event) {
		
		String autorString = cbAutor.getSelectionModel().getSelectedItem();
		String reziserString = cbReziser.getSelectionModel().getSelectedItem();
		
		Predstava trenutna = Predstava.vratiPoStringu(lvPredstave.getSelectionModel().getSelectedItem());
		
		OsobljePredstave.dodajAutora(Osoblje.vratiPoStringu(autorString, 3), trenutna.getId());
		//System.out.println(Osoblje.vratiPoStringu(autorString, 3));
		OsobljePredstave.dodajRezisera(Osoblje.vratiPoStringu(reziserString, 2), trenutna.getId());
		
		OsobljePredstave.dodajGlumce(selectedItems, trenutna.getId());
		
	}
	
	public void init()
	{
		lvPredstave.getItems().setAll(Predstava.vratiPredstavu());
		cbZanr.setItems(Pozoriste.vratiZanrove());
		nazivPozorista.setText(RadnikPozorista.trenutni.getPozoristeID().getNaziv());
		
		
		/////// autor
		cbAutor.getItems().setAll(Osoblje.vratiAutore());
        cbAutor.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item.isEmpty()) {
                            setText("Dodaj Autora...");
                        } else {
                            setText(item);
                        }
                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
                if (cell.getItem().isEmpty() && ! cell.isEmpty()) {
                    TextInputDialog imePrezime = new TextInputDialog();
                    imePrezime.setTitle("Unos Osoblja");
            		imePrezime.setHeaderText("Unesi Novog Autora!");
                    imePrezime.setContentText("Unesi ime i prezime: ");
                    imePrezime.showAndWait().ifPresent(text -> {
                        int index = cbAutor.getItems().size()-1;
                        cbAutor.getItems().add(index, text);
                        Osoblje.dodajOsoblje(text, 3);
                        cbAutor.getSelectionModel().select(index);
                    });
                    evt.consume();
                }
            });

            return cell ;
        });
        
        
		/////// reziser
		cbReziser.getItems().setAll(Osoblje.vratiRezisere());
        cbReziser.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item.isEmpty()) {
                            setText("Dodaj Rezisera...");
                        } else {
                            setText(item);
                        }
                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
                if (cell.getItem().isEmpty() && ! cell.isEmpty()) {
                    TextInputDialog imePrezime = new TextInputDialog();
                    imePrezime.setTitle("Unos Osoblja");
            		imePrezime.setHeaderText("Unesi Novog Režisera!");
                    imePrezime.setContentText("Unesi ime i prezime: ");
                    imePrezime.showAndWait().ifPresent(text -> {
                        int index = cbReziser.getItems().size()-1;
                        cbReziser.getItems().add(index, text);
                        Osoblje.dodajOsoblje(text, 2);
                        cbReziser.getSelectionModel().select(index);
                    });
                    evt.consume();
                }
            });

            return cell ;
        });
        
        
        ///// glumac
        glumci();
	
	}
	
	@FXML
	public void dodajGlumcaMI(ActionEvent event) {
		TextInputDialog imePrezime = new TextInputDialog();
		imePrezime.setTitle("Unos Osoblja");
		imePrezime.setHeaderText("Unesi Novog Glumca!");
        imePrezime.setContentText("Unesi ime i prezime: ");
        imePrezime.showAndWait().ifPresent(text -> {
            Osoblje.dodajOsoblje(text, 1);
        });
        glumci();
	}
	
	public void glumci()
	{
		mbGlumci.getItems().remove(1, mbGlumci.getItems().size());
        mbGlumci.getItems().addAll(Osoblje.vratiGlumce());
        
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (((CheckMenuItem)e.getSource()).isSelected()) {
                	System.out.println(("\t\t\t\t" + ((CheckMenuItem)e.getSource())
                            .getText() + " selected"));
                	
                	selectedItems.add(((CheckMenuItem)e.getSource()).getText());
                	
                }
                else
                {
                	
                	selectedItems.remove(((CheckMenuItem)e.getSource()).getText());
                	
                }
            }
        };
        
        int flag = 0;
        for (MenuItem item : mbGlumci.getItems()) {
        	if(flag>=1)
        		item.setOnAction(event);
        	flag++;
        	
        }
		
	}
	
	public void datumarije()
	{
		dpDatum.setConverter(
		        new StringConverter<>() {
		          final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		          @Override
		          public String toString(LocalDate date) {
		            return (date != null) ? dateFormatter.format(date) : "";
		          }

		          @Override
		          public LocalDate fromString(String string) {
		            return (string != null && !string.isEmpty())
		                ? LocalDate.parse(string, dateFormatter)
		                : null;
		          }
		          
		        });

		dpDatum.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });

		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
		datumarije();
	}
}
