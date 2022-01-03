package ru.geekbrains.mvpuser.di_comp

import dagger.Subcomponent
import ru.geekbrains.mvpuser.UserPresenter
import javax.inject.Scope

@FragmentScope
@Subcomponent(
    modules = [FragmentModule::class]
)

interface FragmentComponent {

    @Subcomponent.Builder
    interface Builder{
        fun build():FragmentComponent
    }

    fun inject(activity:UserPresenter)
}

@Scope
annotation class FragmentScope