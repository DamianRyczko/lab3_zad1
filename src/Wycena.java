import java.util.List;

public class Wycena implements IWycenaZamowienia{

    @Override
    public void wycenaZamowienia(List<Produkt> koszyk) {
        float wartoscKoszyka = 0;
        for(Produkt produkt : koszyk){
            wartoscKoszyka += produkt.getCena() * produkt.getIlosc();
        }
        System.out.println("Cena artykułów w koszyku: "+wartoscKoszyka+" zł");
    }
}
