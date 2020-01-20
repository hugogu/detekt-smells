package io.hugogu.detekt.extensions.processors

import io.gitlab.arturbosch.detekt.api.DetektVisitor
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.FileProcessListener
import io.gitlab.arturbosch.detekt.api.ProjectMetric
import org.jetbrains.kotlin.com.intellij.openapi.util.Key
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtLoopExpression

class NumberOfLoopsProcessor : FileProcessListener {
    private val visitor = LoopVisitor()

    override fun onProcess(file: KtFile) {
        file.accept(visitor)
        file.putUserData(numberOfLoopsKey, visitor.numberOfLoops)
    }

    override fun onFinish(files: List<KtFile>, result: Detektion) {
        val count = files
            .mapNotNull { it.getUserData(numberOfLoopsKey) }
            .sum()
        result.add(ProjectMetric(numberOfLoopsKey.toString(), count))
    }

    companion object {
        val numberOfLoopsKey = Key<Int>("number of loops")
    }

    class LoopVisitor : DetektVisitor() {
        internal var numberOfLoops = 0
        override fun visitLoopExpression(loopExpression: KtLoopExpression) {
            super.visitLoopExpression(loopExpression)
            numberOfLoops++
        }
    }
}
