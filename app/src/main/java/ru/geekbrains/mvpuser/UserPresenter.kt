package ru.geekbrains.mvpuser

import com.google.android.material.appbar.AppBarLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import moxy.MvpPresenter
import ru.geekbrains.App
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.navigation.CustomRouter
import javax.inject.Inject

class UserPresenter(
    private val userLogin: String,
) : MvpPresenter<UserView>() {

    @Inject lateinit var userRepository:GitHubUserRepository

    override fun onFirstViewAttach() {
        App.instance.component.inject(this)
        userRepository
            .getUserByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({user->
                viewState.showUser(user)
            })
    }


}