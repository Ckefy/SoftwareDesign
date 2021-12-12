package state;

import token.Token;
import token.Tokenizer;

public class Error implements State {
    private final String msg;

    public Error(String msg) {
        this.msg = msg;
    }

    @Override
    public State getNextState(Tokenizer tokenizer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Token newToken(Tokenizer tokenizer) {
        throw new UnsupportedOperationException();
    }

    public String getMsg() {
        return msg;
    }
}
