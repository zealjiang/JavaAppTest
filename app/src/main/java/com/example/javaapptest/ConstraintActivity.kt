package com.example.javaapptest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class ConstraintActivity : AppCompatActivity() {

    val name: String? = null
    private val index: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)

        System.currentTimeMillis()
        name?.trim()
        //name!!.trim()

        println(
                list.joinToString(
                        prefix = "[",
                        separator = ":",
                        postfix = "]",
                        limit = 3,
                        truncated = "...",
                        transform = { it.toUpperCase() })
        )
    }

    fun threadlocalInfo(){
        var h: Handler = Handler()
        //h.looper.queue.postSyncBarrier()
        val looper: Looper = Looper.getMainLooper()

    }

    val list = listOf("one", "two", "three", "four", "five")

}