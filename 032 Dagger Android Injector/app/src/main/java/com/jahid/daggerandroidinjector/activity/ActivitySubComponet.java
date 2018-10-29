package com.jahid.daggerandroidinjector.activity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ActivitySubComponet extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}
