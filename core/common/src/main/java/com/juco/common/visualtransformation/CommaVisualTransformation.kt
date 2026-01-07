package com.juco.common.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat

class CommaVisualTransformation(
    pattern: String = "#,###"
) : VisualTransformation {

    private val decimalFormat = DecimalFormat(pattern)

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text

        if (originalText.isEmpty()) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        val formattedText = try {
            decimalFormat.format(originalText.toLongOrNull() ?: 0)
        } catch (e: Exception) {
            originalText
        }

        // 커서 위치 보정
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (originalText.isEmpty()) return 0
                if (offset >= originalText.length) return formattedText.length
                var digitCount = 0
                var transformedIndex = 0
                while (transformedIndex < formattedText.length && digitCount < offset) {
                    if (formattedText[transformedIndex].isDigit()) {
                        digitCount++
                    }
                    transformedIndex++
                }
                return transformedIndex
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (originalText.isEmpty()) return 0
                if (offset >= formattedText.length) return originalText.length
                val textBeforeCursor = formattedText.take(offset)
                val nonDigitCount = textBeforeCursor.count { !it.isDigit() }
                return (offset - nonDigitCount).coerceIn(0, originalText.length)
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}