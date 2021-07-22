package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.UrlValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlValidatorTest extends ValidatorTest {

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new UrlValidator();
    }

    @Test
    @Override
    public void validate() {
        assertTrue(mValidator.isValid("www.google.com"));
        assertTrue(mValidator.isValid("google.com"));
        assertTrue(mValidator.isValid("http://www.wp.pl/"));
        assertTrue(mValidator.isValid("http://www.google.com/page/1"));
        assertTrue(mValidator.isValid("https://google.com"));
        assertFalse(mValidator.isValid("htp://google.com"));
        assertFalse(mValidator.isValid("http://google"));
    }

    @Test
    @Override
    public void message() {
        mValidator.setErrorMessage("url");
        assertEquals("url", mValidator.getErrorMessage());
    }
}