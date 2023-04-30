package ru.otus

import android.util.Log
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lolo.io.onelist.MainActivity
import com.lolo.io.onelist.R
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import ru.otus.screen.MainScreen

class TodoTests : BaseTest() {
// На некоторых эмуляторах для записи касперским скриншотов может понабиться текст:
   /*
    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )*/

    @get:Rule
    val applicationRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val intentsRule = IntentsRule()

    @Ignore
    @Test
    fun repeateTest() = run {
         repeat(10) {
             Log.d("ToDo", "!@# Start ================$it==================")
             completeTask()
             Log.d("ToDo", "!@# End ================$==================")
         }
    }

    @Test
    fun addTaskToList() = run {
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
                        hasDrawableWithTint(R.drawable.ic_check_black_24dp, R.color.colorPrimary)
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

    @Test

    fun completeTask() = run {
            addTaskToList()
            step("Complete task") {
                MainScreen {
                    step("Click on task in list") {
                        taskListRv {
                            firstChild<MainScreen.TaskItem> {
                                click()
                            }
                        }
                    }
                    step("Check task is completed") {
                        taskListRv {
                            lastChild<MainScreen.TaskItem> {
                                 icon {
                                hasDrawableWithTint(
                                    R.drawable.ic_bullet_outline_checked,
                                    R.color.colorAccentLight
                                )
                            }
                                description {
                                    hasText("Privet")
                                    hasTextColor(R.color.colorAccentLight)
                                    isStrikethrough()
                                }

                            }
                        }
                    }
                }
            }
    }

    @Test
    fun shareList() = run {
        step("Share list") {
            MainScreen {
                step("Click share button") {
                    shareListB {
                        isDisplayed()
                        click()
                    }
                }
                step("Check open Share") {
                    sharedIntent {
                        intended()
                    }
                }
            }
        }
    }

    @Test
    fun renameList() = run {
        step("Rename TODO list to Today list") {
            MainScreen {
                step("Click on TODO list") {
                    listRv {
                        lastChild<MainScreen.ListItem> {
                            longClick()
                        }
                    }
                }
                step("Click edit button") {
                    editListB {
                        isDisplayed()
                        click()
                    }
                }
                step("Clear list name") {
                    listNameEt {
                        clearText()
                    }
                }
                step("Enter list name") {
                    listNameEt {
                        typeText("Today")
                    }
                }
                step("Approve changes") {
                    approveListB {
                        isDisplayed()
                        click()
                    }
                }
                step("Check list is renamed") {
                    listRv {
                        lastChild<MainScreen.ListItem> {
                            renamedListTitle.hasText("Today")
                        }
                    }
                }
            }
        }
    }

    @Test
    fun deleteList() = run {
        step("Delete Tuto list") {
            MainScreen {
                step("Click on Tuto list") {
                    listRv {
                        firstChild<MainScreen.ListItem> {
                            longClick()
                        }
                    }
                }
                step("Click delete button") {
                    deleteListB {
                        isDisplayed()
                        click()
                    }
                }
                step("Approve deleting list") {
                    approveDeleteListB {
                        isDisplayed()
                        click()
                    }
                }
                step("Check list is deleted") {
                    listRv {
                        firstChild<MainScreen.ListItem> {
                            listTitle.doesNotExist()
                        }
                    }
                }
            }
        }
    }
}