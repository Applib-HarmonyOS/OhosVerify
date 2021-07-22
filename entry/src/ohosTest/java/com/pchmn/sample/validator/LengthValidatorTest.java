package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.IPValidator;
import com.pchmn.ohosverify.validator.MaxLengthValidator;
import com.pchmn.ohosverify.validator.MinLengthValidator;
import com.pchmn.ohosverify.validator.RangeLengthValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Test;

import static org.junit.Assert.*;

public class LengthValidatorTest extends ValidatorTest {

    private MinLengthValidator mMinLengthValidator;
    private MaxLengthValidator mMaxLengthValidator;
    private RangeLengthValidator mRangeLengthValidator;

    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mMinLengthValidator = new MinLengthValidator(2);
        mMaxLengthValidator = new MaxLengthValidator(5);
        mRangeLengthValidator = new RangeLengthValidator(2, 5);
        mValidator = new IPValidator();
    }

    @Override
    public void validate() {
        // min length
        assertTrue(mMinLengthValidator.isValid("abc"));
        assertTrue(mMinLengthValidator.isValid("ab"));
        assertFalse(mMinLengthValidator.isValid(""));
        assertFalse(mMinLengthValidator.isValid("a"));
        // max length
        assertTrue(mMaxLengthValidator.isValid("abcde"));
        assertTrue(mMaxLengthValidator.isValid("abc"));
        assertTrue(mMaxLengthValidator.isValid(""));
        assertFalse(mMaxLengthValidator.isValid("abcdef"));
        // range length
        assertTrue(mRangeLengthValidator.isValid("ab"));
        assertTrue(mRangeLengthValidator.isValid("abcde"));
        assertTrue(mRangeLengthValidator.isValid("abc"));
        assertFalse(mRangeLengthValidator.isValid(""));
        assertFalse(mRangeLengthValidator.isValid("a"));
        assertFalse(mRangeLengthValidator.isValid("abcdef"));
    }

    @Test
    public void illegalArgumentMinLength() {
        // negative min length
        try {
            new MinLengthValidator(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void illegalArgumentMaxLength() {
        // negative max length
        try {
            new MaxLengthValidator(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // max length = 0
        try {
            new MaxLengthValidator(0);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    public void illegalArgumentRangeLength() {
        // negative min length
        try {
            new RangeLengthValidator(-1, 0);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // negative min length and max length
        try {
            new RangeLengthValidator(-1, -1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // negative max length
        try {
            new RangeLengthValidator(0, -1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // min length > max length
        try {
            new RangeLengthValidator(3, 1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Override
    public void message() {
        // min length
        mMinLengthValidator.setErrorMessage("Min length 2");
        assertEquals("Min length 2", mMinLengthValidator.getErrorMessage());
        // max length
        mMaxLengthValidator.setErrorMessage("Max length 5");
        assertEquals("Max length 5", mMaxLengthValidator.getErrorMessage());
        // range length
        mRangeLengthValidator.setErrorMessage("Between 2  and 5 characters");
        assertEquals("Between 2  and 5 characters", mRangeLengthValidator.getErrorMessage());
    }
}
