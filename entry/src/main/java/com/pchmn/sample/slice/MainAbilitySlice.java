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

import com.pchmn.sample.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;

/**
 * Entry screen with buttons to various forms.
 */
public class MainAbilitySlice extends AbilitySlice {

    Button btn_java_form;
    Button btn_two_form;
    Button btn_one_form;
    Button btn_form_fraction;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        btn_java_form = (Button) findComponentById(ResourceTable.Id_btn_java_form);
        btn_two_form = (Button) findComponentById(ResourceTable.Id_btn_two_form);
        btn_one_form = (Button) findComponentById(ResourceTable.Id_btn_one_form);
        btn_form_fraction = (Button) findComponentById(ResourceTable.Id_btn_form_fraction);

        btn_java_form.setClickedListener(listener -> present(new JavaFormAbilitySlice(), new Intent()));
        btn_two_form.setClickedListener(listener -> present(new TwoFormAbilitySlice(), new Intent()));
        btn_one_form.setClickedListener(listener -> present(new OneFormAbilitySlice(), new Intent()));
        btn_form_fraction.setClickedListener(component -> {
            Intent intent2 = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withBundleName("com.pchmn.sample")
                    .withAbilityName("com.pchmn.sample.FormFractionAbility")
                    .build();
            intent2.setOperation(operation);
            startAbility(intent2);
        });

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