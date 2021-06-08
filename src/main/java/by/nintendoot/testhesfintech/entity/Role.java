package by.nintendoot.testhesfintech.entity;

public enum Role {
    USER("User")
    ,ADMIN("Admin");

    private String iteam;

    Role(String iteam) {
        this.iteam = iteam;
    }

    public String getIteam() {
        return iteam;
    }

    public void setIteam(String iteam) {
        this.iteam = iteam;
    }
}
