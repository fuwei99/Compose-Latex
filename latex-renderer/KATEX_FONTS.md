# KaTeX 字体文件说明

> 字体来源：[KaTeX v0.16.11](https://cdn.jsdelivr.net/npm/katex@0.16.11/dist/fonts/)

所有字体文件存放于 `latex-render/commonMain/composeResources/font` 目录下。

---

## 1. 字体文件总览

共 20 个 TTF 文件，分为 **主字体、数学字体、装饰字体、无衬线/等宽字体、定界符尺寸字体** 五大类。

| 文件名 | 大小 | 类别 |
|--------|------|------|
| KaTeX_Main-Regular.ttf | 53.6 KB | 主字体 |
| KaTeX_Main-Bold.ttf | 51.3 KB | 主字体 |
| KaTeX_Main-Italic.ttf | 33.6 KB | 主字体 |
| KaTeX_Main-BoldItalic.ttf | 33.0 KB | 主字体 |
| KaTeX_Math-Italic.ttf | 31.3 KB | 数学字体 |
| KaTeX_Math-BoldItalic.ttf | 31.2 KB | 数学字体 |
| KaTeX_AMS-Regular.ttf | 63.6 KB | 数学字体 |
| KaTeX_Caligraphic-Regular.ttf | 12.3 KB | 装饰字体 |
| KaTeX_Caligraphic-Bold.ttf | 12.4 KB | 装饰字体 |
| KaTeX_Fraktur-Regular.ttf | 19.6 KB | 装饰字体 |
| KaTeX_Fraktur-Bold.ttf | 19.6 KB | 装饰字体 |
| KaTeX_Script-Regular.ttf | 16.6 KB | 装饰字体 |
| KaTeX_SansSerif-Regular.ttf | 19.4 KB | 无衬线/等宽 |
| KaTeX_SansSerif-Bold.ttf | 24.5 KB | 无衬线/等宽 |
| KaTeX_SansSerif-Italic.ttf | 22.4 KB | 无衬线/等宽 |
| KaTeX_Typewriter-Regular.ttf | 27.6 KB | 无衬线/等宽 |
| KaTeX_Size1-Regular.ttf | 12.2 KB | 定界符尺寸 |
| KaTeX_Size2-Regular.ttf | 11.5 KB | 定界符尺寸 |
| KaTeX_Size3-Regular.ttf | 7.6 KB | 定界符尺寸 |
| KaTeX_Size4-Regular.ttf | 10.4 KB | 定界符尺寸 |

---

## 2. 主字体 — KaTeX_Main

**覆盖字形**：拉丁字母 A-Z/a-z、数字 0-9、标准标点、基本数学符号（`+`, `-`, `=`, `<`, `>`）、希腊字母、常用关系符和运算符。

| 文件 | 字重/样式 | LaTeX 命令 | 使用场景 |
|------|----------|-----------|---------|
| KaTeX_Main-Regular | Normal 400 | `\mathrm{}`, `\text{}` | 正体文本、数字、标点、文本模式默认字体 |
| KaTeX_Main-Bold | Bold 700 | `\mathbf{}`, `\textbf{}` | 粗体文本和粗体数学符号 |
| KaTeX_Main-Italic | Italic 400 | `\mathit{}`, `\textit{}` | 文本斜体（非数学变量斜体） |
| KaTeX_Main-BoldItalic | Bold Italic 700 | `\textbf{\textit{}}` | 粗斜体文本 |

**符号子集示例**（均在 Main-Regular 中）：
- 希腊字母：`\alpha`(α), `\beta`(β), `\gamma`(γ), `\Gamma`(Γ), `\Delta`(Δ) ...
- 运算符：`\sum`(∑), `\prod`(∏), `\int`(∫)
- 关系符：`\leq`(≤), `\geq`(≥), `\equiv`(≡), `\sim`(∼), `\approx`(≈)
- 二元运算：`\times`(×), `\div`(÷), `\pm`(±), `\mp`(∓)
- 定界符（行内大小）：`(`, `)`, `[`, `]`, `\{`, `\}`, `|`
- 箭头：`\leftarrow`(←), `\rightarrow`(→), `\Rightarrow`(⇒)
- 其他：`\infty`(∞), `\partial`(∂), `\nabla`(∇), `\forall`(∀), `\exists`(∃)

---

## 3. 数学字体 — KaTeX_Math

**覆盖字形**：拉丁斜体变量字母、希腊斜体字母。

| 文件 | 字重/样式 | LaTeX 命令 | 使用场景 |
|------|----------|-----------|---------|
| KaTeX_Math-Italic | Italic 400 | `\mathnormal{}` (默认) | **数学变量的默认字体**。在数学模式中输入 `x`, `y`, `f` 等字母时自动使用 |
| KaTeX_Math-BoldItalic | Bold Italic 700 | `\boldsymbol{}` | 粗斜体数学变量（如 `\boldsymbol{\alpha}`） |

**与 Main-Italic 的区别**：
- Math-Italic 是**数学变量**的斜体（间距和字距针对数学排版优化）
- Main-Italic 是**文本**的斜体（普通文本排版）
- 例：数学模式中 `x` 用 Math-Italic，`\textit{hello}` 用 Main-Italic

---

## 4. AMS 符号字体 — KaTeX_AMS

**覆盖字形**：美国数学学会 (AMS) 扩展符号集。

| 文件 | LaTeX 命令 | 使用场景 |
|------|-----------|---------|
| KaTeX_AMS-Regular | `\mathbb{}` 及各种 AMS 符号 | 黑板粗体字母和高级数学符号 |

**符号子集示例**：
- 黑板粗体：`\mathbb{R}`(ℝ), `\mathbb{N}`(ℕ), `\mathbb{Z}`(ℤ), `\mathbb{C}`(ℂ), `\mathbb{Q}`(ℚ)
- 否定关系符：`\nless`(≮), `\nleq`(≰), `\ngeq`(≱), `\nsubseteq`(⊈)
- 几何运算符：`\boxminus`(⊟), `\boxplus`(⊞), `\boxtimes`(⊠)
- 集合论：`\varnothing`(∅), `\complement`(∁)
- 特殊箭头：`\twoheadrightarrow`(↠), `\looparrowleft`(↫)
- 特殊关系符：`\lessgtr`(≶), `\gtreqless`(⋛)

---

## 5. 装饰字体

### 5.1 KaTeX_Caligraphic — 花体

| 文件 | LaTeX 命令 | 使用场景 |
|------|-----------|---------|
| KaTeX_Caligraphic-Regular | `\mathcal{A}` | 花体大写字母（常用于拓扑空间、σ-代数等） |
| KaTeX_Caligraphic-Bold | `\mathcal{}` + `\boldsymbol` | 粗花体 |

覆盖字形：大写拉丁字母 A-Z 的花体变体。

### 5.2 KaTeX_Fraktur — 哥特体

| 文件 | LaTeX 命令 | 使用场景 |
|------|-----------|---------|
| KaTeX_Fraktur-Regular | `\mathfrak{g}` | 哥特体字母（常用于李代数 𝔤, 𝔰𝔩, 𝔰𝔲 等） |
| KaTeX_Fraktur-Bold | `\mathfrak{}` + `\boldsymbol` | 粗哥特体 |

覆盖字形：大写 A-Z 和小写 a-z 的哥特体变体。

### 5.3 KaTeX_Script — 手写花体

| 文件 | LaTeX 命令 | 使用场景 |
|------|-----------|---------|
| KaTeX_Script-Regular | `\mathscr{L}` | 手写花体大写字母（常用于拉格朗日量 ℒ、哈密顿量 ℋ 等） |

覆盖字形：大写拉丁字母 A-Z 的手写花体变体。

---

## 6. 无衬线 & 等宽字体

### 6.1 KaTeX_SansSerif — 无衬线体

| 文件 | 字重/样式 | LaTeX 命令 | 使用场景 |
|------|----------|-----------|---------|
| KaTeX_SansSerif-Regular | Normal 400 | `\mathsf{}`, `\textsf{}` | 无衬线体（常用于矩阵名、算法名等） |
| KaTeX_SansSerif-Bold | Bold 700 | `\mathsf{}` + `\textbf{}` | 无衬线粗体 |
| KaTeX_SansSerif-Italic | Italic 400 | `\mathsfit{}` | 无衬线斜体 |

### 6.2 KaTeX_Typewriter — 等宽打字机体

| 文件 | LaTeX 命令 | 使用场景 |
|------|-----------|---------|
| KaTeX_Typewriter-Regular | `\mathtt{}`, `\texttt{}` | 等宽字体（常用于代码、标识符等） |

---

## 7. 定界符尺寸字体 — KaTeX_Size1~4

这 4 个字体文件包含**不同尺寸级别的定界符字形**（括号、方括号、花括号、积分号等），每个级别的字形都是独立设计的（非缩放），因此笔画粗细在不同大小下保持一致。

| 文件 | LaTeX 命令 | 缩放级别 | 典型字形 |
|------|-----------|---------|---------|
| KaTeX_Size1-Regular | `\big(`, `\big)`, `\big[` ... | 1 级（约 1.2x） | 中型括号、中型积分号 |
| KaTeX_Size2-Regular | `\Big(`, `\Big)`, `\Big[` ... | 2 级（约 1.8x） | 大型括号 |
| KaTeX_Size3-Regular | `\bigg(`, `\bigg)`, `\bigg[` ... | 3 级（约 2.4x） | 更大括号 |
| KaTeX_Size4-Regular | `\Bigg(`, `\Bigg)`, `\Bigg[` ... | 4 级（约 3.0x） | 最大括号 |

**`\left...\right` 自动伸缩**：KaTeX 根据内容高度从 Main → Size1 → Size2 → Size3 → Size4 逐级选择，选择能包含内容的最小尺寸字形。

**每个 Size 字体包含的字形**：
- 圆括号：`(`, `)`
- 方括号：`[`, `]`
- 花括号：`{`, `}`
- 绝对值/范数：`|`, `‖`
- 尖括号：`⟨`, `⟩`
- 其他：`/`, `\`, `⌈`, `⌉`, `⌊`, `⌋`

---

## 8. 字体名生成规则

KaTeX 通过三个维度组合字体名：**`{Family}-{Style}`**

```
Family:  Main | Math | AMS | Caligraphic | Fraktur | Script | SansSerif | Typewriter | Size1~4
Style:   Regular | Bold | Italic | BoldItalic
```

文本模式的组合逻辑：

| fontFamily | fontWeight | fontShape | 最终字体 |
|-----------|-----------|-----------|---------|
| textrm | — | — | Main-Regular |
| textrm | textbf | — | Main-Bold |
| textrm | — | textit | Main-Italic |
| textrm | textbf | textit | Main-BoldItalic |
| textsf | — | — | SansSerif-Regular |
| textsf | textbf | — | SansSerif-Bold |
| textsf | — | textit | SansSerif-Italic |
| texttt | — | — | Typewriter-Regular |
| amsrm | — | — | AMS-Regular |

---

## 9. 默认字体选择逻辑

```
数学模式 (math mode):
  ├─ 普通字母 (mathord) → Math-Italic（变量自动斜体）
  ├─ 文本字符 (textord) → Main-Regular
  ├─ 显式 \mathbf{} → Main-Bold
  ├─ 显式 \boldsymbol{} → Math-BoldItalic（优先）或 Main-Bold（回退）
  ├─ 运算符 \sin, \cos → Main-Regular
  └─ 大型运算符 → Size1-Regular（行内）/ Size2-Regular（展示模式）

文本模式 (text mode):
  └─ 默认 → Main-Regular，根据 \textbf/\textit 切换字重和样式
```

---

## 10. fontMetricsData 度量数据

KaTeX 为每个字体的每个字符存储 5 项度量值：

```
[depth, height, italic, skew, width]
```

| 字段 | 含义 | 用途 |
|------|------|------|
| depth | 字形基线以下深度 | 垂直排版（下标、分母定位） |
| height | 字形基线以上高度 | 垂直排版（上标、分子定位） |
| italic | 斜体修正值 | 上标水平偏移补偿 |
| skew | 字形倾斜度 | 重音符号居中修正 |
| width | 字形宽度 | 水平排版间距 |

这些度量数据确保了数学公式在不同字体、不同字符组合下的精确排版。

当前 renderer 以 KaTeX v0.16.11 的 `sigmasAndXis`、逐字形 italic correction、
Size1–Size4 选择规则，以及 TeX Rule 15（分数）/ Rule 18（上下标）作为 TTF
measure/layout 的基准。字体不再是 `LatexConfig` 配置项；渲染器固定使用
内置 KaTeX TTF，定界符、根号和大型运算符统一从 Main/Size1–Size4
选择字形。
