package org.jacared.housepin.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringUtil {
    public static Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUserName() {
        Authentication authentication = getCurrentUser();
        return authentication.getName();
    }
}
