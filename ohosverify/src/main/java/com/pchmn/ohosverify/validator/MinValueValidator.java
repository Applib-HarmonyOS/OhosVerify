package com.pchmn.ohosverify.validator;

/**
 * Basic unit that validates min value.
 */
public class MinValueValidator extends AbstractValidator {

    private int mMinValue;
    private static String mErrorPrefix = "This field must be greater than ";

    /**
     * Constructor for MinValueValidator.
     *
     * @param value min value allowed in the validator
     */
    public MinValueValidator(int value) {
        mMinValue = value;
        mErrorMessage = mErrorPrefix + mMinValue;
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = mErrorPrefix + mMinValue;
            return d >= mMinValue;
        } catch (NumberFormatException nfe) {
            mErrorMessage = mErrorPrefix + mMinValue;
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
