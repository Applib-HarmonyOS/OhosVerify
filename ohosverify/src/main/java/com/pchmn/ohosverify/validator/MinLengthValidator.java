package com.pchmn.ohosverify.validator;

/**
 * Basic unit that validates min length.
 */
public class MinLengthValidator extends AbstractValidator {

    private int mLength;

    /**
     * Constructor for MinLengthValidator.
     *
     * @param length min length allowed in the validator
     */
    public MinLengthValidator(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("You put a negative min length (" + length + ")");
        }
        mLength = length;
        mErrorMessage = "This field can contain " + mLength + " characters maximum";
    }

    @Override
    public boolean isValid(String value) {
        return value.length() >= mLength;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
