package com.pchmn.ohosverify.validator;

import java.util.regex.Pattern;

/**
 * Basic unit that validates expression on custom regex.
 */
public class RegexValidator extends AbstractValidator {

    private Pattern mRegexPattern;

    public RegexValidator(String regex) {
        mRegexPattern = Pattern.compile(regex);
        mErrorMessage = "This field is not valid";
    }

    @Override
    public boolean isValid(String value) {
        return mRegexPattern.matcher(value).matches();
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
