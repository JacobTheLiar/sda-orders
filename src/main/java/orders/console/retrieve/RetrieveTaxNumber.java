package orders.console.retrieve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static orders.console.retrieve.Input.getString;


public class RetrieveTaxNumber implements Retrieve{

    private String taxNumber;

    public String getData(){
        boolean exit;
        do{

            System.out.println("│ format kodu NIP: 123-456-78-90");
            System.out.print("│");
            exit = isCorrect(getString());

            if (!exit)
                System.out.println("│ wprowadzony kod jest niepoprawny, spróbuj ponownie.");

        }while(!exit);
        return taxNumber;
    }

    private boolean isCorrect(String kod) {
        taxNumber = kod;

        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(kod);

        return matcher.matches();
    }
}
