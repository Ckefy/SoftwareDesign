package token;

import visitor.TokenVisitor;

public record NumberToken(int value) implements Token {

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public TypeToken getType() {
        return TypeToken.NUMBER;
    }

    @Override
    public String toString() {
        return "NUMBER(" + value + ")";
    }
}
