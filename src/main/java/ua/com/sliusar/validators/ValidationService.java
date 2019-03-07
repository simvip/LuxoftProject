package ua.com.sliusar.validators;

import ua.com.sliusar.exceptions.BusinessException;

/**
 * Interface ValidationService
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public interface ValidationService {
    void validateAge(int age) throws BusinessException;

    void validatePhone(String inputValue) throws BusinessException;

    void validateEmail(String inputValue) throws BusinessException;

    void validateBigDecimal(String inputValue) throws BusinessException;
}
