package ru.geekbrains.mvpusers

import moxy.MvpPresenter
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.mvpuser.UserScreen
import ru.geekbrains.navigation.CustomRouter

class UsersPresenter(
    private val userRepository: GitHubUserRepository,
    private val router: CustomRouter
): MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {

        userRepository.getIds()
            .flatMap { userRepository.getUserById(it) }
            .subscribe(
                { result -> viewState.showUsers(listOf(result)) },
                {}
            )

    }

    fun displayUser(user: GitHubUser) =
        router.navigateTo(UserScreen(user.login))


}