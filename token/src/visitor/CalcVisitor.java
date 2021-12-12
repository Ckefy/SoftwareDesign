package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class CalcVisitor implements TokenVisitor {
    private final ArrayDeque<Integer> stack = new ArrayDeque<>();

    public int evaluate(ArrayList<Token> tokens) {
        if (tokens.isEmpty()) {
            return 0;
        }

        for (Token token : tokens) {
            token.accept(this);
        }

        if (stack.size() != 1) {
            throw new IllegalStateException();
        }

        return stack.pollLast();
    }

    @Override
    public void visit(NumberToken token) {
        stack.add(token.value());
    }

    @Override
    public void visit(Brace token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(Operation token) {
        if (stack.size() < 2) {
            throw new IllegalStateException();
        }

        int second = stack.pollLast();
        int first = stack.pollLast();

        stack.add(token.eval(first, second));
    }
}
