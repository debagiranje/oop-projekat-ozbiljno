package baza;

import java.util.Optional;
import java.util.ArrayList;

public interface BazaCRUD<T> {
	
	Optional<T> vratiPoId(long id);
    
    ArrayList<T> vratiSve();
    
    //void sacuvaj(T model);
    
    void azuriraj(T model);
    
    void obrisiPoId(int id);

}
