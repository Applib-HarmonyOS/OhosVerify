package com.pchmn.ohosverify.validator;

/**
 * Basic dummy unit.
 */
public class ValidateValidator extends AbstractValidator {

    public ValidateValidator() {
        // Dummy constructor hence empty
    }

    @Override
    public boolean isValid(String value) {
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
