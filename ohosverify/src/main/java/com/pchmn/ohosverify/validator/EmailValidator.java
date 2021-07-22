package com.pchmn.ohosverify.validator;

import ohos.utils.CommonPattern;

/**
 * Basic unit that validates email.
 */
public class EmailValidator extends AbstractValidator {

    public EmailValidator() {
        mErrorMessage = "The email is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return CommonPattern.getEmailAddress().matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return  mErrorMessage;
    }
}
