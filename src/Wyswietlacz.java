import java.util.List;

public class Wyswietlacz implements IWysywietlListe{
    final private String naglowek = String.format("| %-4s | %-30s | %-10s | %-13s |%n", "Kod", "Nazwa", "Ilosc", "Cena");
    @Override
    public void wyswitlListe (List<Produkt> prdukty){
        System.out.printf(naglowek);
        System.out.println("_".repeat(naglowek.length()-2));
        for(Produkt produkt:prdukty){
            produkt.wyswietlProdukt();
        }
    }
}
