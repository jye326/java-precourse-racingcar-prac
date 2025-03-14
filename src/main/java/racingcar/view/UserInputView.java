package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import racingcar.util.ERROR_MESSAGE;
import racingcar.util.REGEX_PATTERN;

public class UserInputView {

    // 1. 차 이름 입력 받기
    // 2. 명단 제출
    public ArrayList<String> readCarNames() {
        ArrayList<String> carNames = new ArrayList<>();
        try {
            carNames = validateCarNames(Console.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return carNames;
    }

    private ArrayList<String> validateCarNames(String inputString) throws IOException {
        Pattern pattern = Pattern.compile(REGEX_PATTERN.CAR_NAMES_PATTERN.getRegexPattern());
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return splitCarNames(inputString);
        }
        throw new IllegalArgumentException(ERROR_MESSAGE.INPUT_FORMAT_ERROR.toString());
    }

    private ArrayList<String> splitCarNames(String inputString) {
        return new ArrayList<>(Arrays.asList(inputString.split(",")));
    }

    public int readTryTimes() {
        String input = Console.readLine();
        if (validateOnlyNumber(input)) {
            return Integer.parseInt(input);
        }
        throw new IllegalArgumentException(ERROR_MESSAGE.INPUT_FORMAT_ERROR.toString());
    }

    private boolean validateOnlyNumber(String inputString) {
        Pattern pattern = Pattern.compile(REGEX_PATTERN.ONLY_NUMBER.getRegexPattern());
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    // 1. 사용자 입력 읽기
    // 2. 정규식 패턴을 적용한 validation (정규식 수정 필요)
    // 3. 패턴 불일치 시 오류 메시지 출력 및 재입력
    public String readPurchaseLine() {
        String ret;
        while (true) {
            try {
                ret = validatePurchase(Console.readLine());
                // 우테코 지원 Console
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return ret;
    }

    // 정규식 사용한 입력 검증
    private String validatePurchase(String inputString) {
        Pattern pattern = Pattern.compile(REGEX_PATTERN.PURCHASE_VALIDATE_PATTERN.toString());
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return inputString;
        }
        throw new IllegalArgumentException(ERROR_MESSAGE.INPUT_FORMAT_ERROR.toString());
    }
}