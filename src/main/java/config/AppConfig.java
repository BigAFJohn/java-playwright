package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/general.properties"})
public interface AppConfig extends org.aeonbits.owner.Config {
    String baseUrl();
    String browser();
    int timeout();

    @Key("login.username")
    String loginUsername();
    @Key("login.invalidUser")
    String invalidUser();
    @Key("headless")
    boolean headless();

    @Key("login.password")
    String loginPassword();
}

