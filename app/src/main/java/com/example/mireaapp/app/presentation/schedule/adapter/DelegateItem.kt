package com.example.mireaapp.app.presentation.schedule.adapter

interface DelegateItem {
    fun content(): Any
    fun id(): Int
    fun compareToOther(other: DelegateItem): Boolean
}