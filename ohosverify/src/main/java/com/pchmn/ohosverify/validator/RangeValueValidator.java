package com.pchmn.ohosverify.validator;

/**
 * Basic unit that validates if the value lies within a given range.
 */
public class RangeValueValidator extends AbstractValidator {

    private int mMinValue;
    private int mMaxValue;
    private static String mErrorPrefix = "This field must be between ";
    private static String mErrorAnd = " and ";

    /**
     * Constructor for RangeLengthValidator.
     *
     * @param minValue min value allowed in the validator
     *
     * @param maxValue max value allowed in the validator
     */
    public RangeValueValidator(int minValue, int maxValue) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("The max value has to be superior or equal to the min value");
        }

        mMinValue = minValue;
        mMaxValue = maxValue;
        mErrorMessage = mErrorPrefix + mMinValue + mErrorAnd + mMaxValue;
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            mErrorMessage = mErrorPrefix + mMinValue + mErrorAnd + mMaxValue;
            return d >= mMinValue && d <= mMaxValue;
        } catch (NumberFormatException nfe) {
            mErrorMessage = mErrorPrefix + mMinValue + mErrorAnd + mMaxValue;
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
