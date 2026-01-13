package com.juco.subscription_edit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juco.common.util.formatDate
import com.juco.designsystem.theme.SubManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentDateSection(
    modifier: Modifier = Modifier,
    selectedDate: Long,
    onDateChanged: (Long) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            colors = DatePickerDefaults.colors(
                containerColor = SubManagerTheme.colors.secondaryBackground
            ),
            confirmButton = {
                Text(
                    text = "확인",
                    style = SubManagerTheme.typography.b1SemiBold,
                    color = SubManagerTheme.colors.primaryText,
                    modifier = Modifier
                        .clickable {
                            datePickerState.selectedDateMillis?.let { millis ->
                                onDateChanged(millis)
                            }
                            showDatePicker = false
                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            },
            dismissButton = {
                Text(
                    text = "취소",
                    style = SubManagerTheme.typography.b1Regular,
                    color = SubManagerTheme.colors.secondaryText,
                    modifier = Modifier
                        .clickable { showDatePicker = false }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = SubManagerTheme.colors.secondaryBackground,
                    titleContentColor = SubManagerTheme.colors.secondaryText,
                    headlineContentColor = SubManagerTheme.colors.primaryText,
                    navigationContentColor = SubManagerTheme.colors.primaryText,
                    weekdayContentColor = SubManagerTheme.colors.secondaryText,
                    dayContentColor = SubManagerTheme.colors.primaryText,
                    disabledDayContentColor = SubManagerTheme.colors.outline.copy(alpha = 0.5f),

                    todayContentColor = SubManagerTheme.colors.primaryText,
                    todayDateBorderColor = Color.Transparent,

                    selectedDayContainerColor = SubManagerTheme.colors.primaryText,
                    selectedDayContentColor = SubManagerTheme.colors.primaryBackground,
                    disabledSelectedDayContainerColor = SubManagerTheme.colors.outline,
                    disabledSelectedDayContentColor = SubManagerTheme.colors.secondaryBackground,
                    yearContentColor = SubManagerTheme.colors.secondaryText,
                    currentYearContentColor = SubManagerTheme.colors.primaryText,
                    selectedYearContainerColor = SubManagerTheme.colors.primaryText,
                    selectedYearContentColor = SubManagerTheme.colors.primaryBackground,
                    dividerColor = SubManagerTheme.colors.outline.copy(alpha = 0.5f)
                )
            )
        }
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "결제한 날짜 수정",
            style = SubManagerTheme.typography.h3SemiBold,
            color = SubManagerTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(SubManagerTheme.colors.secondaryBackground, RoundedCornerShape(12.dp))
                .border(1.dp, SubManagerTheme.colors.primaryText, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable { showDatePicker = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = formatDate(selectedDate),
                style = SubManagerTheme.typography.b1Regular,
                color = SubManagerTheme.colors.primaryText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentDateSectionPreview() {
    SubManagerTheme {
        PaymentDateSection(
            selectedDate = 0,
            onDateChanged = {}
        )
    }
}