import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> dirtyWords = new ArrayList<>();
        ArrayList<String> commonWords = new ArrayList<>();
        ArrayList<String> cleanWords = new ArrayList<>();

        Scanner scan = new Scanner(new File("1984.txt"));
        Scanner scanCW = new Scanner(new File("commonWords.txt"));

        // populating commonWords arraylist
        while (scanCW.hasNextLine()) commonWords.add(scanCW.nextLine());
        scanCW.close();

        // text reader
        while (scan.hasNextLine()) {
            String[] temp = scan.nextLine().split("\\s+");
            for (int i = 0; i < temp.length; i++) {
                // credit: https://stackoverflow.com/questions/24741797/java-regex-to-remove-specific-punctuation
                temp[i] = temp[i].replaceAll("[\\p{P}&&[^\u0027]]", "").toLowerCase();

                // credit: https://www.baeldung.com/java-string-number-presence
                if (temp[i].isEmpty() || temp[i].matches(".*\\d.*")) continue;

                if (temp[i].equals("i")) temp[i] = "I";
                if (temp[i].charAt(0) == '\'') temp[i] = temp[i].substring(1);
                if (temp[i].charAt(temp[i].length()-1) == '\'') temp[i] = temp[i].substring(0,temp[i].length()-1);

                dirtyWords.add(temp[i]);
            }
        }
        scan.close();

        // filtering clean words
        for (int i = 0; i < dirtyWords.size(); i++) {
            if (!isCW(dirtyWords.get(i), commonWords)) cleanWords.add(dirtyWords.get(i));
        }

        Counter count = new Counter(cleanWords);
        count.printTop5();
    }
    public static boolean isCW(String word, ArrayList<String> commonWords) {
        for (int j = 0; j < commonWords.size(); j++) {
            if (word.equals(commonWords.get(j))) return true;
        }

        return false;
    }
}