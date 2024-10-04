import java.util.*;
public class Counter {
    private ArrayList<Integer> frequencies;
    private ArrayList<String> cleanCopy;
    private ArrayList<String> cleanWords;
    public Counter(ArrayList<String> cleanWords) {
        frequencies = new ArrayList<>();
        cleanCopy = new ArrayList<>();
        this.cleanWords = cleanWords;

        countFreq();
    }
    private void countFreq() {
        int x = 0;

        for (int i = 0; i < cleanWords.size(); i++) {
            if (!cleanCopy.contains(cleanWords.get(i))) {
                cleanCopy.add(cleanWords.get(i));
                frequencies.add(1);
            } else {
                x = frequencies.get(cleanCopy.indexOf(cleanWords.get(i))) + 1;
                frequencies.set(cleanCopy.indexOf(cleanWords.get(i)), x);
            }
        }
    }
    public void printTop5() {
        for (int i = 0; i < frequencies.size(); i++) {
            for (int j = i+1; j < frequencies.size(); j++) {
                if (frequencies.get(i) > frequencies.get(j)) {
                    int temp = frequencies.get(i);
                    frequencies.set(i, frequencies.get(j));
                    frequencies.set(j, temp);

                    String temp2 = cleanCopy.get(i);
                    cleanCopy.set(i, cleanCopy.get(j));
                    cleanCopy.set(j, temp2);
                }
            }
        }

        System.out.println("The top five most common words in the text file are: ");
        int x = 1;
        for (int i = frequencies.size()-1; i > frequencies.size()-6; i--) {
            System.out.println(x + ". '" + cleanCopy.get(i) + "' with " + frequencies.get(i) + " occurences.");
            x++;
        }
    }
    public void print() {
        for (int i = 0; i < cleanCopy.size(); i++) {
            System.out.println(cleanCopy.get(i) + ": " + frequencies.get(i));
        }
    }
    public ArrayList<String> getCleanCopy() {
        return cleanCopy;
    }
    public void setCleanCopy(ArrayList<String> cleanCopy) {
        this.cleanCopy = cleanCopy;
    }
    public ArrayList<Integer> getFrequencies() {
        return frequencies;
    }
    public void setFrequencies(ArrayList<Integer> frequencies) {
        this.frequencies = frequencies;
    }
}