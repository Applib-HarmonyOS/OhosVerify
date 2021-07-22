package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.IdenticalValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.TextField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdenticalValidatorTest extends ValidatorTest{

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        TextField editText = new TextField(mContext);
        editText.setText("test identical");
        mValidator = new IdenticalValidator(editText);
    }

    @Test
    @Override
    public void validate() {
        assertFalse(mValidator.isValid("test"));
        assertFalse(mValidator.isValid(""));
        assertTrue(mValidator.isValid("test identical"));
    }

    @Test
    @Override
    public void message() {
        mValidator.setErrorMessage("Fields mismatch");
        assertEquals("Fields mismatch", mValidator.getErrorMessage());
    }
}
