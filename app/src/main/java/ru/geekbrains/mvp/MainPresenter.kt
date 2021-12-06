package ru.geekbrains.mvp

import moxy.MvpPresenter
import ru.geekbrains.models.ButtonUiModel
import ru.geekbrains.models.ButtonTypeEnum

class MainPresenter: MvpPresenter<MainView>() {

    private val mapper = EnumToIndexMapper()
    private val model = CountersModel(mapper)

    override fun attachView(view: MainView?) {
        super.attachView(view)
        val countersCount = ButtonTypeEnum.values().size - 1
        for (index in 0..countersCount) {
            val enumValue = mapper.mapIndexToEnum(index)
            val uiModel = ButtonUiModel(enumValue, model.getCurrent(enumValue).toString())
            viewState.setButtonText(uiModel)
        }
    }


    fun counterClick(type: ButtonTypeEnum) {
        val nextValue = model.next(type)
        val uiModel = ButtonUiModel(type, nextValue.toString())
        viewState.setButtonText(uiModel)
    }
}