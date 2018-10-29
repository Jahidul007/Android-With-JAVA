package com.jahid.daggerandroidinjector.application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        ApplicationModule.class,
        BindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder applicationComponent(MyApplication application);
        ApplicationComponent build();
    }
    void inject(MyApplication application);

}
