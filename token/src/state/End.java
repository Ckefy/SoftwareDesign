package state;

import token.Token;
import token.Tokenizer;

public class End implements State {
    @Override
    public State getNextState(Tokenizer tokenizer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Token newToken(Tokenizer tokenizer) {
        throw new UnsupportedOperationException();
    }
}
