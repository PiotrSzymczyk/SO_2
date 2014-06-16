import java.util.LinkedList;

/**
 * Zadaniem metod tej klasy jest wygenerowanie uporzÄ…dkowanej wedĹ‚ug czasu 
 * wejĹ›cia listy ProcesĂłw.
 * @author Piotrek
 */

public class ProcesListGenerator {
    
    /**
     * Tworzy listÄ™ o zadanej dĹ‚ugoĹ›ci zawierajÄ…cÄ… procesy ze wskazaniem na 
     * losowy sektor i o losowym czasie wejĹ›cia
     * @param arrayLength
     * @return LinkedList
     */    
    public LinkedList<Proces> randGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int loc  = (int) (Math.random()*last + 1);
        list.add(new Proces( (int) (Math.random() * safeClock), loc));
        for(int i=0; i < arrayLength-1; i++){
            list.add(new Proces( (int) (Math.random() * safeClock), loc));
            loc  = (int) (Math.random()*last + 1);
            if(list.size() > 1){
                safeClock += Math.abs(list.getLast().getLoc() - list.get(list.size()-2).getLoc());
            }  else safeClock = list.getLast().getLoc();
        }
        System.out.print("\nrandGenerate ");
        return sort(list);
    }
    
    /**
     * Ustawia wszystkie czasy wejĹ›cia na 0, oraz losowe sektory pamiÄ™ci.
     * @param arrayLength
     * @param last
     * @return
     */
    public LinkedList<Proces> immediateGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        for(int i = 0;i<arrayLength ;i++)                                     //Dodanie 10 procesĂłw "na start"
            list.add(new Proces(0, (int)( Math.random()*last)+1));
        System.out.print("\nimmediateGenerate ");
        return sort(list);
    }
    
   /**
     * Tworzy listÄ™ o zadanej dĹ‚ugoĹ›ci zawierajÄ…cÄ… procesy ze wskazaniem na 
     * sektor znajdujÄ…cy siÄ™ ZA obecnym i losowym czasie wejĹ›cia
     * @param arrayLength
     * @return LinkedList
     */
    public LinkedList<Proces> inOrderGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int loc = 0;
        list.add(new Proces((int)(Math.random() * safeClock), loc));
        for(int num =0; num < arrayLength - 1;num++){
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
     * Tworzy listÄ™ o zadanej dĹ‚ugoĹ›ci zawierajÄ…cÄ… procesy ze wskazaniem na 
     * sektor znajdujÄ…cy siÄ™ PRZED obecnym (od koĹ„ca) i losowym czasie wejĹ›cia
     * @param arrayLength
     * @return LinkedList
     */    
    public LinkedList<Proces> revOrderGenerate(int arrayLength, int last){
        LinkedList<Proces> list = new LinkedList();
        int safeClock = 0;
        int loc = arrayLength;
        list.add(new Proces(0, (int)( Math.random()*last)+1));
        for(int i=0; i < arrayLength-1; i++){
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
     * Otrzymuje listÄ™ procesĂłw, porzÄ…dkuje jÄ… wedĹ‚ug czasĂłw wejĹ›cia procesu,
     * zwraca posortowanÄ… listÄ™
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
        
        System.out.print("dla " + tempList.size() + " procesĂłw");
        return tempList;
    }   
}
