package org.protonaosp.systemui;

import android.content.Context;
import android.content.res.AssetManager;

import org.protonaosp.systemui.dagger.DaggerGlobalRootComponentProton;
import org.protonaosp.systemui.dagger.GlobalRootComponentProton;

import com.android.systemui.SystemUIFactory;
import com.android.systemui.dagger.GlobalRootComponent;
import com.android.systemui.navigationbar.gestural.BackGestureTfClassifierProvider;

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
}
