package lv.odylab.evedb.domain;

public class IdNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4777151862529021570L;

    public IdNotFoundException() {
    }

    public IdNotFoundException(Long typeID) {
        super("ID not found: " + typeID);
    }
}
