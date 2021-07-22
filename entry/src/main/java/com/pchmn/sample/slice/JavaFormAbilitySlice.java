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
import com.pchmn.sample.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

/**
 * Form validation via builder methods in Java.
 */
public class JavaFormAbilitySlice extends AbilitySlice {

    Button mValidate;
    TextField mEmail;
    TextField mPassword;
    TextField mConfirmPassword;
    Form mForm;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_java_form);

        mEmail = (TextField) findComponentById(ResourceTable.Id_email);
        mPassword = (TextField) findComponentById(ResourceTable.Id_password);
        mConfirmPassword = (TextField) findComponentById(ResourceTable.Id_confirm_password);
        mValidate = (Button) findComponentById(ResourceTable.Id_validate_java);

        InputValidator emailValidator = new InputValidator.Builder(this)
                .on(mEmail)
                .validatorType(InputValidator.IS_EMAIL)
                .build();

        InputValidator passwordValidator = new InputValidator.Builder(this)
                .on(mPassword)
                .required(true)
                .minLength(6)
                .build();

        InputValidator confirmValidator = new InputValidator.Builder(this)
                .on(mConfirmPassword)
                .required(true)
                .identicalAs(mPassword)
                .build();

        mForm = new Form.Builder(this)
                .addInputValidator(emailValidator)
                .addInputValidator(passwordValidator)
                .addInputValidator(confirmValidator)
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
