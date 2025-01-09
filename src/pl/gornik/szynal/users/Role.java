package pl.gornik.szynal.users;

public enum Role {
    MANAGER("Menadżer"),
    EMPLOYEE("Pracownik"),
    CLIENT("Klient");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
