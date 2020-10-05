package observe_point.quiz;

import io.qameta.allure.Step;

public final class LogUtil {

    private LogUtil() {
    }

    @Step("{0}")
    public static void log(final String message){
        // intentionally empty
    }
}
