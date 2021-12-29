package ru.geekbrains.mvpusers

import android.widget.Toast
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.App
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.mvpuser.UserScreen
import javax.inject.Inject

class UsersPresenter(): MvpPresenter<UsersView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var userRepository:GitHubUserRepository

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
                Toast.makeText(App.instance, errorMessage, Toast.LENGTH_SHORT).show()
            })
    }

}