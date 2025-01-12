package com.sg.katabankaccountmanagement.domain;

import com.sg.katabankaccountmanagement.domain.enums.OperationType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OperationTest {


    @Test
    public void should_process_valid_amount() {

        // Given
        Operation operation = Operation.builder().id(1L).type(OperationType.DEPOSIT).date(LocalDateTime.now()).amount(new BigDecimal(750)).build();

        // When
        boolean isNegativeAmount = operation.isNegativeAmount();

        // Then
        assertFalse(isNegativeAmount);
    }

    @Test
    public void should_not_process_invalid_amount() {

        // Given
        Operation operation = Operation.builder().id(1L).type(OperationType.WITHDRAWAL).date(LocalDateTime.now()).amount(new BigDecimal(-100)).build();

        // When
        boolean negativeAmount = operation.isNegativeAmount();

        // Then
        assertTrue(negativeAmount);
    }

}
