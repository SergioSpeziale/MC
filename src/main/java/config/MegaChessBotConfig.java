package config;

import java.net.URI;

public class MegaChessBotConfig {	
	static String authToken = "9549bb0c-b8dc-43b2-849e-59d7ea2a2665";	
	static String url = "ws://megachess.herokuapp.com/service?authtoken=";	
	static URI uri = URI.create("ws://megachess.herokuapp.com/service?authtoken=" + authToken);

	public static String getAuthToken() {
		return authToken;
	}

	public static String getUrl() {
		return url;
	}

	public static URI getUri() {
		return uri;
	}
}
