import java.util.LinkedList;

/**
 * Zadaniem metod tej klasy jest wygenerowanie uporządkowanej według czasu 
 * wejścia listy Procesów.
 * @author Piotrek
 */

public class ProcesListGenerator {
    
    /**
     * Tworzy listę o zadanej długości zawierającą procesy ze wskazaniem na 
     * losowy sektor i o losowym czasie wejścia
     * @param arrayLength
     * @return LinkedList
     */    
    public LinkedList<Proces> randGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int loc;
        for(int i = 0;i<10;i++)                                     //Dodanie 10 procesów "na start"
            list.add(new Proces(0, (int)( Math.random()*last)+1));
        for(int i=0; i < arrayLength; i++){
            loc  = (int) (Math.random()*last) + 1 ;
            list.add(new Proces( (int) (Math.random() * safeClock), loc));
            if(list.size() > 1){
                safeClock += Math.abs(list.getLast().getLoc() - list.get(list.size()-2).getLoc());
            }  else safeClock = list.getLast().getLoc();
        }
        System.out.print("\nrandGenerate ");
        return sort(list);
    }
    
    /**
     * Ustawia wszystkie czasy wejścia na 0, oraz losowe sektory pamięci.
     * @param arrayLength
     * @param last
     * @return
     */
    public LinkedList<Proces> immediateGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        for(int i = 0;i<arrayLength + 10;i++)                                     //Dodanie 10 procesów "na start"
            list.add(new Proces(0, (int)( Math.random()*last)+1));
        System.out.print("\nimmediateGenerate ");
        return sort(list);
    }
    
   /**
     * Tworzy listę o zadanej długości zawierającą procesy ze wskazaniem na 
     * sektor znajdujący się ZA obecnym i losowym czasie wejścia
     * @param arrayLength
     * @return LinkedList
     */
    public LinkedList<Proces> inOrderGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int loc = 0;
        for(int i = 0;i<10;i++)                                     //Dodanie 10 procesów "na start"
            list.add(new Proces(0, (int)( Math.random()*last)+1));
        for(int num =0; num < arrayLength;num++){
            loc += last/arrayLength;
            list.add(new Proces((int)(Math.random() * safeClock), loc));
            if(list.size() > 1){
                safeClock += list.getLast().getLoc() - list.get(list.size()-2).getLoc();
            }  else safeClock = list.getLast().getLoc();
        }
        System.out.print("\ninOrderGenerate ");
        return sort(list);
    }
    
    /**
     * Tworzy listę o zadanej długości zawierającą procesy ze wskazaniem na 
     * sektor znajdujący się PRZED obecnym (od końca) i losowym czasie wejścia
     * @param arrayLength
     * @return LinkedList
     */    
    public LinkedList<Proces> revOrderGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int loc = arrayLength;
        for(int i = 0;i<10;i++)                                     //Dodanie 10 procesów "na start"
            list.add(new Proces(0, (int)( Math.random()*last)+1));
        for(int i=0; i < arrayLength; i++){
            loc -= last/arrayLength;
            list.add(new Proces((int)(Math.random() * safeClock), loc ));
            if(list.size() > 1){
                safeClock += list.getLast().getLoc() - list.get(list.size()-2).getLoc();
            }  else safeClock = list.getLast().getLoc();
        }
        System.out.print("\nrevOrderGenerate ");
        return sort(list);
    }
    
    /**
     * Otrzymuje listę procesów, porządkuje ją według czasów wejścia procesu,
     * zwraca posortowaną listę
     * @param list
     * @return LinkedList
     */
    private LinkedList<Proces> sort(LinkedList<Proces> list){
        
        LinkedList<Proces> tempList = new LinkedList();
        tempList.add(list.remove(0));
        int i;
        for(Proces proc : list){
            for(i = 0; i < tempList.size() && !(proc.getTime() < tempList.get(i).getTime()); i++);
            tempList.add(i, proc);  
        }
        
        System.out.print("dla " + tempList.size() + " procesów");
        return tempList;
    }   
}
