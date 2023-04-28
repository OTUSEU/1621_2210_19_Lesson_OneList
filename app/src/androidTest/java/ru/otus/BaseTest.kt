package ru.otus

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase


abstract class BaseTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced {
        flakySafetyParams = FlakySafetyParams.custom(10000)
    }
)