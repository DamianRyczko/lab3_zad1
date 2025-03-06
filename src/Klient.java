import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Klient {
    private final IWysywietlListe wysywietlListe;
    private final IWycenaZamowienia iWycenaZamowienia;
    private ArrayList<Produkt> produkty;
    private LinkedList<Produkt> koszyk = new LinkedList<>();

    public Klient(IWysywietlListe wysywietlListe, IWycenaZamowienia iWycenaZamowienia, ArrayList<Produkt> produkty) {
        this.wysywietlListe = wysywietlListe;
        this.iWycenaZamowienia = iWycenaZamowienia;
        this.produkty = produkty;
    }

    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public ArrayList<Produkt> znajdzArtykul(String nazwa){
        String regex = nazwa.replaceAll("\\?", ".").replaceAll("\\*", ".*"); // "."zatępuje downy znak * oznacza ze moze wystopić dowloną liczbe razy
        ArrayList<Produkt> wyniki = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);

        for (Produkt produkt : produkty) {
            if (pattern.matcher(produkt.getNazwa()).matches()) {
                wyniki.add(produkt);
            }
        }

        return wyniki;
    }

    public void usunZKoszyka(String nazwa, int ilosc) {
        ArrayList<Produkt> pasujaceArtykuly = znajdzArtykul(nazwa);
        if (ilosc <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa niż zero.");
        }
        if (pasujaceArtykuly.isEmpty()) {
            System.out.println("Artykułu pasującego do '" + nazwa + "' nie ma w koszyku");
            return;
        }

        ArrayList<Produkt> produktyDoUsuniecia = new ArrayList<>();

        for (Produkt produkt : koszyk) {
            for (Produkt pasujacy : pasujaceArtykuly) {
                if (Objects.equals(produkt.getNazwa(), pasujacy.getNazwa())) {
                    int usunietaIlosc = 0;
                    if (produkt.getIlosc() >= ilosc) {
                        usunietaIlosc = ilosc;
                        produkt.setIlosc(produkt.getIlosc() - ilosc);
                        System.out.println("Z koszyka usunięto " + produkt.getNazwa() + " w ilośći " + usunietaIlosc);
                    } else {
                        usunietaIlosc = produkt.getIlosc();
                        System.out.println("Z koszyka usunięto " + produkt.getNazwa() + " w ilośći " + usunietaIlosc);
                        produktyDoUsuniecia.add(produkt);
                    }
                    for (Produkt produktWMagazynie : pasujaceArtykuly) {
                        if (Objects.equals(produktWMagazynie.getNazwa(), pasujacy.getNazwa())) {
                            produktWMagazynie.setIlosc(produktWMagazynie.getIlosc() + usunietaIlosc );
                        }
                    }
                    break;
                }
            }
        }
        koszyk.removeAll(produktyDoUsuniecia);
        wyswietlKoszyk();
    }
    public void dodajDoJKoszyka(String nazwa, int ilosc){
        if (ilosc <= 0) {
                throw new IllegalArgumentException("Ilość musi być większa niż zero.");
            }
            ArrayList<Produkt> pasujaceArtykuly = znajdzArtykul(nazwa);
            if (pasujaceArtykuly.isEmpty()) {
                System.out.println("Artykułu pasującego do '" + nazwa + "' nie ma w maagzynie");
                return;
        }

        for(Produkt produkt: pasujaceArtykuly){
            if(produkt.getIlosc() > 0){
                koszyk.add(new Produkt(produkt.getKod(),produkt.getNazwa(),produkt.getIlosc(),produkt.getCena())); // add new product not a reference
                if(produkt.getIlosc() >= ilosc) {
                    koszyk.getLast().setIlosc(ilosc);
                    produkt.setIlosc(produkt.getIlosc()-koszyk.getLast().getIlosc());
                    System.out.println("Dodano produkt "+produkt.getNazwa()+" w ilości "+ilosc);
                    continue;
                }
                else{
                    produkt.setIlosc(0);
                    System.out.println("Zbyt mała ilośc pduktów na stanie, do koszyka dodano maksymalną dostepną ilość");
                }

            }

        }
        wyswietlKoszyk();
    }
    public void wycenaZamowienia(){
        iWycenaZamowienia.wycenaZamowienia(koszyk);
    }
    public void wyswietlKoszyk(){
        wysywietlListe.wyswitlListe(koszyk);
    }
    public void wyswietlProdukty(){
        wysywietlListe.wyswitlListe(produkty);
    }
    public void wyswietlWyniki(ArrayList<Produkt> wyniki){
        wysywietlListe.wyswitlListe(wyniki);
    }
    
}
