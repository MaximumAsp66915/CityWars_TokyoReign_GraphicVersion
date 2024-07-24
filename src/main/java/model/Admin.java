package model;

public class Admin extends User {
    private static final String AdminUsername = "admin";
    private static final String AdminPassword = "1234";

    public Admin() {
        super(AdminUsername , AdminPassword);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
