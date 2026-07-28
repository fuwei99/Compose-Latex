/*
 * Copyright (c) 2026 huarangmeng
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hrm.latex.renderer.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import com.hrm.latex.renderer.layout.NodeLayout
import com.hrm.latex.renderer.model.LatexFontFamilies
import com.hrm.latex.renderer.model.RenderContext
import com.hrm.latex.renderer.model.textStyle

/** KaTeX Main/Size1…Size4 定界符选择与精确墨迹测量。 */
internal object DelimiterRenderer {
    private data class SizeLevel(
        val font: (LatexFontFamilies) -> FontFamily,
        val bytes: (LatexFontFamilies) -> ByteArray?
    )

    private val sizeLevels = listOf(
        SizeLevel({ it.main }, { it.mainBytes }),
        SizeLevel({ it.size1 }, { it.size1Bytes }),
        SizeLevel({ it.size2 }, { it.size2Bytes }),
        SizeLevel({ it.size3 }, { it.size3Bytes }),
        SizeLevel({ it.size4 }, { it.size4Bytes }),
    )

    fun measureScaled(
        delimiter: String,
        context: RenderContext,
        measurer: TextMeasurer,
        targetHeight: Float,
        density: Density? = null
    ): NodeLayout {
        if (delimiter.isEmpty()) return NodeLayout.EMPTY

        val glyph = FontResolver.resolveDelimiterGlyph(delimiter, context.fontFamilies)
        val families = context.fontFamilies
        if (families == null) {
            return measureText(
                glyph,
                FontResolver.delimiterContext(context, delimiter),
                measurer,
                density = density
            )
        }

        var best = NodeLayout.EMPTY
        var bestContext = context
        for (level in sizeLevels) {
            val levelContext = context.copy(
                fontFamily = level.font(families),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal
            )
            val layout = measureText(glyph, levelContext, measurer, level.bytes(families), density)
            best = layout
            bestContext = levelContext
            if (targetHeight <= 0f || layout.height >= targetHeight) return layout
        }

        if (best.height <= 0f) return best
        val scaledContext = bestContext.copy(fontSize = context.fontSize * (targetHeight / best.height))
        return measureText(glyph, scaledContext, measurer, families.size4Bytes, density)
    }

    fun measureText(
        delimiter: String,
        delimiterStyle: RenderContext,
        measurer: TextMeasurer,
        fontBytes: ByteArray? = null,
        density: Density? = null
    ): NodeLayout {
        val result = measurer.measure(AnnotatedString(delimiter), delimiterStyle.textStyle())
        val fontSizePx = density?.let { with(it) { delimiterStyle.fontSize.toPx() } }
            ?: delimiterStyle.fontSize.value
        val precise = fontBytes?.let {
            InkBoundsEstimator.measurePrecise(
                delimiter,
                fontSizePx,
                it,
                result.firstBaseline,
                delimiterStyle.fontWeight?.weight ?: 400
            )
        }
        val topOffset = precise?.inkTopOffset ?: 0f
        return NodeLayout(
            result.size.width.toFloat(),
            precise?.inkHeight ?: result.size.height.toFloat(),
            precise?.inkBaseline ?: result.firstBaseline
        ) { x, y ->
            drawText(result, topLeft = Offset(x, y - topOffset))
        }
    }
}
