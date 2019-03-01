package ua.com.sliusar.validators.impl;

import ua.com.sliusar.exceptions.BusinessException;
import ua.com.sliusar.validators.ValidationService;

/**
 * Class ValidationServiceImp
 *
 * @author create by ivanslusar
 * 2/15/19
 * @project MyLuxoftProject
 */
public class ValidationServiceImp implements ValidationService {
    @Override
    public void validateAge(int age) throws BusinessException {
        if (age <0 || age > 200){
            throw new BusinessException("Incorrect age");
        }
    }

    @Override
    public void validateEmail(String inputValue) throws BusinessException {
        if (!inputValue.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
            throw new BusinessException("Incorrect email");
        }
    }

    @Override
    public void validatePhone(String inputValue) throws BusinessException {
        if (!inputValue.matches("(\\+38)?(097|050)[0-9]{7}")){
            throw new BusinessException("Incorrect phone number");
        }
    }

    @Override
    public void validateBigВecimal(String inputValue) throws BusinessException {
        if (!inputValue.matches("-?+\\d+(\\.0*)?")){
            throw new BusinessException("Incorrect BigDecimal format");
        }
    }
}
