package net.purocodigo.backendcursojava.security;

import net.purocodigo.backendcursojava.SpringApplicationContext;

public class SecurityConstants {
    
    public static final long EXPIRATION_DATE = 864000000; // 10 DÍAS
    public static final String TOKEN_PREFIX = "Bearer "; 
    public static final String HEADER_STRING = "Authorization"; 
    public static final String SIGN_UP_URL = "/users"; 
    // public static final String TOKEN_SECRET = "m0qVlToSSurAzGzXFjzSNcN9uUMIMok8"; 
    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
