package token;

import visitor.TokenVisitor;

public record Operation(TypeToken type) implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public TypeToken getType() {
        return type;
    }

    public int eval(int first, int second) {
        if (type.equals(TypeToken.MUL)) {
            return first * second;
        }
        if (type.equals(TypeToken.DIV)) {
            if (second == 0) {
                throw new ArithmeticException("Division by zero is forbidden");
            }
            return first / second;
        }
        if (type.equals(TypeToken.PLUS)) {
            return first + second;
        }
        if (type.equals(TypeToken.MINUS)) {
            return first - second;
        }
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
