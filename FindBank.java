import java.io.*;
import java.net.*;
import java.util.*;

public class FindBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj trzy pierwsze cyfry numeru konta: ");
        String accountPrefix = scanner.nextLine().trim();

        String fileUrl = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

        try {

            URL url = new URL(fileUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 3 && parts[0].substring(0, 3).equals(accountPrefix)) {
                    System.out.println("Skrócony numer banku: " + parts[1]);
                    System.out.println("Nazwa banku: " + parts[2]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Nie znaleziono banku dla podanych cyfr.");
            }

            reader.close();

        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas pobierania lub przetwarzania pliku: " + e.getMessage());
        }
    }
}
