package state;

import token.Token;
import token.Tokenizer;

public interface State {
    State getNextState(Tokenizer tokenizer);

    Token newToken(Tokenizer tokenizer);
}
