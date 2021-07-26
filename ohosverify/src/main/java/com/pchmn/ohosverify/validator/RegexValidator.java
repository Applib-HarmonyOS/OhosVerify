package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import java.util.regex.Pattern;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

/**
 * Basic unit that validates expression on custom regex.
 */
public class RegexValidator extends AbstractValidator {

    private Pattern mRegexPattern;

    /**
     * Constructor for RegexValidator.
     *
     * @param regex Regex pattern for the validator
     */
    public RegexValidator(String regex) {
        mRegexPattern = Pattern.compile(regex);
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_invalid_field).getString();
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (WrongTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
