package ru.geekbrains

import android.os.Bundle
import android.widget.Button
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.models.ButtonTypeEnum
import ru.geekbrains.models.ButtonUiModel
import ru.geekbrains.mvp.MainPresenter
import ru.geekbrains.mvp.MainView

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private var btnCounter1: Button? = null
    private var btnCounter2: Button? = null
    private var btnCounter3: Button? = null

    private val presenter by moxyPresenter {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnCounter1 = findViewById(R.id.btn_counter1)
        btnCounter2 = findViewById(R.id.btn_counter2)
        btnCounter3 = findViewById(R.id.btn_counter3)

        btnCounter1?.setOnClickListener {
            presenter.counterClick(ButtonTypeEnum.FIRST_BUTTON)
        }

        btnCounter2?.setOnClickListener {
            presenter.counterClick(ButtonTypeEnum.SECOND_BUTTON)
        }

        btnCounter3?.setOnClickListener {
            presenter.counterClick(ButtonTypeEnum.THIRD_BUTTON)
        }
    }

    override fun setButtonText(model: ButtonUiModel) {
        when(model.index) {
            ButtonTypeEnum.FIRST_BUTTON -> btnCounter1?.text = model.value
            ButtonTypeEnum.SECOND_BUTTON -> btnCounter2?.text = model.value
            ButtonTypeEnum.THIRD_BUTTON -> btnCounter3?.text = model.value
        }
    }
}
