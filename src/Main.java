/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author Mateusz
*/
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    public static void main(String[]args) throws FileNotFoundException{
        int pomocniczaIloscZczytan;
        int pomocniczaWielkDysk;
        Scanner sc = new Scanner(new FileReader("Test.txt"));
        System.out.print("\t\t\t\t\t\t\t\t\tCSCAN\tFCFS\tSCAN\tSSTF");
        while(sc.hasNextInt()){
          
                pomocniczaWielkDysk = sc.nextInt();
                Sheluder shlud = new Sheluder(pomocniczaWielkDysk);
                ProcesListGenerator plg = new ProcesListGenerator();
                LinkedList<Proces> procesy = new LinkedList<>();
                switch(sc.nextInt()){
                    case 0:
                        pomocniczaIloscZczytan = sc.nextInt();
                        procesy = plg.randGenerate(pomocniczaIloscZczytan, pomocniczaWielkDysk);
                        System.out.print(" dla dysku o wielkości: " + pomocniczaWielkDysk + " \t\t");

                        shlud.setCzasowka(procesy);
                        while(shlud.sendToDisk());

                        shlud.printTimes(pomocniczaIloscZczytan);
                        break;
                    case 1:
                        pomocniczaIloscZczytan = sc.nextInt();
                        procesy = plg.immediateGenerate(pomocniczaIloscZczytan, pomocniczaWielkDysk);
                        System.out.print(" dla dysku o wielkości: " + pomocniczaWielkDysk + " \t\t");
                        shlud.setCzasowka(procesy);
                        while(shlud.sendToDisk());

                        shlud.printTimes(pomocniczaIloscZczytan);
                        break;
                    case 2:
                        pomocniczaIloscZczytan = sc.nextInt();
                        procesy = plg.inOrderGenerate(pomocniczaIloscZczytan, pomocniczaWielkDysk);
                        System.out.print(" dla dysku o wielkości: " + pomocniczaWielkDysk + " \t\t");
                        shlud.setCzasowka(procesy);
                        while(shlud.sendToDisk());

                        shlud.printTimes(pomocniczaIloscZczytan);
                        break;
                    case 3:
                        pomocniczaIloscZczytan = sc.nextInt();
                        procesy = plg.revOrderGenerate(pomocniczaIloscZczytan, pomocniczaWielkDysk);
                        System.out.print(" dla dysku o wielkości: " + pomocniczaWielkDysk + " \t\t");
                        shlud.setCzasowka(procesy);
                        while(shlud.sendToDisk());

                        shlud.printTimes(pomocniczaIloscZczytan);
                        break;
                } 
            
        }
    }
}
