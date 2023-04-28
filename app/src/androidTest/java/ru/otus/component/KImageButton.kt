package ru.otus.component

import io.github.kakaocup.kakao.common.builders.ViewBuilder
import io.github.kakaocup.kakao.common.views.KBaseView
import io.github.kakaocup.kakao.image.ImageViewAssertions
import io.github.kakaocup.kakao.text.TextViewAssertions

class KImageButton(function: ViewBuilder.() -> Unit) : KBaseView<KImageButton>(function),
    TextViewAssertions, ImageViewAssertions