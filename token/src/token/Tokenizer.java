package token;

import state.End;
import state.Error;
import state.Start;
import state.State;

import java.text.ParseException;
import java.util.ArrayList;

public class Tokenizer {
    private final String OPERATIONS = "*/+-()";
    private State curState;
    private int curIndex;
    private String inp;

    public char getCurChar() {
        return this.inp.charAt(this.curIndex);
    }

    public void moveIndex() {
        this.curIndex++;
    }

    public boolean isEOF() {
        return this.curIndex >= this.inp.length();
    }

    public boolean isSpace() {
        return Character.isWhitespace(this.getCurChar());
    }

    public boolean isDigit() {
        return Character.isDigit(this.getCurChar());
    }

    public boolean isOpsBrace() {
        return this.OPERATIONS.indexOf(this.getCurChar()) >= 0;
    }

    public ArrayList<Token> parse(String inp) throws ParseException {
        this.inp = inp.trim();
        this.curIndex = 0;
        ArrayList<Token> result = new ArrayList<>();

        this.curState = new Start();
        this.curState = this.curState.getNextState(this);

        while (!(this.curState instanceof Error || this.curState instanceof End)) {
            result.add(this.curState.newToken(this));

            while (!this.isEOF() && this.isSpace()) {
                this.moveIndex();
            }

            this.curState = this.curState.getNextState(this);
        }

        if (this.curState instanceof Error) {
            throw new ParseException(this.getErrorMessage((Error) curState), 0);
        }

        return result;
    }

    private String getErrorMessage(Error state) {
        return state.getMsg();
    }
}
