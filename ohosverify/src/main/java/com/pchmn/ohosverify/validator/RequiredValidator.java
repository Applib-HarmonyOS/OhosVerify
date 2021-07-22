package com.pchmn.ohosverify.validator;

/**
 * Basic unit that validates mandatory fields.
 */
public class RequiredValidator extends AbstractValidator {

    private boolean mRequired;

    public RequiredValidator(boolean required) {
        mRequired = required;
        mErrorMessage = "This field is required";
    }

    @Override
    public boolean isValid(String value) {
        if (mRequired) {
            if (value == null || value.length() == 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }

    @Override
    public void setErrorMessage(String message) {
        mRequired = true;
        mErrorMessage = message;
    }
}