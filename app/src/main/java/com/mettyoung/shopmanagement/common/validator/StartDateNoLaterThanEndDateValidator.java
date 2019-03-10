package com.mettyoung.shopmanagement.common.validator;

import com.mettyoung.shopmanagement.common.model.DateRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartDateNoLaterThanEndDateValidator implements ConstraintValidator<StartDateNoLaterThanEndDate, DateRange> {

    @Override
    public boolean isValid(DateRange dateRange, ConstraintValidatorContext constraintValidatorContext) {
        if (dateRange.getStartDate() != null && dateRange.getEndDate() != null) {
            return dateRange.getStartDate().before(dateRange.getEndDate()) || dateRange.getStartDate().equals(dateRange.getEndDate());
        }
        return true;
    }
}
