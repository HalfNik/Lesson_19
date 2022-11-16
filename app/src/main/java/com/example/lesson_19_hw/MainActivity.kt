package com.example.lesson_19_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.just(3, 5, 7, 9, 11) //Наблюдаемый
            .subscribeOn(Schedulers.io())
            .map { number -> number * 2 }
            .subscribe( //Наблюдатель
                { value -> println("The number equals $value, ${Thread.currentThread()}") },
                { error -> println(error) })


        Flowable.just("This is Flowable")
            .subscribeOn(Schedulers.io())
            .subscribe(
                { value -> println("Received: $value ") },
                { error -> println(error) }
            )


        Single.just("This is a Single")
            .subscribeOn(Schedulers.io())
            .subscribe(
                { v -> println("Value is: $v") },
                { e -> println("Error: $e")}
            )

        Maybe.just("This is a Maybe")
            .subscribeOn(Schedulers.io())
            .subscribe(
                { value -> println("Received: $value") },
                { error -> println(error) }
            )


        Completable.create { emitter ->
            emitter.onComplete()
            emitter.onError(Exception())
        }

    }
}