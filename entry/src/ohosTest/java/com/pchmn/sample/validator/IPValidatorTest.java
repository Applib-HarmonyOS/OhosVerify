package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.IPValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPValidatorTest extends ValidatorTest {

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mValidator = new IPValidator();
    }

    @Test
    @Override
    public void validate() {
        assertTrue(mValidator.isValid("91.198.174.225"));
        assertFalse(mValidator.isValid(""));
        assertFalse(mValidator.isValid("91.198.174"));
        assertFalse(mValidator.isValid(".198.174.225"));
    }

    @Test
    @Override
    public void message() {
        mValidator.setErrorMessage("IP not valid");
        assertEquals("IP not valid",mValidator.getErrorMessage());
    }
}
