package ru.geekbrains.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.geekbrains.MainActivity
import ru.geekbrains.mvpuser.UserPresenter
import ru.geekbrains.mvpuser.di_comp.FragmentComponent
import ru.geekbrains.mvpusers.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        LocalStorageModule::class,
        NavigationModule::class,
        NetworkModule::class
    ]
)

interface ApplicationComponent {

    fun provideUserComponent(): FragmentComponent.Builder

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun setContext(context: Context):Builder
        fun build():ApplicationComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: UsersPresenter)
    fun inject(activity: UserPresenter)
}