package ru.geekbrains.mvpusers

import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.App.ContextHolder.context
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.mvpuser.UserScreen
import ru.geekbrains.navigation.CustomRouter

class UsersPresenter(
    private val userRepository: GitHubUserRepository,
    private val router: CustomRouter
): MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        updateData()
    }

    fun displayUser(user: GitHubUser) =
        router.navigateTo(UserScreen(user.login))

    fun updateData(){
        userRepository
            .getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showUsers(it)
            },{
                val errorMessage = it.message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            })
    }

}