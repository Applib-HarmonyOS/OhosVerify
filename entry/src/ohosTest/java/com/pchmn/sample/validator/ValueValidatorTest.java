package com.pchmn.sample.validator;

import com.pchmn.ohosverify.validator.MaxValueValidator;
import com.pchmn.ohosverify.validator.MinValueValidator;
import com.pchmn.ohosverify.validator.RangeValueValidator;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueValidatorTest extends ValidatorTest {

    private MinValueValidator mMinValueValidator;
    private MaxValueValidator mMaxValueValidator;
    private RangeValueValidator mRangeValueValidator;

    @Before
    @Override
    public void setUp() {
        mContext = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        mMinValueValidator = new MinValueValidator(5);
        mMaxValueValidator = new MaxValueValidator(10);
        mRangeValueValidator = new RangeValueValidator(0, 10);
    }

    @Test
    @Override
    public void validate() {
        // min value
        assertTrue(mMinValueValidator.isValid("5"));
        assertTrue(mMinValueValidator.isValid("10"));
        assertFalse(mMinValueValidator.isValid("3"));
        assertFalse(mMinValueValidator.isValid(""));
        assertFalse(mMinValueValidator.isValid("-1"));
        assertFalse(mMinValueValidator.isValid("abc"));
        assertFalse(mMinValueValidator.isValid("12a"));
        // max value
        assertTrue(mMaxValueValidator.isValid("10"));
        assertTrue(mMaxValueValidator.isValid("8"));
        assertTrue(mMaxValueValidator.isValid("-1"));
        assertFalse(mMaxValueValidator.isValid(""));
        assertFalse(mMaxValueValidator.isValid("11"));
        assertFalse(mMaxValueValidator.isValid("abc"));
        assertFalse(mMaxValueValidator.isValid("12a"));
        // range value
        assertTrue(mRangeValueValidator.isValid("0"));
        assertTrue(mRangeValueValidator.isValid("10"));
        assertTrue(mRangeValueValidator.isValid("5"));
        assertFalse(mRangeValueValidator.isValid(""));
        assertFalse(mRangeValueValidator.isValid("-1"));
        assertFalse(mRangeValueValidator.isValid("11"));
        assertFalse(mRangeValueValidator.isValid("abc"));
        assertFalse(mRangeValueValidator.isValid("12a"));
    }

    @Test
    public void illegalArgumentRange() {
        // min value > max value
        try {
            new RangeValueValidator(3, 1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }

        // min value > max value
        try {
            new RangeValueValidator(-2, -10);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
    }

    @Test
    @Override
    public void message() {
        // min value
        mMinValueValidator.setErrorMessage("min");
        assertEquals("min", mMinValueValidator.getErrorMessage());
        // invalid number
        assertFalse(mMinValueValidator.isValid("abc"));
        // max value
        mMaxValueValidator.setErrorMessage("max");
        assertEquals("max", mMaxValueValidator.getErrorMessage());
        // invalid number
        assertFalse(mMaxValueValidator.isValid("abc"));
        // range value
        mRangeValueValidator.setErrorMessage("range");
        assertEquals("range", mRangeValueValidator.getErrorMessage());
        // invalid number
        assertFalse(mRangeValueValidator.isValid("abc"));
    }
}
