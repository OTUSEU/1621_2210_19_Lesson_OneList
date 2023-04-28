package ru.otus.screen

import android.content.Intent
import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.lolo.io.onelist.OneListFragment
import com.lolo.io.onelist.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.intent.KIntent
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.otus.component.KImageButton
import ru.otus.component.KStrikethroughTextView

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
        }, itemTypeBuilder = {
            itemType(::TaskItem)
        }
    )

    val shareListB = KImageButton {
        withId(R.id.buttonShareList)
    }

    val deleteListB = KImageButton {
        withId(R.id.buttonRemoveList)
    }

    val editListB = KImageButton {
        withId(R.id.buttonEditList)
    }

    val listNameEt = KEditText {
        withId(R.id.listTitle)
    }

    val approveListB = KImageButton {
        withId(R.id.validateEditList)
    }

    val approveDeleteListB = KImageButton {
        withId(R.id.validateDeleteList)
    }

    val listRv: KRecyclerView = KRecyclerView(
        {
            withId(R.id.listsRecyclerView)
        }, itemTypeBuilder = {
            itemType(::ListItem)
        }
    )

    val sharedIntent = KIntent {
        hasAction(Intent.ACTION_CHOOSER)
    }


    internal class TaskItem(parent: Matcher<View>) : KRecyclerItem<TaskItem>(parent) {
        val icon = KImageView(parent) {
            withId(R.id.badge)
        }

        val description = KStrikethroughTextView(parent) {
            withId(R.id.text)
        }
    }

    internal class ListItem(parent: Matcher<View>) : KRecyclerItem<ListItem>(parent) {
        val listTitle = KTextView {
            withId(R.id.textView)
            withText("Tuto ❓")
        }

        val renamedListTitle = KTextView {
            withId(R.id.textView)
            withText("Today")
        }
    }
}