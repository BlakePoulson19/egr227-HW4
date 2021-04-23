import java.util.*;

/**
 * An anagram is a word or phrase made by rearranging the letters of another word or phrase. For example the words “midterm”
 * and “trimmed” are anagrams. If you ignore spaces and capitalization and allow multiple words, a multi-word phrase can be
 * an anagram of some other word or phrase. For example, the phrase “Clint Eastwood” and “old west action” are anagrams.
 */

public class Anagrams {
    private List<String> list;
    private Map<String, LetterInventory> letters;

    // This constructor should initialize a new Anagrams object that will use the given list as its dictionary. You
    // should not change the list in any way. You may assume that the dictionary is a nonempty collection of nonempty
    // sequences of letters and that it contains no duplicates. You should "preprocess" the dictionary in your
    // constructor to compute all of the inventories in advance (once per word)
    public Anagrams(List<String> dictionary) {
        list = dictionary;
        letters = new HashMap<String, LetterInventory>();
        for (int i = 0; i < list.size(); i++) {
            letters.put(list.get(i), new LetterInventory(list.get(i)));
        }
    }

    // This method should use recursive backtracking to find combinations of words that have the same letters as the
    // given string. It should print all combinations of words from the dictionary that are anagrams of text and that
    // include at most max words (or an unlimited number of words if max is 0) to System.out.
    // You should throw an IllegalArgumentException if max is less than 0.
    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        } else {
            List<String> dictionaryModded = new ArrayList<String>();
            LetterInventory original = new LetterInventory(text);
            printPrepare(original, dictionaryModded);
            printHelper(max, original, dictionaryModded, new Stack<String>());
        }
    }

    private void printPrepare (LetterInventory original, List<String> dictionaryModded) {
        for (int i = 0; i < list.size(); i++) {
            LetterInventory catalog = letters.get(list.get(i));
            if (original.subtract(catalog) != null) {
                dictionaryModded.add(list.get(i));
            }
        }
    }

    private void printHelper(int max, LetterInventory inventory, List<String> dictionaryModded, Stack<String> result) {
        if (inventory.isEmpty() && (result.size() <= max || max == 0)) {
            System.out.println(result);
        } else if (result.size() <= max || max == 0) {
            for (int i = 0; i < dictionaryModded.size(); i++) {
                LetterInventory record = letters.get(dictionaryModded.get(i));
                LetterInventory inventoryModded = inventory.subtract(record);
                if (inventoryModded != null) {
                    result.push(dictionaryModded.get(i));
                    printHelper(max, inventoryModded, dictionaryModded, result);
                    result.pop();
                }
            }
        }
    }
}