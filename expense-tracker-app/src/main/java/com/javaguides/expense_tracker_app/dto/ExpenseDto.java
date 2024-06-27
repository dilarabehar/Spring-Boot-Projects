package com.javaguides.expense_tracker_app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(Long id,
                         BigDecimal amount,
                         LocalDate expenseDate,
                         CategoryDto categoryDto) {
}
