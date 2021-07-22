package com.pchmn.ohosverify.validator;

/**
 * Basic unit that validates max length.
 */
public class MaxLengthValidator extends AbstractValidator {

    private int mLength;

    /**
     * Constructor for MaxLengthValidator.
     *
     * @param length max length allowed in the validator
     */
    public MaxLengthValidator(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("You put a negative max length (" + length + ")");
        }
        if (length == 0) {
            throw new IllegalArgumentException("Max length cannot be equal to zero");
        }
        mLength = length;
        mErrorMessage = "This field can contain " + mLength + " characters maximum";
    }

    @Override
    public boolean isValid(String value) {
        return value.length() <= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}