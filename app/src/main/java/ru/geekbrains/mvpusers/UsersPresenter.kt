package ru.geekbrains.mvpusers

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import moxy.MvpPresenter
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.mvpuser.UserScreen
import ru.geekbrains.navigation.CustomRouter

class UsersPresenter(
    private val userRepository: GitHubUserRepository,
    private val router: CustomRouter
): MvpPresenter<UsersView>() {

    private val subject = BehaviorSubject.create<Unit>()

    override fun onFirstViewAttach() {
       observeChanges()
       updateContent()
    }

    fun goToNextScreen(login: String){
        router.navigateTo(UserScreen(login))
    }

    fun updateContent(){
        subject.onNext(Unit)
    }

    private fun observeChanges(){
        subject
            .flatMap { userRepository.getIds() }
            .flatMap { userRepository.getUserById(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                viewState.showUsers(listOf(it))
            },{
                val errorMessage = it.message
                //TODO: DisplayMessage
            })
    }
}