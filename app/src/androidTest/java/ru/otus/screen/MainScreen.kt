package ru.otus.screen

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kaspersky.kaspresso.screens.KScreen
import com.lolo.io.onelist.OneListFragment
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import com.lolo.io.onelist.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.otus.component.KImageButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int = R.layout.fragment_one_list
    override val viewClass: Class<*> = OneListFragment::class.java

    val taskNameEt = KEditText {
        withId(R.id.addItemEditText)
    }

    val addTaskB = KImageButton {
        withId(R.id.validate)
    }

    val taskListRv = KRecyclerView(
        {
            withId(R.id.itemsRecyclerView)
        },
        itemTypeBuilder = {
            itemType(::TaskItem)
        }
    )

    internal class TaskItem(parent: Matcher<View>) : KRecyclerItem<TaskItem>(parent) {
        val icon = KImageView(parent) {
            withId(R.id.badge)
        }

        val description = KTextView(parent) {
            withId(R.id.text)
        }
    }

}