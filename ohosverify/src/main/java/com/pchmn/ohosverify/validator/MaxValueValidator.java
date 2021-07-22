package com.pchmn.ohosverify.validator;

/**
 * Basic unit that validates max value.
 */
public class MaxValueValidator extends AbstractValidator {

    private int mMaxValue;
    private static String mErrorPrefix = "This field must be less than ";

    /**
     * Constructor for MaxValueValidator.
     *
     * @param value max value allowed in the validator
     */
    public MaxValueValidator(int value) {
        mMaxValue = value;
        mErrorMessage = mErrorPrefix + mMaxValue;
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = mErrorPrefix + mMaxValue;
            return d <= mMaxValue;
        } catch (NumberFormatException nfe) {
            mErrorMessage = mErrorPrefix + mMaxValue;
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
