package com.jahid.daggerandroidinjector.application;

import android.content.Context;

import com.jahid.daggerandroidinjector.activity.ActivitySubComponet;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {ActivitySubComponet.class})
public class ApplicationModule {

    Context context;
//
//    public ApplicationModule(Context context) {
//
//        this.context = context;
//    }

    @ApplicationScope
    @Provides
    Context context() {
        return this.context;
    }
}
