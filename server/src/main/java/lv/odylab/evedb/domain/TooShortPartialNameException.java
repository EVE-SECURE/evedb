package lv.odylab.evedb.domain;

public class TooShortPartialNameException extends RuntimeException {
    private static final long serialVersionUID = -9143162286453458668L;

    public TooShortPartialNameException() {
    }

    public TooShortPartialNameException(String partialTypeName) {
        super("Too short partialTypeName: " + partialTypeName);
    }
}