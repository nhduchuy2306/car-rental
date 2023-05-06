package com.example.demo.service;

import lombok.Data;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthenticationMethod;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegistration {
    private String registrationId;	
	private String clientId;	
	private String clientSecret;
	private String redirectUri;	
	private Set<String> scopes;	
	public ProviderDetails providerDetails;
	private String clientName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ProviderDetails {
		private String authorizationUri;	
		private String tokenUri;	
		public UserInfoEndpoint userInfoEndpoint;
		private String jwkSetUri;	
		private String issuerUri;	
        private Map<String, Object> configurationMetadata;  

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class UserInfoEndpoint {
			private String uri; 
			private String userNameAttributeName;	
		}
    }
}
