package observe_point.quiz;

import com.stubbornjava.common.Configs;
import com.stubbornjava.common.Env;
import com.typesafe.config.Config;

public class CoreConstant {

    private static final Config conf;

    static {
        conf = Configs.newBuilder()
                .withResource("project.properties")
                .withResource("project." + Env.get().getName() + ".properties")
                .build();
    }

    public static final String RAPID_API_ENDPOINT = conf.getString("endpoint");
    public static final String RAPID_HOST_KEY = conf.getString("rapidhost.key");
    public static final String RAPID_HOST_VALUE = conf.getString("rapidhost.value");
    public static final String RAPID_API_KEY = conf.getString("rapidapi.key");
    public static final String RAPID_API_VALUE = conf.getString("rapidapi.value");
}
