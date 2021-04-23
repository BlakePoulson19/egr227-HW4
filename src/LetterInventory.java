/**
 * The LetterInventory Class should:
 * - Store the inventory (how many a’s, how many b’s, etc.) as an array with 26 counters (one for each letter) along with
 * any other data fields you find that you need. (Should NOT be an ArrayList)
 * - Ignore the case of the letters (e.g., “a” and “A”)
 * - Ignore non-alphabetic characters
 * - Introduce a class constant for the value 26 to make the class more readable
 * - Should NOT have any extra public methods or have any extra behavior
 */
public class LetterInventory {
    private static final int defaultSize = 26;
    private int size;
    private int[] counts;

    // Constructs an empty inventory (all counts are 0)
    public LetterInventory() {
        counts = new int[defaultSize];
        for (int i = 0; i < defaultSize; i++) {
            counts[i] = 0;
            size++;
        }
    }

    // Constructs an inventory (a count) for the alphabetic letters in data (the given string). Uppercase and lowercase
    // letters should be treated as the same. All non-alphabetic characters should be ignored
    public LetterInventory(String data) {
        data = data.toLowerCase();
        counts = new int[defaultSize];
        for (int i = 0; i < data.length(); i++) {
            if (Character.isLetter(data.charAt(i))) {
                counts[data.charAt(i) - 'a']++;
                size++;
            }
        }
    }

    // Returns the number of times the given letters appears in this inventory. Letter can be lowercase or uppercase
    // (your method shouldn't care). If a non-alphabetic character is passed, your method should throw an IllegalArgumentException
    public int get(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException();
        } else {
            return counts[Character.toLowerCase(letter) - 'a'];
        }
    }

    // Sets the count for the given letter to the given value. Letter might be lowercase or uppercase. If a non-alphabetic
    // character is passed or if value is negative, your method should throw an IllegalArgumentException
    public void set (char letter, int value) {
        letter = Character.toLowerCase(letter);
        if (value < 0 || !Character.isLetter(letter)) {
            throw new IllegalArgumentException();
        } else {
            size -= counts[letter - 'a'];
            counts[letter - 'a'] = value;
            size += value;
        }
    }

    // Returns the sum of all of the counts in this inventory. This operation should be "fast" in the sense that it should
    // store the size rather than computing it each time the method is called.
    public int size() {
        return size;
    }

    // Returns true if this inventory is empty (all counts are 0). This operation should be "fast" in the sense that
    // it shouldn't loop over the array each time the method is called.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Returns a String representation of the inventory with all the letters in lowercase, in sorted order, and surrounded
    // by square brackets. The number of occurrences of each letter should match its count in the inventory.
    public String toString() {
        String string = "[";
        for (int i = 0; i < defaultSize; i++) {
            for (int j = 0; j < counts[i]; j++) {
                string += (char) (i + 'a');
            }
        }
        string += "]";
        return string;
    }

    // Constructs and returns a new LetterInventory object that represents the sum of this LetterInventory and the other
    // given LetterInventory. The counts for each letter should be added together. The two LetterInventory objects being
    // added together (this and other) should not be changed by this method. You might be tempted to implement the add
    // method by calling the toString method, but you may not use that approach, because it would be inefficient for
    // inventories with large character counts.
    public LetterInventory add (LetterInventory other) {
        LetterInventory sum = new LetterInventory("");
        for (int i = 0; i < defaultSize; i++) {
            sum.counts[i] = this.counts[i] + other.counts[i];
        }
        sum.size = this.size + other.size;
        return sum;
    }

    // Constructs and returns a new LetterInventory object that represents the difference of this letter inventory and
    // the other given LetterInventory. The counts from the other inventory should be subtracted from the counts of this
    // one. The two LetterInventory objects being subtracted (the and other) should not be changed by this method. If any
    // resulting count would be negative, your method should return null.
    public LetterInventory subtract (LetterInventory other) {
        LetterInventory difference = new LetterInventory("");
        int differenceSize = 0;
        for (int i = 0; i < defaultSize; i++) {
            difference.counts[i] = this.counts[i] - other.counts[i];
            differenceSize = differenceSize + difference.counts[i];
            if (difference.counts[i] <= -1 ) {
                return null;
            }
        }
        difference.size = differenceSize;
        return difference;
    }

    // Returns a double from 0.0 to 1.0 representing the percentage of letters in the inventory that are the given letter.
    // If there are no letters in the inventory, this method should always return 0. If a non-alphabetic character is
    // passed, your method should throw an IllegalArgumentException.
    public double getLetterPercentage(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException();
        } else {
            return (double)(get(letter))/(double)(size);
        }
    }
}