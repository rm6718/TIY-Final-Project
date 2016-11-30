package com.ironyard.security;

import org.assertj.core.util.Compatibility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by reevamerchant on 11/29/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityUtilsTest {

    @Test
    public void genToken() throws Exception {

        // set to null so that there isn't an already generated token that needs to be used
        String token = null;
        try {
            token = SecurityUtils.genToken();
            System.out.println(token);
            Assert.assertTrue(SecurityUtils.isValidKey(token));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }




}