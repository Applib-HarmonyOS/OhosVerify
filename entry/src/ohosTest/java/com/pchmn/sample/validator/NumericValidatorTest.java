package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.NumericValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumericValidatorTest extends ValidatorTest {

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new NumericValidator();
    }

    @Test
    @Override
    public void validate() {
        assertTrue(mValidator.isValid("1"));
        assertTrue(mValidator.isValid("-1"));
        assertTrue(mValidator.isValid("123"));
        assertTrue(mValidator.isValid("1.2"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("abc"));
        assertFalse(mValidator.isValid("12a"));
    }

    @Test
    @Override
    public void message() {
        mValidator.setErrorMessage("numeric");
        assertEquals("numeric", mValidator.getErrorMessage());
    }
}

