
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class Sheluder {
    LinkedList<Proces> czasowaLista1;
    LinkedList<Proces> czasowaLista2;
    LinkedList<Proces> czasowaLista3;
    LinkedList<Proces> czasowaLista4;
    Dysk readerCSCAN;
    Dysk readerFCFS;
    Dysk readerSSTF;
    Dysk readerSCAN;
    CSCAN kolejkaCSCAN;
    FCFS kolejkaFCFS;
    SCAN kolejkaSCAN;
    SSTF kolejkaSSTF;
    public Sheluder(int rozmiarDysku){
        czasowaLista1 = new LinkedList<Proces>();
        czasowaLista2 = new LinkedList<Proces>();
        czasowaLista3 = new LinkedList<Proces>();
        czasowaLista4 = new LinkedList<Proces>();
        readerCSCAN = new Dysk(rozmiarDysku);
        readerFCFS = new Dysk(rozmiarDysku);
        readerSSTF = new Dysk(rozmiarDysku);
        readerSCAN = new Dysk(rozmiarDysku);
        kolejkaCSCAN = new CSCAN(readerCSCAN);
        kolejkaFCFS = new FCFS();
        kolejkaSSTF = new SSTF();
        kolejkaSCAN = new SCAN(readerSCAN);
    }
    public void setCzasowka(LinkedList<Proces> prezent){
        czasowaLista1 = new LinkedList(prezent);
        czasowaLista2 = new LinkedList(prezent);
        czasowaLista3 = new LinkedList(prezent);
        czasowaLista4 = new LinkedList(prezent);
    
    }
    public void dodanieDoList(){
       while(!czasowaLista1.isEmpty() &&czasowaLista1.getFirst().getTime() <= kolejkaCSCAN.getTotal()){
                kolejkaCSCAN.add(czasowaLista1.remove());
            }
        while(!czasowaLista2.isEmpty() &&czasowaLista2.getFirst().getTime() <= kolejkaFCFS.getTotal()){
                kolejkaFCFS.add(czasowaLista2.remove());
            }
        while(!czasowaLista3.isEmpty() &&czasowaLista3.getFirst().getTime() <= kolejkaSSTF.getTotal()){
                kolejkaSSTF.add(czasowaLista3.remove());
            }
        while(!czasowaLista4.isEmpty() &&czasowaLista4.getFirst().getTime() <= kolejkaSCAN.getTotal()){
                kolejkaSCAN.add(czasowaLista4.remove());
            }
    }
    public boolean sendToDisk(){     //
        dodanieDoList();
        int licznik = 0;                // zmienna pomocnicza przy sprawdzaniu czy proces się wykonał
        if(!kolejkaCSCAN.isEmpty()){
            licznik = readerCSCAN.przetworz(kolejkaCSCAN.get(readerCSCAN.poz()));
            
            kolejkaCSCAN.increaseTotal(licznik);
            
        }
        if(!kolejkaFCFS.isEmpty()){
            licznik = readerFCFS.przetworz(kolejkaFCFS.get());
            
            kolejkaFCFS.increaseTotal(licznik);
            
        }
        if(!kolejkaSSTF.isEmpty()){
            licznik = readerSSTF.przetworz(kolejkaSSTF.get(readerCSCAN.poz()));
            kolejkaSSTF.increaseTotal(licznik);
            
            
        }
        if(!kolejkaSCAN.isEmpty()){
            licznik = readerSCAN.przetworz(kolejkaSCAN.get(readerCSCAN.poz()));
            kolejkaSCAN.increaseTotal(licznik);
            
        }
        boolean anything = (!kolejkaCSCAN.kolejka.isEmpty() || !kolejkaFCFS.kolejka.isEmpty()|| !kolejkaSCAN.kolejka.isEmpty()|| !kolejkaSSTF.kolejka.isEmpty()); // zmienna pomocnicza pozwalająca sprawdzić
        return anything;                                                                                         //czy zostały jeszcze jakiekolwiek procesy na którejkolwiek liście
    }
    public void printTimes(int liczbaProcesow){
        System.out.print(kolejkaCSCAN.getTotal());
        System.out.print("\t"  +   kolejkaFCFS.getTotal());
        System.out.print("\t"  +   kolejkaSCAN.getTotal());
        System.out.print("\t"  +   kolejkaSSTF.getTotal());
    }
    

}
