package com.jahid.daggerandroidinjector.application;

import android.app.Activity;

import com.jahid.daggerandroidinjector.activity.ActivitySubComponet;
import com.jahid.daggerandroidinjector.activity.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
abstract public class BindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivity(ActivitySubComponet.Builder builder);
}
