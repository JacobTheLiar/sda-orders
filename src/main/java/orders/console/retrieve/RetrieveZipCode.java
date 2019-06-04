package orders.console.retrieve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static orders.console.retrieve.Input.getString;


public class RetrieveZipCode implements Retrieve{

    private String zipCode;

    public String getData(){
        boolean exit;
        do{
            System.out.println("│ format kodu pocztowego 00-000");
            System.out.print("│");
            exit = isCorrect(getString());
            if (!exit)
                System.out.println("│ wprowadzony kod jest niepoprawny, spróbuj ponownie.");
        }while(!exit);

        return zipCode;
    }

    private boolean isCorrect(String kod) {
        zipCode = kod;

        Pattern pattern = Pattern.compile("\\d{2}-\\d{3}");
        Matcher matcher = pattern.matcher(kod);

        return matcher.matches();
    }


}
