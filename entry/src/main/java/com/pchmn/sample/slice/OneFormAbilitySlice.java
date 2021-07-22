/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pchmn.sample.slice;

import com.pchmn.ohosverify.Form;
import com.pchmn.ohosverify.InputValidator;
import com.pchmn.ohosverify.validator.AbstractValidator;
import com.pchmn.sample.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.window.dialog.ToastDialog;

/**
 * Form validation via form builder method and JSON InputValidators.
 */
public class OneFormAbilitySlice extends AbilitySlice {

    Button mValidate;
    InputValidator mInputValidator;
    Form mForm;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_one_form);

        mValidate = (Button) findComponentById(ResourceTable.Id_validate_one_form);
        mInputValidator = (InputValidator) findComponentById(ResourceTable.Id_custom_validator);
        mInputValidator.setCustomValidator(new AbstractValidator() {
            @Override
            public boolean isValid(String value) {
                return value.equals("ok man");
            }

            @Override
            public String getErrorMessage() {
                return "This field must be equals to 'ok man'";
            }
        });

        DirectionalLayout mViewForm = (DirectionalLayout) findComponentById(ResourceTable.Id_form);

        mForm = new Form.Builder(this, mViewForm)
                .showErrors(true)
                .build();

        mValidate.setClickedListener(component ->
                showToast(mForm.isValid() ? "This form is valid" : "This form is not valid"));
    }

    private void showToast(String msg) {
        new ToastDialog(this).setDuration(1000).setText(msg).show();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
