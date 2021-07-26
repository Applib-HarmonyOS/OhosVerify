package com.pchmn.ohosverify;

import com.pchmn.ohosverify.validator.AbstractValidator;
import com.pchmn.ohosverify.validator.EmailValidator;
import com.pchmn.ohosverify.validator.IPValidator;
import com.pchmn.ohosverify.validator.IdenticalValidator;
import com.pchmn.ohosverify.validator.MaxLengthValidator;
import com.pchmn.ohosverify.validator.MaxValueValidator;
import com.pchmn.ohosverify.validator.MinLengthValidator;
import com.pchmn.ohosverify.validator.MinValueValidator;
import com.pchmn.ohosverify.validator.NumericValidator;
import com.pchmn.ohosverify.validator.PhoneNumberValidator;
import com.pchmn.ohosverify.validator.RangeLengthValidator;
import com.pchmn.ohosverify.validator.RangeValueValidator;
import com.pchmn.ohosverify.validator.RegexValidator;
import com.pchmn.ohosverify.validator.RequiredValidator;
import com.pchmn.ohosverify.validator.UrlValidator;
import com.pchmn.ohosverify.validator.ValidateValidator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.ComponentParent;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.TextField;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 *  InputValidators are used to validate text fields.
 */
public class InputValidator extends DependentLayout implements Component.EstimateSizeListener,
        ComponentContainer.ArrangeListener {

    // const
    public static final int IS_EMAIL = 0;
    public static final int IS_PHONE_NUMBER = 1;
    public static final int IS_NUMERIC = 2;
    public static final int IS_URL = 3;
    public static final int IS_IP = 4;
    private static final int NONE = -1;
    // validator
    private AbstractValidator mValidator = new ValidateValidator();
    private RequiredValidator mRequiredValidator;
    // errors
    private boolean mShowError = true;
    // edit text
    private TextField mEditText;
    private TextField mOtherEditText;
    // attributes
    private boolean mRequired = false;
    private int mValidatorNumber = NONE;
    private int mMinLength = NONE;
    private int mMaxLength = NONE;
    private int mMinValue = NONE;
    private int mMaxValue = NONE;
    private String mRegex;
    private int mOtherEditTextId = NONE;
    private String mErrorMessage;
    private String mRequiredMessage;
    // build
    private boolean mBuilt = false;
    private String defaultHint;
    private boolean firstHint = true;
    static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    /**
     * Construct for form.
     *
     * @param context current context
     */
    public InputValidator(Context context) {
        super(context);
        mContext = context;
        try {
            init(null);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    /**
     * Construct for form.
     *
     * @param context current context
     *
     * @param attrs attribute set to initialize form with
     */
    public InputValidator(Context context, AttrSet attrs) {
        super(context, attrs);
        mContext = context;
        try {
            init(attrs);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            HiLog.error(LABEL, e.toString());
        }
    }

    private void initValidator(AttrSet attrs) {

        Optional<Attr> attrValidatorNumber = attrs.getAttr("validator");
        if (attrValidatorNumber.isPresent()) {
            String mValidatorType = attrValidatorNumber.get().getStringValue();
            if (mValidatorType.equals("isEmail")) {
                mValidatorNumber = IS_EMAIL;
            } else if (mValidatorType.equals("isPhoneNumber")) {
                mValidatorNumber = IS_PHONE_NUMBER;
            } else if (mValidatorType.equals("isUrl")) {
                mValidatorNumber = IS_URL;
            } else if (mValidatorType.equals("isIP")) {
                mValidatorNumber = IS_IP;
            } else if (mValidatorType.equals("isNumeric")) {
                mValidatorNumber = IS_NUMERIC;
            } else {
                mValidatorNumber = NONE;
            }
        } else {
            mValidatorNumber = NONE;
        }
    }

    private void initValueAndLength(AttrSet attrs) {

        Optional<Attr> attrMinLength = attrs.getAttr("minLength");
        if (attrMinLength.isPresent()) {
            mMinLength = attrMinLength.get().getIntegerValue();
        } else {
            mMinLength = NONE;
        }
        Optional<Attr> attrMaxLength = attrs.getAttr("maxLength");
        if (attrMaxLength.isPresent()) {
            mMaxLength = attrMaxLength.get().getIntegerValue();
        } else {
            mMaxLength = NONE;
        }

        Optional<Attr> attrMinValue = attrs.getAttr("minValue");
        if (attrMinValue.isPresent()) {
            mMinValue = attrMinValue.get().getIntegerValue();
        } else {
            mMinValue = NONE;
        }
        Optional<Attr> attrMaxValue = attrs.getAttr("maxValue");
        if (attrMaxValue.isPresent()) {
            mMaxValue = attrMaxValue.get().getIntegerValue();
        } else {
            mMaxValue = NONE;
        }
    }

    private void initMessages(AttrSet attrs) {

        Optional<Attr> attrErrorMessage = attrs.getAttr("errorMessage");
        if (attrErrorMessage.isPresent()) {
            mErrorMessage = attrErrorMessage.get().getStringValue();
        } else {
            mErrorMessage = null;
        }
        Optional<Attr> attrRequiredMessage = attrs.getAttr("requiredMessage");
        if (attrRequiredMessage.isPresent()) {
            mRequiredMessage = attrRequiredMessage.get().getStringValue();
        } else {
            mRequiredMessage = null;
        }

    }

    private void init(AttrSet attrs) {

        // attributes
        if (attrs != null) {
            Optional<Attr> attrRequired = attrs.getAttr("required");
            if (attrRequired.isPresent()) {
                mRequired = attrRequired.get().getBoolValue();
            } else {
                mRequired = false;
            }

            initValidator(attrs);

            initValueAndLength(attrs);

            Optional<Attr> attrRegex = attrs.getAttr("regex");
            if (attrRegex.isPresent()) {
                mRegex = attrRegex.get().getStringValue();
            } else {
                mRegex = null;
            }
            Optional<Attr> attrOtherEditTextId = attrs.getAttr("identicalAs");
            if (attrOtherEditTextId.isPresent()) {
                mOtherEditTextId = attrOtherEditTextId.get().getIntegerValue();
            } else {
                mOtherEditTextId = NONE;
            }

            initMessages(attrs);

        }

        setEstimateSizeListener(this);
        setArrangeListener(this);

    }


    /**
     * Build the validator according to the attributes.
     */
    private void buildValidator() {

        // get views
        getOtherEditText();

        // required
        mRequiredValidator = new RequiredValidator(mRequired);

        // length
        if (mMaxLength != NONE && mMinLength != NONE) {
            mValidator = new RangeLengthValidator(mMinLength, mMaxLength);
        } else if (mMinLength != NONE) {
            mValidator = new MinLengthValidator(mMinLength);
        } else if (mMaxLength != NONE) {
            mValidator = new MaxLengthValidator(mMaxLength);
        }

        // value
        if (mMaxValue != NONE && mMinValue != NONE) {
            mValidator = new RangeValueValidator(mMinValue, mMaxValue);
        } else if (mMinValue != NONE) {
            mValidator = new MinValueValidator(mMinValue);
        } else if (mMaxValue != NONE) {
            mValidator = new MaxValueValidator(mMaxValue);
        }

        // validator
        setValidator(mValidatorNumber);

        // regex
        if (mRegex != null) {
            mValidator = new RegexValidator(mRegex);
        }

        // custom messages
        if (mErrorMessage != null) {
            mValidator.setErrorMessage(mErrorMessage);
        }
        if (mRequiredMessage != null) {
            mRequiredValidator.setErrorMessage(mRequiredMessage);
        }

        if (firstHint) {
            defaultHint = mEditText.getHint();
            firstHint = false;
        }

        // mBuilt
        mBuilt = true;
    }



    /**
     * Get the other EditText to compare with.
     * Only if the attributes identicalAs is present.
     */
    private void getOtherEditText() {
        // get other edit text
        if (mOtherEditText != null) {
            mValidator = new IdenticalValidator(mOtherEditText);
        } else if (mOtherEditTextId != NONE) {
            ComponentParent mComponentParent = mEditText.getComponentParent().getComponentParent();
            if (mComponentParent instanceof ComponentContainer) {
                ComponentContainer mComponentContainer = (ComponentContainer) mComponentParent;
                mOtherEditText = (TextField) mComponentContainer.findComponentById(mOtherEditTextId);
            }
            mValidator = new IdenticalValidator(mOtherEditText);
        }
    }

    /**
     * Set the validator according to the validator type.
     *
     * @param validator the validator type
     */
    private void setValidator(int validator) {
        switch (validator) {
            case IS_EMAIL:
                mValidator = new EmailValidator();
                break;
            case IS_PHONE_NUMBER:
                mValidator = new PhoneNumberValidator();
                break;
            case IS_IP:
                mValidator = new IPValidator();
                break;
            case IS_URL:
                mValidator = new UrlValidator();
                break;
            case IS_NUMERIC:
                if (mMinValue == NONE && mMaxValue == NONE) {
                    mValidator = new NumericValidator();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Check if the input is valid.
     *
     * @return true of it is valid, false either
     */
    public boolean isValid() {
        // build validator
        if (!mBuilt) {
            buildValidator();
        }
        String value = mEditText.getText();
        // if already invalid and no value set
        if (mEditText.getHintColor().equals(Color.RED) && (value == null || value.length() == 0)) {
            return false;
        }

        // test requirement
        if (!mRequiredValidator.isValid(value)) {
            if (mShowError) {
                mEditText.setHint(mRequiredValidator.getErrorMessage());
                mEditText.setHintColor(Color.RED);
                mEditText.setText("");
            }
            return false;
        }

        // test validity
        if (value != null && !value.isEmpty() && !mValidator.isValid(value)) {
            if (mShowError) {
                mEditText.setHint(mValidator.getErrorMessage());
                mEditText.setHintColor(Color.RED);
                mEditText.setText("");
                if (mValidator instanceof IdenticalValidator) {
                    mOtherEditText.setHint(mValidator.getErrorMessage());
                    mOtherEditText.setHintColor(Color.RED);
                    mOtherEditText.setText("");
                }
            }
            return false;
        }
        // reset error
        mEditText.setHintColor(Color.GRAY);
        mEditText.setHint(defaultHint);
        return true;
    }

    public void setCustomValidator(AbstractValidator validator) {
        mValidator = validator;
    }

    public void setEditText(TextField editText) {
        mEditText = editText;
    }

    public void setShowError(boolean show) {
        mShowError = show;
    }

    public void setRequired(boolean mRequired) {
        this.mRequired = mRequired;
    }

    public void setValidatorType(int mValidatorNumber) {
        this.mValidatorNumber = mValidatorNumber;
    }

    public void setMinLength(int mMinLength) {
        this.mMinLength = mMinLength;
    }

    public void setMaxLength(int mMaxLength) {
        this.mMaxLength = mMaxLength;
    }

    public void setMinValue(int mMinValue) {
        this.mMinValue = mMinValue;
    }

    public void setMaxValue(int mMaxValue) {
        this.mMaxValue = mMaxValue;
    }

    public void setRegex(String mRegex) {
        this.mRegex = mRegex;
    }

    public void setIdenticalAs(int mOtherEditTextId) {
        this.mOtherEditTextId = mOtherEditTextId;
    }

    public void setOtherEditText(TextField mOtherEditText) {
        this.mOtherEditText = mOtherEditText;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public void setRequiredMessage(String mRequiredMessage) {
        this.mRequiredMessage = mRequiredMessage;
    }

    @Override
    public boolean onEstimateSize(int widthMeasureSpec, int heightMeasureSpec) {
        int width = EstimateSpec.getSize(widthMeasureSpec);
        int height = EstimateSpec.getSize(heightMeasureSpec);
        setEstimatedSize(width, height);
        return false;
    }

    @Override
    public boolean onArrange(int left, int top, int width, int height) {
        // get edit text
        int childCount = getChildCount();
        // only one edit text per input validator
        if (childCount == 0 || childCount > 1) {
            try {
                throw new InvalidUIException("InputValidator must contain only one EditText");
            } catch (Exception e) {
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                HiLog.error(LABEL, e.toString());
            }
        }
        mEditText = (TextField) getComponentAt(0);
        buildValidator();
        return false;
    }


    /**
     * Builder class for InputValidator.
     */
    public static class Builder {
        private Context context;
        private boolean required = false;
        private AbstractValidator validator = new ValidateValidator();
        private int validatorType = NONE;
        private int minLength = NONE;
        private int maxLength = NONE;
        private int minValue = NONE;
        private int maxValue = NONE;
        private String regex;
        private int otherEditTextId = NONE;
        private TextField otherEditText;
        private String errorMessage;
        private String requiredMessage;
        private boolean showError = true;
        private TextField editText;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Whether the field is required or not.
         *
         * @param required true if required, false either
         * @return the Builder
         */
        public InputValidator.Builder required(boolean required) {
            this.required = required;
            return this;
        }

        /**
         * Set a custom validator.
         *
         * @param validator the validator
         * @return the Builder
         */
        public InputValidator.Builder customValidator(AbstractValidator validator) {
            this.validator = validator;
            return this;
        }

        /**
         * Set the validator type.
         * IS_EMAIL, IS_PHONE_NUMBER, IS_NUMERIC, IS_URL or IS_IP
         *
         * @param validatorType the validator type
         * @return the Builder
         */
        public InputValidator.Builder validatorType(int validatorType) {
            this.validatorType = validatorType;
            return this;
        }

        /**
         * Set the min length of the field.
         *
         * @param minLength the min length
         * @return the Builder
         */
        public InputValidator.Builder minLength(int minLength) {
            this.minLength = minLength;
            return this;
        }

        /**
         * Set the max length of the field.
         *
         * @param maxLength the max length
         * @return the Builder
         */
        public InputValidator.Builder maxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        /**
         * Set the min value of the field.
         * The field must be numeric.
         *
         * @param minValue the min value
         * @return the Builder
         */
        public InputValidator.Builder minValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        /**
         * Set the max value of the field.
         * The field must be numeric.
         *
         * @param maxValue the max value
         * @return the Builder
         */
        public InputValidator.Builder maxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        /**
         * Set a regex to validate the field.
         *
         * @param regex the regex
         * @return the Builder
         */
        public InputValidator.Builder regex(String regex) {
            this.regex = regex;
            return this;
        }

        /**
         * Compare the field to another EditText so they have to be equals.
         *
         * @param otherEditTextId the id of the other EditText
         * @return the Builder
         */
        public InputValidator.Builder identicalAs(int otherEditTextId) {
            this.otherEditTextId = otherEditTextId;
            return this;
        }

        /**
         * Compare the field to another EditText so they have to be equals.
         *
         * @param editText the other EditText
         * @return the Builder
         */
        public InputValidator.Builder identicalAs(TextField editText) {
            this.otherEditText = editText;
            return this;
        }

        /**
         * Set a custom error message.
         *
         * @param errorMessage the error message
         * @return the Builder
         */
        public InputValidator.Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        /**
         * Set a custom required message.
         *
         * @param requiredMessage the required message
         * @return the Builder
         */
        public InputValidator.Builder requiredMessage(String requiredMessage) {
            this.requiredMessage = requiredMessage;
            return this;
        }

        /**
         * Whether to show the error messages.
         *
         * @param showError true to show, false either
         * @return the Builder
         */
        public InputValidator.Builder showError(boolean showError) {
            this.showError = showError;
            return this;
        }

        /**
         * Specify the EditText to validate.
         *
         * @param editText the edit text
         * @return the Builder
         */
        public InputValidator.Builder on(TextField editText) {
            this.editText = editText;
            return this;
        }

        /**
         * Build a new InputValidator object.
         *
         * @return a InputValidator object
         */
        public InputValidator build() {
            return newInstance(this);
        }
    }

    /**
     * Create a new instance of InputValidator according to a Builder.
     *
     * @param builder the builder
     * @return an InputValidator object
     */
    public static InputValidator newInstance(InputValidator.Builder builder) {
        InputValidator InputValidator = new InputValidator(builder.context);
        InputValidator.setEditText(builder.editText);
        InputValidator.setRequired(builder.required);
        InputValidator.setCustomValidator(builder.validator);
        InputValidator.setValidatorType(builder.validatorType);
        InputValidator.setMinLength(builder.minLength);
        InputValidator.setMaxLength(builder.maxLength);
        InputValidator.setMaxValue(builder.maxValue);
        InputValidator.setMinValue(builder.minValue);
        InputValidator.setRegex(builder.regex);
        InputValidator.setOtherEditText(builder.otherEditText);
        InputValidator.setIdenticalAs(builder.otherEditTextId);
        InputValidator.setErrorMessage(builder.errorMessage);
        InputValidator.setRequiredMessage(builder.requiredMessage);
        InputValidator.setShowError(builder.showError);
        InputValidator.buildValidator();
        return InputValidator;
    }

    class InvalidUIException extends Exception {
        public InvalidUIException(String s) {
            super(s);
        }
    }

}
