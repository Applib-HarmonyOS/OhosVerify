package com.pchmn.ohosverify.validator;

import com.pchmn.ohosverify.App;
import com.pchmn.ohosverify.ResourceTable;
import java.io.IOException;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

/**
 * Basic unit that validates if the value lies within a given range.
 */
public class RangeValueValidator extends AbstractValidator {

    private int mMinValue;
    private int mMaxValue;

    /**
     * Constructor   RangeLengthValidator.
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
        fetchErrorMessage();
    }

    @Override
    public boolean isValid(String value) {
        try {
            double d = Double.parseDouble(value);
            fetchErrorMessage();
            return d >= mMinValue && d <= mMaxValue;
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

    private void fetchErrorMessage()  {
        try {
            mErrorMessage = App.getInstance().getContext().getResourceManager()
                    .getElement(ResourceTable.String_error_range_value).getString(mMinValue, mMaxValue);
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
