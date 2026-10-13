
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        while (i >= 0 || j >= 0) {

            // Find the next valid character in s.
            i = getNextValidIndex(s, i);

            // Find the next valid character in t.
            j = getNextValidIndex(t, j);

            // If only one string has a valid character left,
            // the final strings cannot be equal.
            if (i < 0 || j < 0) {
                return i == j;
            }

            // Compare the characters that survive backspacing.
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }

            // Move both pointers to the left.
            i--;
            j--;
        }

        return true;
    }

    private int getNextValidIndex(String str, int index) {
        int backspace = 0;

        while (index >= 0) {
            char ch = str.charAt(index);

            if (ch == '#') {
                // This backspace will delete a preceding character.
                backspace++;
                index--;
            } else if (backspace > 0) {
                // Skip a character deleted by a backspace.
                backspace--;
                index--;
            } else {
                // This character survives.
                break;
            }
        }

        return index;
    }
}
