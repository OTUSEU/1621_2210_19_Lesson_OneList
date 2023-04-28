package ru.otus

import com.kaspersky.components.alluresupport.addAllureSupport
import com.kaspersky.components.alluresupport.files.attachViewHierarchyToAllureReport
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.ScreenshotParams
import com.kaspersky.kaspresso.params.VideoParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

abstract class BaseTest : TestCase(kaspressoBuilder = Kaspresso.Builder.simple(
    customize = {
        videoParams = VideoParams(bitRate = 10_000_000)
        screenshotParams = ScreenshotParams(quality = 1)
    }
).addAllureSupport().apply {
    testRunWatcherInterceptors.apply {
        add(object : TestRunWatcherInterceptor {
            override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
                viewHierarchyDumper.dumpAndApply("ViewHierarchy") { attachViewHierarchyToAllureReport() }
            }
        })
    }
}
)