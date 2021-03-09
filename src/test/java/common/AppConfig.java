package common;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface AppConfig extends Config {
    @Key("correctLogin")
    String correctLogin();

    @Key("correctPassword")
    String correctPassword();

}