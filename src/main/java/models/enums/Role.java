package models.enums;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    HOST("host");

    private final String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
