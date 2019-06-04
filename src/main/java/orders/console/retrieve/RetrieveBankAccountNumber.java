package orders.console.retrieve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static orders.console.retrieve.Input.getString;


public class RetrieveBankAccountNumber implements Retrieve {

    private String accountNumber;

    public String getData(){
        boolean exit;
        do{
            System.out.println("│ format nru konta bankowego 12 1234 1234 1234 1234 1234 1234");
            System.out.print("│");
            exit = isCorrect(getString());
            if (!exit)
                System.out.println("│ wprowadzony numer jest niepoprawny, spróbuj ponownie.");
        }while(!exit);

        return accountNumber;
    }

    private boolean isCorrect(String numer) {
        accountNumber = numer;

        Pattern pattern = Pattern.compile("\\d{2} \\d{4} \\d{4} \\d{4} \\d{4} \\d{4} \\d{4}");
        Matcher matcher = pattern.matcher(numer);

        return matcher.matches();
    }

}
