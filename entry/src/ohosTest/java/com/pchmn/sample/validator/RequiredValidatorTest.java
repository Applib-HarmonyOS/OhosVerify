package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.RequiredValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequiredValidatorTest extends ValidatorTest {

    private RequiredValidator mRequiredValidator;
    private RequiredValidator mNotRequiredValidator;

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mRequiredValidator = new RequiredValidator(true);
        mNotRequiredValidator = new RequiredValidator(false);
    }

    @Test
    @Override
    public void validate() {
        // required
        assertTrue(mRequiredValidator.isValid("abcd"));
        assertTrue(mRequiredValidator.isValid("123"));
        assertFalse(mRequiredValidator.isValid(""));
        // not required
        assertTrue(mNotRequiredValidator.isValid(""));
        assertTrue(mNotRequiredValidator.isValid("abcd"));
        assertTrue(mNotRequiredValidator.isValid("123"));
    }

    @Test
    @Override
    public void message() {
        mRequiredValidator.setErrorMessage("required");
        assertEquals("required", mRequiredValidator.getErrorMessage());
    }
}