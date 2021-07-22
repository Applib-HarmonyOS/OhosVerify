package com.pchmn.ohosverify.validator;

import ohos.utils.CommonPattern;

/**
 * Basic unit that validates IP addresses.
 */
public class IPValidator extends AbstractValidator {

    public IPValidator() {
        mErrorMessage = "The IP address is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return CommonPattern.getIpAddress().matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
