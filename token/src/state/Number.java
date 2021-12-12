package state;

import token.NumberToken;
import token.Token;
import token.Tokenizer;

public class Number implements State {
    @Override
    public State getNextState(Tokenizer tokenizer) {
        if (tokenizer.isEOF()) {
            return new End();
        }
        if (tokenizer.isOpsBrace()) {
            return new Start();
        }
        return new Error("Unexpected char: " + tokenizer.getCurChar());
    }

    @Override
    public Token newToken(Tokenizer tokenizer) {
        StringBuilder value = new StringBuilder();
        while (!tokenizer.isEOF() && tokenizer.isDigit()) {
            value.append(tokenizer.getCurChar());
            tokenizer.moveIndex();
        }

        return new NumberToken(Integer.parseInt(value.toString()));
    }
}
