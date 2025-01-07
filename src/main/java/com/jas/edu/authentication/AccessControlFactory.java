package com.jas.edu.authentication;

import com.jas.edu.data.repository.UserDetaillsRepository;
import com.jas.edu.data.services.UserDetailsService;

public class AccessControlFactory {
    private static final AccessControlFactory INSTANCE = new AccessControlFactory();
    private final AccessControl accessControl = new BasicAccessControl();

    private AccessControlFactory() {
    }

    public static AccessControlFactory getInstance() {
        return INSTANCE;
    }

    public AccessControl createAccessControl() {
        return accessControl;
    }
}

