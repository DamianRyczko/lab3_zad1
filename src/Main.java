import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String plik = "src\\magazyn.txt";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            plik = plik.replace("\\", "/");
        }

        IZapiszDoPliku zapisywacz = new ZapiszDoPliku();
        RozdzielaczDanychWejsciowych rozdzielacz = new RozdzielaczDanychWejsciowych();
        CzytnikPliku czytnik = new CzytnikPliku(new WczytajLinkedList(rozdzielacz), new WczytajArrayList(rozdzielacz));
        Scanner wejscie = new Scanner(System.in);

        if(args.length == 0){
            System.out.println("Nie wybrano opcji. Dostępne są [magazynier/klient]");
        }
        else{
            if (args[0].toLowerCase().equals("magazynier")) {
                LinkedList<Produkt> magazyn = czytnik.wczytajMagazynier(plik);
                Magazynier magazynier = new Magazynier(new Wyswietlacz(), magazyn);
                while(true){
                    System.out.println("Wybierz opcje:");
                    System.out.println("\t1 -"+"dodaj artykuł");
                    System.out.println("\t2 -"+"modyfikuj artykuł");
                    System.out.println("\t3 -"+"usuń artykuł");
                    System.out.println("\t4 -"+"wyswietl magazyn");
                    System.out.println("\t5 -"+"zapisz zmiany");
                    System.out.println("\t6 -"+"zakończ");
                    String polecenie = wejscie.nextLine().toLowerCase();
                    if(polecenie.equals("1")){
                        System.out.println("Dodaj artykuł [kod,nazwa,ilość,cena] - przecinki muszą być uwzględnione");
                        ArrayList<Object> dane = rozdzielacz.RodzielDaneWejsciowe(wejscie.nextLine());
                        magazynier.dodajArtykul(new Produkt((String) dane.get(0),(String) dane.get(1),(Integer) dane.get(2),(Float) dane.get(3)));
                    }
                    else if(polecenie.equals("2")){
                        System.out.println("Pobierz artykuł [kod,nazwa,ilość,cena] - przecinki muszą być uwzględnione");
                        ArrayList<Object> dane = rozdzielacz.RodzielDaneWejsciowe(wejscie.nextLine());
                        magazynier.pobierzArtykul(new Produkt((String) dane.get(0),(String) dane.get(1),(Integer) dane.get(2),(Float) dane.get(3)));
                    }
                    else if (polecenie.equals("3")) {
                        System.out.println("Usuń artykuł [kod]");
                        magazynier.usunArtykul(wejscie.nextLine());
                    }
                    else if (polecenie.equals("4")) {
                        magazynier.wyswietlProdukty();
                    }
                    else if (polecenie.equals("5")) {
                        zapisywacz.zapiszDoPliku(magazynier.getProdukty(), plik);
                    }
                    else if (polecenie.equals("6")) {
                        zapisywacz.zapiszDoPliku(magazynier.getProdukty(), plik);
                        System.out.println("zamknięcie programu");
                        break;
                    }
                    else{
                        throw new IllegalArgumentException("Nieprawidłowa opcja");
                    }
                }
            }
            else if (args[0].toLowerCase().equals("klient")) {
                ArrayList<Produkt> magazyn= czytnik.wczytajKupujacy(plik);
                Klient klient = new Klient(new Wyswietlacz(),new Wycena() ,magazyn);
                while(true){
                    System.out.println("Wybierz opcje");
                    System.out.println("\t1 -"+"dodaj artykuł");
                    System.out.println("\t2 -"+"usuń artykuł");
                    System.out.println("\t3 -"+"wyświetl koszyk");
                    System.out.println("\t4 -"+"znajdź artykuł");
                    System.out.println("\t5 -"+"wyceń zamówienie");
                    System.out.println("\t6 -"+"złóż zmaówienie");
                    System.out.println("\t7 -"+"zakończ");
                    String polecenie = wejscie.nextLine().toLowerCase();
                    if(polecenie.equals("1")){
                        System.out.println("Dodaj artykuł do koszyka [nazwa,ilość] - przecinki muszą być uwzględnione");
                        String[] rodzieloneDane = wejscie.nextLine().split(",",2);
                        int ilosc = 0;
                        try{
                            ilosc = Integer.parseInt(rodzieloneDane[1]);
                        }
                        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                            System.out.println("Ilość musi być liczną");;
                        }
                        klient.dodajDoJKoszyka(rodzieloneDane[0],ilosc);
                    }
                    else if(polecenie.equals("2")){
                        System.out.println("Usuń artykuł z koszyka [nazwa,ilość] - przecinki muszą być uwzględnione");
                        String[] rodzieloneDane = wejscie.nextLine().split(",",2);
                        int ilosc = 0;
                        try{
                            ilosc = Integer.parseInt(rodzieloneDane[1]);
                        }
                        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                            System.out.println("Ilość musi być liczną");;
                        }
                        klient.usunZKoszyka(rodzieloneDane[0],ilosc);
                    }
                    else if (polecenie.equals("3")) {
                        klient.wyswietlKoszyk();
                    }
                    else if (polecenie.equals("4")) {
                        System.out.println("Znajdź artykuł [nazwa]");
                        ArrayList<Produkt> wyniki = klient.znajdzArtykul(wejscie.nextLine());
                        klient.wyswietlWyniki(wyniki);
                    }
                    else if (polecenie.equals("5")) {
                        klient.wycenaZamowienia();
                    }
                    else if (polecenie.equals("6")) {
                        zapisywacz.zapiszDoPliku(klient.getProdukty(), plik);
                    }
                    else if (polecenie.equals("7")) {
                        System.out.println("zamknięcie programu");
                        break;
                    }
                    else{
                        throw new IllegalArgumentException("Nieprawidłowa opcja");
                    }
                }
            }
            else {
                throw new IllegalArgumentException("Nieodpoweidni tryb. Dostępne są [magazynier/klient]");
            }
        }

    }
}