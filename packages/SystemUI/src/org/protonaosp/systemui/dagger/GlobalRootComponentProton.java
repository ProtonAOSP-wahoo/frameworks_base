package org.protonaosp.systemui.dagger;

import android.content.Context;

import com.android.systemui.dagger.GlobalModule;
import com.android.systemui.dagger.GlobalRootComponent;
import com.android.systemui.dagger.WMModule;

import com.android.systemui.util.concurrency.ThreadFactory;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        GlobalModule.class,
        SysUISubcomponentModuleProton.class,
        WMModule.class})
public interface GlobalRootComponentProton extends GlobalRootComponent {

    @Component.Builder
    interface Builder extends GlobalRootComponent.Builder {
        GlobalRootComponentProton build();
    }

    @Override
    SysUIComponentProton.Builder getSysUIComponent();
}
