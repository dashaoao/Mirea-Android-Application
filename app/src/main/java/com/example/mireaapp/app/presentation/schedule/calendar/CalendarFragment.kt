package com.example.mireaapp.app.presentation.schedule.calendar

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mireaapp.R
import com.example.mireaapp.app.presentation.schedule.ScheduleViewModel
import com.example.mireaapp.databinding.CalendarDayLayoutBinding
import com.example.mireaapp.databinding.CalendarDotLayoutBinding
import com.example.mireaapp.databinding.FragmentCalendarBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.WeekDayPosition
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.yearMonth
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private val viewModel: ScheduleViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        return binding.root
    }

    private val monthCalendar get() = binding.monthCalendar
    private val weekCalendar get() = binding.weekCalendar
    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setup titles
        val daysOfWeek = daysOfWeek()
        binding.titlesContainer.root.children.forEachIndexed { index, textView ->
            (textView as TextView).text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.getDefault())
        }
        // setup days
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        monthCalendar.apply {
            dayBinder = monthDayBinder
            monthScrollListener = { updateTitle() }
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }
        weekCalendar.apply {
            dayBinder = weekDayBinder
            weekScrollListener = { updateTitle() }
            setup(startMonth.atStartOfMonth(), endMonth.atEndOfMonth(), daysOfWeek.first())
            scrollToWeek(currentMonth.atStartOfMonth())
        }
        // setup week/month mode
        binding.modeCheckBox.setOnCheckedChangeListener { _, selected ->
            changeCalendarMode(selected)
        }
        if (savedInstanceState == null) {
            binding.modeCheckBox.text = requireContext().getString(R.string.month)
        }
        monthCalendar.isInvisible = binding.modeCheckBox.isChecked
        weekCalendar.isInvisible = !binding.modeCheckBox.isChecked
    }

    private fun onDateClick(date: LocalDate) {
        if (selectedDate == date) return
        viewModel.loadScheduleForDate(date)
        val oldDate = selectedDate
        selectedDate = date
        oldDate?.let {
            monthCalendar.notifyDateChanged(it)
            weekCalendar.notifyDateChanged(it)
        }
        monthCalendar.notifyDateChanged(date)
        weekCalendar.notifyDateChanged(date)
    }

    private val monthDayBinder = object : MonthDayBinder<DayViewContainer> {
        override fun create(view: View) = DayViewContainer(view).apply {
            view.setOnClickListener {
                if (day.position == DayPosition.MonthDate) onDateClick(day.date)
            }
        }

        override fun bind(container: DayViewContainer, data: CalendarDay) {
            container.day = data
            val textView = container.binding.calendarDayText
            textView.text = data.date.dayOfMonth.toString()

            if (data.position == DayPosition.MonthDate) {
                textView.alpha = 1f
                textView.setDayBackground(data.date)
                container.binding.setDots(data.date)
            } else
                textView.alpha = 0.3f
        }
    }

    private val weekDayBinder = object : WeekDayBinder<WeekDayViewContainer> {
        override fun create(view: View): WeekDayViewContainer = WeekDayViewContainer(view).apply {
            view.setOnClickListener {
                if (day.position == WeekDayPosition.RangeDate) onDateClick(day.date)
            }
        }

        override fun bind(container: WeekDayViewContainer, data: WeekDay) {
            container.day = data
            val textView = container.binding.calendarDayText
            textView.text = data.date.dayOfMonth.toString()

            textView.alpha = 1f
            textView.setDayBackground(data.date)
            container.binding.setDots(data.date)
        }
    }

    private fun View.setDayBackground(date: LocalDate) {
        when (date) {
            today -> setBackgroundResource(R.drawable.calendar_today_bg)
            selectedDate -> setBackgroundResource(R.drawable.calendar_selected_bg)
            else -> background = null
        }
    }

    private fun CalendarDayLayoutBinding.setDots(date: LocalDate) {
        dotsLayout.removeAllViews()
        viewModel.semesterSchedule[date]?.forEach {
            dotsLayout.addView(
                CalendarDotLayoutBinding.inflate(layoutInflater, root, false).root
            )
        }
    }

    private fun changeCalendarMode(monthToWeek: Boolean) {
        if (monthToWeek) {
            binding.modeCheckBox.text = requireContext().getString(R.string.week)
            val targetDate = monthCalendar.findFirstVisibleDay()?.date ?: return
            weekCalendar.scrollToWeek(targetDate)
        } else {
            binding.modeCheckBox.text = requireContext().getString(R.string.month)
            val targetMonth = weekCalendar.findLastVisibleDay()?.date?.yearMonth ?: return
            monthCalendar.scrollToMonth(targetMonth)
        }

        val weekHeight = weekCalendar.height
        val visibleMonthHeight = weekHeight * monthCalendar.findFirstVisibleMonth()?.weekDays.orEmpty().count()

        val oldHeight = if (monthToWeek) visibleMonthHeight else weekHeight
        val newHeight = if (monthToWeek) weekHeight else visibleMonthHeight

        val animator = ValueAnimator.ofInt(oldHeight, newHeight)
        animator.addUpdateListener { anim ->
            monthCalendar.updateLayoutParams {
                height = anim.animatedValue as Int
            }
            monthCalendar.children.forEach { child ->
                child.requestLayout()
            }
        }

        animator.doOnStart {
            if (!monthToWeek) {
                weekCalendar.isInvisible = true
                monthCalendar.isVisible = true
            }
        }
        animator.doOnEnd {
            if (monthToWeek) {
                weekCalendar.isVisible = true
                monthCalendar.isInvisible = true
            } else {
                monthCalendar.updateLayoutParams {
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                }
            }
            updateTitle()
        }
        animator.duration = 250
        animator.start()
    }

    private fun updateTitle() {
        if (binding.modeCheckBox.isChecked) {
            val week = binding.weekCalendar.findFirstVisibleWeek()?.days?.first()?.date ?: return
            binding.date.text = "${week.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${week.year}"
        } else {
            val month = binding.monthCalendar.findFirstVisibleMonth()?.yearMonth ?: return
            binding.date.text = "${month.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${month.year}"
        }
    }
}