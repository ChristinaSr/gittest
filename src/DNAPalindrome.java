import java.util.Scanner;

public class DNAPalindrome {
    //Creating a method that returns true if the dna sequence is valid
    private static boolean isVal(String s){
        boolean val = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //if even one of the character of the sequence is not 'A', 'T', 'C', 'G' return false
            if (!(c == 'A' || c == 'T' || c == 'C' || c == 'G')) {
                val = false;
                break;
            }
        }
        return val;
    }
    //Creating a method that reverse a string
    private static String reverse(String s) {
        int j = s.length();
        //Creating a character array
        char[] rev_s = new char[j];
        for (int i = 0; i < s.length(); i++) {
            //Putting every character of the given string in the array backwards
            rev_s[--j] = s.charAt(i);
        }
        return new String(rev_s);
    }

    public static void main(String[] args) {
        //Using Scanner to take user input
        System.out.println("Type a DNA sequence");
        Scanner sc = new Scanner(System.in);
        String dna = sc.nextLine();

        boolean val = isVal(dna);
        //if the expression is not valid ask for another input
        while (!val) {
            System.out.println("The expresion is not valid. Type an expression with 'A', 'T', 'G', 'C': ");
            dna = sc.nextLine();
            val = isVal(dna);
        }

        //if the dna sequence has one (valid) or zero characters then it's Watson-Crick
        if (dna.length() <= 1 && isVal(dna)) {
            System.out.println("The dna sequence \' "+ dna + "\' is Watson-Crick complemented palindrome");
        }
        else {
            String rev_dna = reverse(dna);  //Reversing the dna sequence
            StringDoubleEndedQueueImpl<String> complWC_dna = new StringDoubleEndedQueueImpl<>();
            //Putting in complWC_dna the corresponding character recording to the one of the reversed dna
            for(int i = 0; i < dna.length(); i++){
                if(rev_dna.charAt(i) == 'T') {
                    complWC_dna.addLast('A'+"");
                }
                else if(rev_dna.charAt(i) == 'A') {
                    complWC_dna.addLast('T'+"");
                }
                else if(rev_dna.charAt(i) == 'C') {
                    complWC_dna.addLast('G'+"");
                }
                else {
                    complWC_dna.addLast('C'+"");
                }
            }

            //Converting complWC_dna into a string
            String s = "";
            for (int i = 0; i < dna.length(); i++) {
                s = s + complWC_dna.removeFirst();
            }
            //Checking if the complemented and reversed dna sequence equals to the given dna
            if (s.equals(dna)) {
                System.out.println("The given dna sequence \'" + dna + "\' is Watson-Crick complemented palindrome");
            }
            else {
                System.out.println("The given dna sequence \'" + dna + "\' is not Watson-Crick complemented palindrome");
            }
        }
    }
}
