import java.util.Scanner;

public class PostfixToInfix {
    //Creating a method that returns true if the given expression is valid and false if it's not
    private static boolean validExpression(String e) {
        boolean val1 = true;
        boolean val2 = true;
        char c = e.charAt(e.length()-1);
        //Checking if the expression doesn't end with operator
        if (!(c=='*'||c=='/'||c=='^'||c=='+'||c=='-' )) {
            val1 = false;
        }
        //Checking if the expression is constist of numbers between 0 and 9, and valid operators
        for (int i = 0; i < e.length(); i++) {
            char c1 = e.charAt(i);
            if (!(c1 == '*' || c1 == '/' || c1 == '^' || c1 == '+' || c1 == '-' || c1 == '1' || c1 == '2' || c1 == '3' || c1 == '4' || c1 == '5' || c1 == '6'|| c1 == '7' || c1 == '8' || c1 == '9' || c1 == '0')) {
                val2 = false;
                break;
            }
        }
        return (val1 && val2);
    }

    public static void main(String[] args) {
        //Using Scanner to take user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Type a postfix expression: ");
        String exp = sc.nextLine();

        boolean val = validExpression(exp);
        //if the expression is not valid ask for another input
        while (!val) {
            System.out.println("The expresion is not valid. Type a postfix expression: ");
            exp = sc.nextLine();
            val = validExpression(exp);
        }

        StringDoubleEndedQueueImpl<String> queue = new StringDoubleEndedQueueImpl<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            //if the character is an operator
            if (c == '*' || c == '/' || c == '^' || c == '+' || c == '-') {
                String op1 = queue.removeLast();
                String op2 = queue.removeLast();
                String temp = "(" + op2 + c + op1 + ")";
                queue.addLast(temp);
            } else {
                queue.addLast(c + "");
            }
        }
        System.out.println("The infix expression is: ");
        queue.printQueue(System.out);
    }
}
