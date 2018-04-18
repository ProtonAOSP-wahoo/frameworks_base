/*
 * Copyright (C) 2016 The Android Open Source Project
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
 * limitations under the License
 */

package com.android.systemui;

import android.app.AlarmManager;
import android.content.Context;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.smartpixels.SmartPixelsReceiver;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.ambientmusic.AmbientIndicationService;

public class VendorServices extends SystemUI {

    private final AlarmManager mAlarmManager;
    private final StatusBar mStatusBar;
    private final ArrayList<Object> mServices = new ArrayList<>();

    private SmartPixelsReceiver mSmartPixelsReceiver;

    public VendorServices(Context context, AlarmManager alarmManager, StatusBar statusBar) {
        super(context);
        mAlarmManager = alarmManager;
        mStatusBar = statusBar;
        mSmartPixelsReceiver = new SmartPixelsReceiver(context);
    }

    @Override
    public void start() {
        AmbientIndicationContainer ambientIndicationContainer = mStatusBar.getNotificationShadeWindowView().findViewById(R.id.ambient_indication_container);
        ambientIndicationContainer.initializeView(mStatusBar);
        addService(new AmbientIndicationService(mContext, ambientIndicationContainer, mAlarmManager));
    }

    @Override
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < mServices.size(); i++) {
            if (mServices.get(i) instanceof Dumpable) {
                ((Dumpable) mServices.get(i)).dump(fileDescriptor, printWriter, strArr);
            }
        }
    }

    private void addService(Object obj) {
        if (obj != null) {
            mServices.add(obj);
        }
    }
}
