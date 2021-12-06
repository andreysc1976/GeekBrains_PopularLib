package ru.geekbrains.mvp

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.models.ButtonUiModel

@StateStrategyType(SingleStateStrategy::class)
interface MainView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setButtonText(model: ButtonUiModel)
}