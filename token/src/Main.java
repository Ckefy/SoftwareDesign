import token.Token;
import token.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParseVisitor;
import visitor.PrintVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Tokenizer tokenizer = new Tokenizer();
            PrintVisitor printVisitor = new PrintVisitor();
            ParseVisitor parseVisitor = new ParseVisitor();
            CalcVisitor calcVisitor = new CalcVisitor();

            ArrayList<Token> tokens = tokenizer.parse(br.readLine());
            System.out.println("Parsed tokens: " + printVisitor.toString(tokens));

            ArrayList<Token> postfixTokens = parseVisitor.toPostfix(tokens);
            System.out.println("Reverse Polish notation: " + printVisitor.toString(postfixTokens));

            System.out.println(calcVisitor.evaluate(postfixTokens));
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
