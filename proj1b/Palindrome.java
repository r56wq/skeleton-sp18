
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        int l = word.length();
        for(int i = 0; i<l; i++){
            dq.addLast(word.charAt(i));
        }

        return dq;
    }

    public boolean isPalindrome(String word) {
        int l = word.length();
        Deque<Character> dq = wordToDeque(word);
        //check special case
        if((l == 0) || (l == 1)){
            return true;
        }
        return isPalindromeRecursive(dq, l);
    }

    private boolean isPalindromeRecursive(Deque<Character> dq, int length) {
        if (length == 1 || length == 0) {
            return true;
        }
        else {
            if (dq.removeFirst() != dq.removeLast()) {
                return false;
            }
            return isPalindromeRecursive(dq, length - 2);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dq = wordToDeque(word);
        int l = word.length();
        //check special case
        if (l == 0 || l == 1) {
            return true;
        }
        else {
            return isPalindromeRec(dq, cc, l);
        }
    }
    private boolean isPalindromeRec(Deque<Character> dq, CharacterComparator cc, int l){
        if (l == 0 || l == 1) {
            return true;
        }
        else {
            if (!cc.equalChars(dq.removeFirst(), dq.removeLast())) {
                 return false;
            }
            l = l - 2;
            return isPalindromeRec(dq, cc, l);
        }
    }
}
