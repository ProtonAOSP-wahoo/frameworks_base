package org.protonaosp.systemui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;


import org.protonaosp.systemui.dagger.DaggerGlobalRootComponentProton;
import org.protonaosp.systemui.dagger.GlobalRootComponentProton;
import org.protonaosp.systemui.dagger.SysUIComponentProton;


import com.android.systemui.SystemUIFactory;
import com.android.systemui.dagger.GlobalRootComponent;
import com.android.systemui.navigationbar.gestural.BackGestureTfClassifierProvider;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class CustomSystemUIFactory extends SystemUIFactory {
    
    // ML back gesture provider
    @Override
    public BackGestureTfClassifierProvider  createBackGestureTfClassifierProvider(AssetManager am, String modelName) {
        return new CustomBackGestureTfClassifierProvider(am, modelName);
    }

    @Override
    protected GlobalRootComponent buildGlobalRootComponent(Context context) {
        return DaggerGlobalRootComponentProton.builder()
                .context(context)
                .build();
    }

    @Override
    public void init(Context context, boolean fromTest) throws ExecutionException, InterruptedException {
        super.init(context, fromTest);
        if (shouldInitializeComponents()) {
            ((SysUIComponentProton) getSysUIComponent()).createKeyguardSmartspaceController();
        }
    }
}
