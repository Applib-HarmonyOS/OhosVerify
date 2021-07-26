package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.EmailValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest extends ValidatorTest{

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new EmailValidator();
    }

    @Test
    @Override
    public void validate() {
        mValidator.setErrorMessage("Enter a valid email");
        assertEquals("Enter a valid email", mValidator.getErrorMessage());
    }

    @Test
    @Override
    public void message() {
        assertTrue(mValidator.isValid("test@gmail.com"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("test"));
        assertFalse(mValidator.isValid("test@"));
        assertFalse(mValidator.isValid("@gmail.com"));
    }
}
