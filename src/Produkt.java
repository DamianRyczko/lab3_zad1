import java.util.List;

public class Produkt {
    private String kod;
    private String nazwa;
    private int ilosc;
    private float cena;

    private static final List<String> zabronioneZnaki = List.of("\n", ",", "\t", ".");

    public Produkt(String kod, String nazwa, int ilosc, float cena) {
        setKod(kod);
        setNazwa(nazwa);
        setIlosc(ilosc);
        setCena(cena);
    }

    public void setKod(String kod) {

        if(!kod.isEmpty()){
            this.kod = kod;
        }
        else{
            throw new IllegalArgumentException("Kod nie moze być pusty");
        }

    }

    public void setNazwa(String nazwa) {
        nazwa = nazwa.trim(); // usuniecie niepotrzebnych spacji
        if(!nazwa.isEmpty()) {
            for(int i=0; i<zabronioneZnaki.size(); i++){
                if(nazwa.contains(zabronioneZnaki.get(i))){
                    throw new IllegalArgumentException(
                            "Nazwa produktu nie może zawierać : " + zabronioneZnaki);
                }
            }
            this.nazwa = nazwa;
        }
        else{
            throw new IllegalArgumentException("Nazwa nie może być pusta");
        }
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public void setCena(float cena) {
        if(cena > 0 ){
            this.cena = (float) Math.round(cena * 100) /100;
        }
        else{
            throw new IllegalArgumentException("Cena nie może być ujemna");
        }

    }
    public void wyswietlProdukt(){
        System.out.printf("| %-4s | %-30s | %-10d | %-10.2f zł |%n", kod, nazwa, ilosc, cena);
    }

    public String getKod() {
        return kod;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getIlosc() {
        return ilosc;
    }

    public float getCena() {
        return cena;
    }
}
