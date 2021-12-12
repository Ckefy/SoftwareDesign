package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.ArrayList;

public class PrintVisitor implements TokenVisitor {
    private StringBuilder result = new StringBuilder();

    private void toString(Token token) {
        result.append(token).append(" ");
    }

    public String toString(ArrayList<Token> tokens) {
        for (Token token: tokens) {
            token.accept(this);
        }

        String resultString = result.toString();
        result = new StringBuilder();

        return resultString;
    }

    @Override
    public void visit(NumberToken token) {
        this.toString(token);
    }

    @Override
    public void visit(Brace token) {
        this.toString(token);
    }

    @Override
    public void visit(Operation token) {
        this.toString(token);
    }
}
