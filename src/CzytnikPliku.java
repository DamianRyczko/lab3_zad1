import java.util.ArrayList;
import java.util.LinkedList;

public class CzytnikPliku{
    private final IWczytajLinkedList czytnikMagazynier;
    private final IWczytajArrayList czytnikKupujacy;

    public CzytnikPliku(IWczytajLinkedList czytnikMagazynier, IWczytajArrayList czytnikKupujacy) {
        this.czytnikMagazynier = czytnikMagazynier;
        this.czytnikKupujacy = czytnikKupujacy;
    }

    public LinkedList<Produkt> wczytajMagazynier(String plik){
        return czytnikMagazynier.wczytajLinked(plik);
    }
    public ArrayList<Produkt> wczytajKupujacy(String plik){
        return czytnikKupujacy.wczytajArray(plik);
    }
}
