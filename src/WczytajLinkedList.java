import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class WczytajLinkedList implements IWczytajLinkedList{
    private final IRozdzielaczDanychWejsciowych rozdzielacz;

    public WczytajLinkedList(IRozdzielaczDanychWejsciowych rozdzielacz) {
        this.rozdzielacz = rozdzielacz;
    }

    @Override
    public LinkedList<Produkt> wczytajLinked(String plik){
        LinkedList<Produkt> stanMagazynu = new LinkedList<>();
        try{
            File magazyn = new File(plik);
            Scanner reader = new Scanner(magazyn);
            while(reader.hasNextLine()){
                ArrayList<Object> dane = rozdzielacz.RodzielDaneWejsciowe(reader.nextLine());
                if(dane.isEmpty()){
                    System.out.println("Nieprawidłwoe dane w pliku tekstowym");
                    reader.close();
                    return stanMagazynu;
                }
                stanMagazynu.add(new Produkt((String) dane.get(0),(String) dane.get(1),(Integer) dane.get(2),(Float) dane.get(3)));
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Nie znaleziono pliku "+plik);
            e.printStackTrace();
            return stanMagazynu;
        }
        return stanMagazynu;
    }

}
