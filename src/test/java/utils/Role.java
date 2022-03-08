package utils;

public enum Role {
    BUYER ("buyer"),
    VENDOR("vendor")
    ;

    private String value;
    Role(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
