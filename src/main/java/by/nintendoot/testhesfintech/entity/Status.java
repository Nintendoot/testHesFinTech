package by.nintendoot.testhesfintech.entity;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String ite;

    Status(String ite) {
        this.ite = ite;
    }

    public String getIte() {
        return ite;
    }

    public void setIte(String ite) {
        this.ite = ite;
    }
}
