package state;

import token.*;

public class Start implements State {
    @Override
    public State getNextState(Tokenizer tokenizer) {
        if (tokenizer.isEOF()) {
            return new End();
        }
        if (tokenizer.isDigit()) {
            return new Number();
        }
        if (tokenizer.isOpsBrace()) {
            return new Start();
        }
        return new Error("Unexpected char: " + tokenizer.getCurChar());
    }

    @Override
    public Token newToken(Tokenizer tokenizer) {
        char cur = tokenizer.getCurChar();
        tokenizer.moveIndex();
        return switch (cur) {
            case '*' -> new Operation(TypeToken.MUL);
            case '/' -> new Operation(TypeToken.DIV);
            case '+' -> new Operation(TypeToken.PLUS);
            case '-' -> new Operation(TypeToken.MINUS);
            case '(' -> new Brace(TypeToken.LEFT);
            case ')' -> new Brace(TypeToken.RIGHT);
            default -> null;
        };
    }
}
