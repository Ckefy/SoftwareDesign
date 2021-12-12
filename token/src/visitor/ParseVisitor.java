package visitor;

import token.*;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class ParseVisitor implements TokenVisitor {
    private final ArrayList<Token> result = new ArrayList<>();
    private final ArrayDeque<Token> stack = new ArrayDeque<>();

    public ArrayList<Token> toPostfix(ArrayList<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }

        while (!stack.isEmpty()) {
            result.add(stack.pollLast());
        }

        return result;
    }

    @Override
    public void visit(NumberToken token) {
        result.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.getType().equals(TypeToken.LEFT)) {
            stack.add(token);
        } else {
            Token curToken = stack.pollLast();

            if (curToken != null) {
                while (!(curToken == null || curToken.getType().equals(TypeToken.LEFT))) {
                    result.add(curToken);
                    curToken = stack.pollLast();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public void visit(Operation token) {
        if (!stack.isEmpty()) {
            Token curToken = stack.peekLast();

            while (!stack.isEmpty() && token.priority() <= curToken.priority()) {
                result.add(stack.pollLast());
                curToken = stack.peekLast();
            }
        }
        stack.add(token);
    }
}
