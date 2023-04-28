package ru.otus

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.lolo.io.onelist.MainActivity
import org.junit.Rule
import org.junit.Test
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import ru.otus.screen.MainScreen
import com.lolo.io.onelist.R


class AddTodoTest : BaseTest() {

    @get:Rule
    val applicationRule = ActivityScenarioRule(MainActivity::class.java)
    
    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    @Test
    fun addTodoToList() = run {
        step("Create task") {
            MainScreen {
                step("Enter task name") {
                    taskNameEt {
                        typeText("Privet")
                    }
                }
                step("Click add button") {
                    addTaskB {
                        isDisplayed()
                        hasDrawableWithTint(
                            R.drawable.ic_check_black_24dp,
                            R.color.colorCyanListClick
                        )
                        click()
                    }
                }
                step("Check task in list") {
                    taskListRv {
                        firstChild<MainScreen.TaskItem> {
                            icon {
                                hasDrawable(R.drawable.ic_bullet_outline)
                            }
                            description {
                                hasText("Privet")
                            }
                        }
                    }
                }
            }
        }
    }
}
