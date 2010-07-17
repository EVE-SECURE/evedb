package lv.odylab.evedb.domain;

public class TooShortPartialNameException extends RuntimeException {
    private static final long serialVersionUID = 2940527482355949758L;

    public TooShortPartialNameException(String partialTypeName) {
        super("Too short partialTypeName: " + partialTypeName);
    }
}