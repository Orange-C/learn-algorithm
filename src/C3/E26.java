package C3;

import java.util.Stack;

public class E26 {
    public static String expression = "a+b*c/(d-e+f)^g^(h+k)";

    public static void infixToPostfix(String origin) {
        Stack<Character> operand = new Stack<>();
        for(int i =0;i < origin.length();i++) {
            Character current = origin.charAt(i);

            if (current >= 'a' && current <= 'z') {
                System.out.print(current);
            } else {
                switch(current){
                    case '(': {
                        operand.push(current);
                        break;
                    }
                    case ')': {
                        while(operand.peek() != '(') {
                            System.out.print(operand.pop());
                        }
                        operand.pop();
                        break;
                    }
                    case '*': 
                    case '/': {
                        while(!operand.empty() && operand.peek() != '(' && operand.peek() != '+' && operand.peek() != '-') {
                            System.out.print(operand.pop());
                        }
                        operand.push(current);
                        break;
                    } 
                    case '+': 
                    case '-': {
                        while(!operand.empty() && operand.peek() != '(') {
                            System.out.print(operand.pop());
                        }
                        operand.push(current);
                        break;
                    }
                    case '^': {
                        while(!operand.empty() && !(operand.peek() != '(' || operand.peek() != '^')) {
                            System.out.print(operand.pop());
                        }
                        operand.push(current);
                        break;
                    }
                }
            }
        }

        while(!operand.empty()){
            System.out.print(operand.pop());
        }
    }    
    
    public static void main(String[] args) throws Exception {
        infixToPostfix(expression);
    }
}