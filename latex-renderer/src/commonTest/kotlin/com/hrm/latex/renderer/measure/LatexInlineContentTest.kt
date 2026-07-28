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

package com.hrm.latex.renderer.measure

import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import com.hrm.latex.renderer.model.LatexConfig
import kotlin.test.Test
import kotlin.test.assertEquals

class LatexInlineContentTest {
    @Test
    fun placeholderUsesFullCanvasDimensionsAndTextCenterAlignment() {
        val dimensions = LatexDimensions(
            widthPx = 24f,
            heightPx = 16f,
            baselinePx = 11f,
            contentWidthPx = 20f,
            contentHeightPx = 12f,
            contentBaselinePx = 9f
        )

        val inlineContent = dimensions.toInlineTextContent(
            density = Density(density = 2f, fontScale = 1f),
            latex = "\\frac{a}{b}",
            config = LatexConfig()
        )

        assertEquals(12.sp, inlineContent.placeholder.width)
        assertEquals(8.sp, inlineContent.placeholder.height)
        assertEquals(
            PlaceholderVerticalAlign.TextCenter,
            inlineContent.placeholder.placeholderVerticalAlign
        )
    }
}
