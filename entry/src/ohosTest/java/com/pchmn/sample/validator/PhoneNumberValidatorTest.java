package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.PhoneNumberValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneNumberValidatorTest extends ValidatorTest {

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new PhoneNumberValidator();
    }

    @Test
    @Override
    public void validate() {
        assertTrue(mValidator.isValid("0299123456"));
        assertTrue(mValidator.isValid("+33123456789"));
        assertTrue(mValidator.isValid("600456789"));
        assertTrue(mValidator.isValid("600456"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("ab12"));
    }

    @Test
    @Override
    public void message() {
        mValidator.setErrorMessage("phone number");
        assertEquals("phone number", mValidator.getErrorMessage());
    }
}