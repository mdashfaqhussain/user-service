package com.hasdedin.user.utility;

public enum UserRoleType {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private final int value;

    UserRoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
