package executor.service.publisher.enums;

public enum TokenClaim {
    ROLES("roles");
    private final String claim;

    TokenClaim(String claim) {
        this.claim = claim;
    }
    public String getClaim() {
        return claim;
    }
}