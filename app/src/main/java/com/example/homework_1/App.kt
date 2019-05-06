package com.example.homework_1

import android.support.multidex.MultiDexApplication
import io.realm.Realm

class App : MultiDexApplication()
{
    override fun onCreate()
    {
        super.onCreate()
        Realm.init(this)
    }
}