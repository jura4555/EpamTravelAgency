package com.fedynets.constants;

/**
 * User role entity
 *
 * @autor Yurii Fedynets
 */
public enum UserRole {

    USER(1), REGISTERED_USER(2), MANAGER(3), ADMIN(4);

    int index;

    UserRole(int i){
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public static UserRole getUserRole(int i){
        return UserRole.values()[i-1];
    }
}
