import java.util.ArrayList;

public class RozdzielaczDanychWejsciowych implements IRozdzielaczDanychWejsciowych{
    @Override
    public ArrayList<Object> RodzielDaneWejsciowe(String dane){
        ArrayList<Object> daneZweryfikowane = new ArrayList<>();
        String[] rodzieloneDane = dane.split(",",4);
        int ilosc;
        float cena;
        try{
            ilosc = Integer.parseInt(rodzieloneDane[2]);
        }
        catch (NumberFormatException e){
            System.out.println("Ilość musi być liczną");;
            return daneZweryfikowane;
        }
        try{
            cena = Float.parseFloat(rodzieloneDane[3]);
        }
        catch (NumberFormatException e){
            System.out.println("Cena musi być liczną");
            return daneZweryfikowane;
        }

        daneZweryfikowane.add(rodzieloneDane[0]);
        daneZweryfikowane.add(rodzieloneDane[1]);
        daneZweryfikowane.add(ilosc);
        daneZweryfikowane.add(cena);

        return daneZweryfikowane;
    }
}
