package ma.youcode.lineperm.enums;

public enum Permission {
    Normale("---"),
    R("r--"),
    W("w--"),
    RW("rw-");

    private final String value;

    Permission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Permission fromValue(String value) {

        for (Permission permission : Permission.values()) {

            if (value.contains(permission.getValue())) {
                return permission;
            }
        }

        return null;
    }
}
