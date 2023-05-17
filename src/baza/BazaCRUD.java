package baza;


import java.util.ArrayList;

public interface BazaCRUD<T> {
	
	T vratiPoId(int id);
    
    ArrayList<T> vratiSve();
    
    void dodaj(T obj);
    
    void azuriraj(T obj);
    
    void obrisiPoId(int id);
    
    void obrisiSve();

}
