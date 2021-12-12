package token;

import visitor.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);

    TypeToken getType();

    default int priority() {
        return switch (getType()) {
            case LEFT, RIGHT -> 0;
            case PLUS, MINUS -> 1;
            case DIV, MUL -> 2;
            default -> 3;
        };
    }
}
