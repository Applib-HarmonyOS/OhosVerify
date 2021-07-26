package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

/**
 * Basic unit that validates max value.
 */
public class MaxValueValidator extends AbstractValidator {

    private int mMaxValue;

    /**
     * Constructor for MaxValueValidator.
     *
     * @param value max value allowed in the validator
     */
    public MaxValueValidator(int value) {
        mMaxValue = value;
        fetchErrorMessage();
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            fetchErrorMessage();
            return d <= mMaxValue;
        } catch (NumberFormatException nfe) {
            try {
                mErrorMessage = App.getInstance().getContext().getResourceManager()
                        .getElement(ResourceTable.String_error_invalid_number).getString();
            } catch (NotExistException e) {
                e.printStackTrace();
            } catch (WrongTypeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private void fetchErrorMessage() {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_max_value).getString(mMaxValue);
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (WrongTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
