package com.pchmn.ohosverify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.app.Context;

/**
 *  Form allows grouping of various InputValidators.
 */
public class Form extends DirectionalLayout {

    private ComponentContainer mViewGroupRoot;
    // show errors
    private boolean mShowErrors = true;
    // validity
    private boolean mIsValid;
    // input validators
    private List<InputValidator> mInputValidatorList = new ArrayList<>();
    // inflated
    private boolean mInflated = false;

    /**
     * Construct for form.
     *
     * @param context current context
     */
    public Form(Context context) {
        super(context);
    }

    /**
     * Construct for form.
     *
     * @param context current context
     *
     * @param attrs attribute set to initialize form with
     */
    public Form(Context context, AttrSet attrs) {
        super(context, attrs);
        Optional<Attr> temp = attrs.getAttr("showErrors");
        if (temp.isPresent()) {
            mShowErrors = temp.get().getBoolValue();
        } else {
            mShowErrors = true;
        }
    }

    /**
     * Check if the Form is valid.
     *
     * @return true if it is valid, false either
     */
    public boolean isValid() {
        validate();
        return mIsValid;
    }

    /**
     * Validate the Form by getting and checking all the fields of the Form.
     */
    private void validate() {
        // get the fields
        if (!mInflated) {
            int childCount = getChildCount();

            if (childCount == 0 && mViewGroupRoot != null) {
                getChildViews(mViewGroupRoot);
            } else {
                getChildViews(this);
            }
            mInflated = true;
        }
        // check the fields
        validateList();
    }

    /**
     * Get all fields.
     *
     * @param viewGroup the root view
     */
    private void getChildViews(ComponentContainer viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Component v = viewGroup.getComponentAt(i);

            if (v instanceof InputValidator) {
                // only if input validator
                mInputValidatorList.add((InputValidator) v);
            } else if (v instanceof ComponentContainer) {
                // iterate threw view group children
                getChildViews((ComponentContainer) v);
            }
        }
    }

    /**
     * Validate all the fields of the Form.
     */
    private void validateList() {
        mIsValid = true;
        for (InputValidator inputValidator : mInputValidatorList) {
            // show error
            inputValidator.setShowError(mShowErrors);
            // if validator not valid, the Form is not valid either
            if (!inputValidator.isValid()) {
                mIsValid = false;
            }
        }
    }

    public void setShowErrors(boolean mShowErrors) {
        this.mShowErrors = mShowErrors;
    }

    public void addInputValidator(InputValidator inputValidator) {
        mInputValidatorList.add(inputValidator);
    }

    public void setInputValidatorList(List<InputValidator> mInputValidatorList) {
        this.mInputValidatorList = mInputValidatorList;
    }

    public void setViewGroupRoot(ComponentContainer mViewGroupRoot) {
        this.mViewGroupRoot = mViewGroupRoot;
    }

    /**
     * Builder class for Form.
     */
    public static class Builder {
        private Context context;
        private ComponentContainer viewGroup;
        private boolean showErrors = true;
        private List<InputValidator> InputValidatorList = new ArrayList<>();

        public Builder(Context context, Component rootView) {
            this.context = context;
            this.viewGroup = (ComponentContainer) rootView;
        }

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Whether to show error messages.
         *
         * @param showErrors true to show, false either
         * @return the Builder
         */
        public Builder showErrors(boolean showErrors) {
            this.showErrors = showErrors;
            return this;
        }

        /**
         * Add an InputValidator to the Form.
         *
         * @param inputValidator the input validator
         * @return the Builder
         */
        public Builder addInputValidator(InputValidator inputValidator) {
            this.InputValidatorList.add(inputValidator);
            return this;
        }

        /**
         * Build a new Form object.
         *
         * @return a Form object
         */
        public Form build() {
            return Form.newInstance(this);
        }
    }

    /**
     * Create a new instance of Form according to a Builder.
     *
     * @param builder the builder
     * @return a Form object
     */
    public static Form newInstance(Builder builder) {
        Form form;
        if (builder.viewGroup != null) {
            form = new Form(builder.context);
            form.setViewGroupRoot(builder.viewGroup);
        } else {
            form = new Form(builder.context);
        }
        form.setShowErrors(builder.showErrors);
        form.setInputValidatorList(builder.InputValidatorList);
        return form;
    }

}
