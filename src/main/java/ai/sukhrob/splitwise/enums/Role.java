package ai.sukhrob.splitwise.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    public String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
