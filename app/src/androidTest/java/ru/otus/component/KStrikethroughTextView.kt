package ru.otus.component

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import io.github.kakaocup.kakao.common.builders.ViewBuilder
import io.github.kakaocup.kakao.common.views.KBaseView
import io.github.kakaocup.kakao.text.TextViewActions
import io.github.kakaocup.kakao.text.TextViewAssertions
import org.hamcrest.Description
import org.hamcrest.Matcher

class KStrikethroughTextView(parent: Matcher<View>, function: ViewBuilder.() -> Unit) :
    KBaseView<KStrikethroughTextView>(parent, function),
    TextViewActions, TextViewAssertions {

    fun isStrikethrough() {
        view.check(
            ViewAssertions.matches(
                StrikethroughStyleMatcher()
            )
        )
    }
}


class StrikethroughStyleMatcher : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("Strikethrough text style doesn't match.")
    }

    override fun matchesSafely(textView: TextView): Boolean =
        textView.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG
}