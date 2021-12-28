package org.protonaosp.systemui;

import android.content.Context;

import org.protonaosp.systemui.dagger.DaggerGlobalRootComponentProton;
import org.protonaosp.systemui.dagger.GlobalRootComponentProton;

import com.android.systemui.SystemUIFactory;
import com.android.systemui.dagger.GlobalRootComponent;

public class CustomSystemUIFactory extends SystemUIFactory {
    @Override
    protected GlobalRootComponent buildGlobalRootComponent(Context context) {
        return DaggerGlobalRootComponentProton.builder()
                .context(context)
                .build();
    }
}
