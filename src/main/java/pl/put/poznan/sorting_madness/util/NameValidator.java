package pl.put.poznan.sorting_madness.util;

import lombok.extern.slf4j.Slf4j;
import pl.put.poznan.sorting_madness.exception.WrongParameterException;
import pl.put.poznan.sorting_madness.logic.AlgorithmName;
import pl.put.poznan.sorting_madness.logic.InputType;

@Slf4j
public class NameValidator {

    public static AlgorithmName validateAlgorithmName(String algorithmAsString) throws WrongParameterException {
        try {
            return AlgorithmName.valueOf(algorithmAsString);
        } catch (Exception e) {
            var errorMessage = String.format("Wrong algorithm name: %s", algorithmAsString);
            log.error(errorMessage);
            throw new WrongParameterException(errorMessage);
        }
    }

    public static InputType validateInputType(String inputTypeAsString) throws WrongParameterException {
        try {
            return InputType.valueOf(inputTypeAsString);
        } catch (Exception e) {
            var errorMessage = String.format("Wrong input type: %s", inputTypeAsString);
            log.error(errorMessage);
            throw new WrongParameterException(errorMessage);
        }
    }
}
