package ru.geekbrains

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.databinding.ActivityMainBinding
import ru.geekbrains.models.ButtonTypeEnum
import ru.geekbrains.models.ButtonUiModel
import ru.geekbrains.mvp.MainPresenter
import ru.geekbrains.mvp.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var viewBinding: ActivityMainBinding
    private val presenter by moxyPresenter {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnCounter1.setOnClickListener {
            presenter.counterClick(ButtonTypeEnum.FIRST_BUTTON)
        }

        viewBinding.btnCounter2.setOnClickListener {
            presenter.counterClick(ButtonTypeEnum.SECOND_BUTTON)
        }

        viewBinding.btnCounter3.setOnClickListener {
            presenter.counterClick(ButtonTypeEnum.THIRD_BUTTON)
        }
    }

    override fun setButtonText(model: ButtonUiModel) {
        when(model.index) {
            ButtonTypeEnum.FIRST_BUTTON -> viewBinding.btnCounter1.text = model.value
            ButtonTypeEnum.SECOND_BUTTON -> viewBinding.btnCounter2.text = model.value
            ButtonTypeEnum.THIRD_BUTTON -> viewBinding.btnCounter3.text = model.value
        }
    }
}
