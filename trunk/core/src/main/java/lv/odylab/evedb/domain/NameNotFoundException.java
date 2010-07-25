package lv.odylab.evedb.domain;

public class NameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 677155853150061068L;

    public NameNotFoundException() {
    }

    public NameNotFoundException(String typeName) {
        super("Name not found: " + typeName);
    }
}