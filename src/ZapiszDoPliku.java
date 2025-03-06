
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ZapiszDoPliku implements IZapiszDoPliku{
    @Override
    public void zapiszDoPliku(List<Produkt> Dane, String plik){
        try{
            FileWriter magazyn = new FileWriter(plik);
            for(Produkt produkt:Dane){
                magazyn.write(produkt.getKod()+","+ produkt.getNazwa()+","+ produkt.getIlosc()+","+ produkt.getCena()+"\n");
            }
            magazyn.close();
            System.out.println("Poprawnie zapisano zmiany");
        }
        catch (IOException e){
            System.out.println("Nie znaleziono pliku "+plik);
            e.printStackTrace();
        }
    }
}
