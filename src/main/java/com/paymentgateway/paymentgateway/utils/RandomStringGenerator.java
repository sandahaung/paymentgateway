package com.paymentgateway.paymentgateway.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.springframework.stereotype.Service;

@Service("randomStringGenerator")
public class RandomStringGenerator {

    private SecureRandom random;

    public RandomStringGenerator() {
        random = new SecureRandom();
    }

    public String nextRandomString() {
        return new BigInteger(130, random).toString(32);
    }
}
