import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class WczytajArrayList  implements IWczytajArrayList{
    private final IRozdzielaczDanychWejsciowych rozdzielacz;

    public WczytajArrayList(IRozdzielaczDanychWejsciowych rozdzielacz) {
        this.rozdzielacz = rozdzielacz;
    }

    @Override
    public ArrayList<Produkt> wczytajArray(String plik){
        ArrayList<Produkt> stanMagazynu = new ArrayList<>();
        try{
            File magazyn = new File(plik);
            Scanner reader = new Scanner(magazyn);
            while(reader.hasNextLine()){
                ArrayList<Object> dane = rozdzielacz.RodzielDaneWejsciowe(reader.nextLine());
                if(dane.isEmpty()){
                    System.out.println("Nieprawidłwoe dane w pliku rekstowym");
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
