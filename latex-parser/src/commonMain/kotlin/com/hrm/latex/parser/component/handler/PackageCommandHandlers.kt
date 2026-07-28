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

package com.hrm.latex.parser.component.handler

import com.hrm.latex.parser.component.LatexParserContext
import com.hrm.latex.parser.component.LatexTokenStream
import com.hrm.latex.parser.model.LatexNode
import com.hrm.latex.parser.tokenizer.LatexToken

/** Common, deliberately small subsets of the physics and siunitx packages. */
internal fun CommandRegistry.installPackageCommandHandlers() {
    installPhysicsHandlers()
    installSiunitxHandlers()
}

private fun CommandRegistry.installPhysicsHandlers() {
    register("abs") { _, ctx, _ -> delimited(ctx, "|", "|") }
    register("norm") { _, ctx, _ -> delimited(ctx, "‖", "‖") }
    register("bra") { _, ctx, _ -> delimited(ctx, "⟨", "|") }
    register("ket") { _, ctx, _ -> delimited(ctx, "|", "⟩") }
    register("braket") { _, ctx, _ -> delimited(ctx, "⟨", "⟩") }

    register("dv", "pdv") { command, ctx, stream ->
        val order = parseOptionalNode(ctx, stream)
        val first = ctx.parseArgument() ?: LatexNode.Text("")
        val second = if (stream.peekSkipping { it is LatexToken.Whitespace } is LatexToken.LeftBrace) {
            ctx.parseArgument()
        } else {
            null
        }
        derivative(
            marker = if (command == "pdv") {
                LatexNode.Symbol("partial", "∂")
            } else {
                LatexNode.Style(listOf(LatexNode.Text("d")), LatexNode.Style.StyleType.ROMAN)
            },
            function = second?.let { first },
            variable = second ?: first,
            order = order
        )
    }
}

private fun CommandRegistry.installSiunitxHandlers() {
    register("num") { _, ctx, _ ->
        formatNumber(ctx.parseArgument() ?: LatexNode.Text(""))
    }

    register("si") { _, ctx, _ ->
        formatUnit(ctx.parseArgument() ?: LatexNode.Text(""))
    }

    register("SI") { _, ctx, _ ->
        val number = formatNumber(ctx.parseArgument() ?: LatexNode.Text(""))
        val unit = formatUnit(ctx.parseArgument() ?: LatexNode.Text(""))
        LatexNode.Group(
            listOf(number, LatexNode.Space(LatexNode.Space.SpaceType.THIN), unit)
        )
    }
}

private fun delimited(ctx: LatexParserContext, left: String, right: String): LatexNode {
    val arg = ctx.parseArgument() ?: LatexNode.Text("")
    return LatexNode.Delimited(left, right, unwrap(arg))
}

private fun derivative(
    marker: LatexNode,
    function: LatexNode?,
    variable: LatexNode,
    order: LatexNode?
): LatexNode.Fraction {
    val numeratorMarker = order?.let { LatexNode.Superscript(marker, it) } ?: marker
    val denominatorVariable = order?.let { LatexNode.Superscript(variable, it) } ?: variable
    val numerator = buildList {
        add(numeratorMarker)
        function?.let(::add)
    }
    return LatexNode.Fraction(
        numerator = LatexNode.Group(numerator),
        denominator = LatexNode.Group(listOf(marker, denominatorVariable))
    )
}

private fun formatNumber(arg: LatexNode): LatexNode {
    val text = ParseUtils.extractText(unwrap(arg)).trim()
    val scientific = SCIENTIFIC_NUMBER.matchEntire(text) ?: return arg
    val mantissa = scientific.groupValues[1]
    val exponent = scientific.groupValues[2].removePrefix("+")
    return LatexNode.Group(
        listOf(
            LatexNode.Text(mantissa),
            LatexNode.Space(LatexNode.Space.SpaceType.THIN),
            LatexNode.Symbol("times", "×"),
            LatexNode.Space(LatexNode.Space.SpaceType.THIN),
            LatexNode.Superscript(LatexNode.Text("10"), LatexNode.Text(exponent))
        )
    )
}

private fun formatUnit(arg: LatexNode): LatexNode {
    val content = unwrap(arg).map { node ->
        node.mapNodes { child ->
            if (child is LatexNode.Text && '.' in child.content) {
                child.copy(content = child.content.replace('.', '·'))
            } else {
                child
            }
        }
    }
    return LatexNode.Style(content, LatexNode.Style.StyleType.ROMAN)
}

private fun parseOptionalNode(ctx: LatexParserContext, stream: LatexTokenStream): LatexNode? {
    if (stream.peek() !is LatexToken.LeftBracket) return null
    stream.advance()
    val nodes = ParseUtils.parseUntil(ctx, stream) { it is LatexToken.RightBracket }
    if (stream.peek() is LatexToken.RightBracket) stream.advance()
    return nodes.takeIf { it.isNotEmpty() }?.let(LatexNode::Group)
}

private fun unwrap(node: LatexNode): List<LatexNode> =
    if (node is LatexNode.Group) node.children else listOf(node)

private val SCIENTIFIC_NUMBER =
    Regex("""([+-]?(?:\d+(?:\.\d*)?|\.\d+))[eE]([+-]?\d+)""")
