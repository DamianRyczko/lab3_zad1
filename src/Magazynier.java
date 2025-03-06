import java.util.LinkedList;
import java.util.Objects;

public class Magazynier {
    private final IWysywietlListe wysywietlListe;
    private LinkedList<Produkt> produkty;

    public Magazynier(IWysywietlListe wysywietlListe, LinkedList<Produkt> produkty) {
        this.wysywietlListe = wysywietlListe;
        this.produkty = produkty;
    }

    public void dodajArtykul(Produkt dodawanyProdukt){
        boolean produktIstnieje = false;
        for(Produkt produkt: produkty){
            if(Objects.equals(dodawanyProdukt.getKod(), produkt.getKod())){
                if (Objects.equals(dodawanyProdukt.getNazwa(), produkt.getNazwa())) {
                    if (dodawanyProdukt.getCena() == produkt.getCena()) {
                        System.out.println("Dodwany produkt już istnieje w systemie zwiększono ilość produktu na stanie.");
                        produkt.setIlosc(produkt.getIlosc() + dodawanyProdukt.getIlosc());
                        produktIstnieje = true;
                        break;
                    }
                    else{
                        System.out.println("Produkt: " +produkt.getKod()+" "+produkt.getNazwa()+" już istnieje ale jego cena się różni. Jeżeli chcesz dokonać zmian w tym produkcie uzyj funkcji edytuj");
                    }
                }
                else {
                    System.out.println("Produkt z takim kodem " + produkt.getKod() + " juz istnieje w systemie pod inna nazwą, nie zmienono stanów na magazynie");
                }
            }
        }
        if(!produktIstnieje){
            produkty.add(dodawanyProdukt);
            System.out.println("Dodano nowy produkt: " + dodawanyProdukt.getNazwa());
        }
    }


    public void pobierzArtykul(Produkt modyfikowanyProdukt){
        for(Produkt produkt: produkty) {
            if(Objects.equals(produkt.getKod(), modyfikowanyProdukt.getKod())){
                produkt.setNazwa(modyfikowanyProdukt.getNazwa());
                produkt.setIlosc(modyfikowanyProdukt.getIlosc());
                produkt.setCena(modyfikowanyProdukt.getCena());
            }
        }
    }
    public void usunArtykul(String kod){
        produkty.removeIf(produkt -> Objects.equals(produkt.getKod(), kod));
    }

    public void wyswietlProdukty(){
        wysywietlListe.wyswitlListe(produkty);
    }

    public LinkedList<Produkt> getProdukty() {
        return produkty;
    }
}
