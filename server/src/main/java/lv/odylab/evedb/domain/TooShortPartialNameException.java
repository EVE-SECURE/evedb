package lv.odylab.evedb.domain;

public class TooShortPartialNameException extends RuntimeException {
    public TooShortPartialNameException(String partialTypeName) {
        super("Too short partialTypeName: " + partialTypeName);
    }
}