package com.businessproj;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by
 */
public final class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(256, random).toString(32);
    }
}
