package ru.geekbrains

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

const val article1 =
    """Реактивное программирование обеспечивает доступ к асинхронному программированию. Оно используется, чтобы упростить асинхронную обработку длительных операций. Именно это программирование — способ обработки нескольких событий, ошибок и завершения потока событий. Такой тип программирования обеспечивает также упрощённый способ запуска различных задач в разных потоках."""
const val article2 =
    """Оператор flatMap похож на map, также применяет функцию к каждому излучаемому элементу, но эта функция возвращает Obsevable. То есть один излучаемый элемент исходного источника через flatMap порождает другие. Проще говоря, flatMap из каждого элемента создаёт новый источник, после чего выполняет слияние этих источников, похожее на применение над ними оператора merge. Разберёмся на примерах."""
val random = Random(System.currentTimeMillis())


fun main() {

    Flowable.fromIterable(listOf(0,1,3,4,5))
        .onBackpressureDrop()
        .map {
            User(it.toString(), "UserName")
        }.subscribe()
}


data class User(val userId: String, val userName: String)
