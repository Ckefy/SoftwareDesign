package token;

import visitor.TokenVisitor;

public record Brace(TypeToken type) implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public TypeToken getType() {
        return type;
    }

    @Override
    public String toString() {
       return type.toString();
    }
}
