package com.example.mireaapp.app.presentation.schedule.model

import com.example.mireaapp.app.presentation.schedule.adapter.DelegateItem

class EventDelegateItem(val id: Int, private val value: ScheduleEventItem) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean =
        (other as EventDelegateItem).content() == value
}