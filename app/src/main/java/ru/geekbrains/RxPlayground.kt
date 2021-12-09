package ru.geekbrains

import io.reactivex.rxjava3.core.Observable
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

const val article1 =
    """Реактивное программирование обеспечивает доступ к асинхронному программированию. Оно используется, чтобы упростить асинхронную обработку длительных операций. Именно это программирование — способ обработки нескольких событий, ошибок и завершения потока событий. Такой тип программирования обеспечивает также упрощённый способ запуска различных задач в разных потоках."""
const val article2 =
    """Оператор flatMap похож на map, также применяет функцию к каждому излучаемому элементу, но эта функция возвращает Obsevable. То есть один излучаемый элемент исходного источника через flatMap порождает другие. Проще говоря, flatMap из каждого элемента создаёт новый источник, после чего выполняет слияние этих источников, похожее на применение над ними оператора merge. Разберёмся на примерах."""
val random = Random(System.currentTimeMillis())


fun main() {

    Observable.just(article1)
        .map { text -> text.split(" ") }
        .flatMap { Observable.fromIterable(it) }
        .switchMap {
            val delay = random.nextInt(1000).toLong()
            return@switchMap Observable.just(it).delay(delay, TimeUnit.MILLISECONDS)
        }.subscribe { println(it) }



    Thread.sleep(50000)
    }





// put whole list
//    Observable.just(article1)
//        .map { text -> text.split(" ") }
//        .flatMap {
//            Observable.fromIterable(it)
//        }.subscribe({ word ->
//            println(word)
//        }, { error ->
//                print(error.message)
//            })


// emit event with interval
//Observable.interval(1,TimeUnit.SECONDS)
//.map { iteration -> article1.split(" ")[iteration.toInt()] }
//.subscribe({ word ->
//    println(word)
//}, { error ->
//    print(error.message)
//})


//Timer
//val observable = Observable.timer(1, TimeUnit.SECONDS)
//    .map { iteration -> article1.split(" ")[iteration.toInt()] }
//    .subscribe({ word ->
//        println(word)
//    }, { error ->
//        print(error.message)
//    })


//Interval c рандомом

//
//Thread.sleep(15000)

//FlatMapProblem
//Observable.just("1","2","3","4")
//.flatMap {
//    val delay = Random.nextInt(1000).toLong()
//    return@flatMap Observable.just(it + "x").delay(delay, TimeUnit.MILLISECONDS)
//}.subscribe{ println(it)}
//
//Thread.sleep(15000)


//FlatMapProblem With Text
//Observable.just(article1)
//.map { text -> text.split(" ") }
//.flatMap { Observable.fromIterable(it) }
//.flatMap {
//    val delay = Random.nextInt(1000).toLong()
//    return@flatMap Observable.just(it).delay(delay, TimeUnit.MILLISECONDS)
//}.subscribe{ println(it)}
//
//Thread.sleep(15000)


//Zip
//val observable1 = Observable.just("Привет").delay(1, TimeUnit.SECONDS)
//val observable2 = Observable.just(1).delay(2, TimeUnit.SECONDS)
//
//Observable.zip(
//observable1,
//observable2,
//{ t1, t2 -> Pair(t1, t2)})
//.map { println("${it.first} ok ${it.second}") }
//.subscribe()
//
//Thread.sleep(15000)