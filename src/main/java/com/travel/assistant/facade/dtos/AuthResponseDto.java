package com.travel.assistant.facade.dtos;

public class AuthResponseDto {
	    public String type;
	    public String username;
	    public String application_name;
	    public String client_id;
	    public String token_type;
	    public String access_token;
	    public String expires_in; 
	    public String state;
	    public String scope;
		@Override
		public String toString() {
			return "AuthResponseDto [type=" + type + ", username=" + username + ", application_name=" + application_name
					+ ", client_id=" + client_id + ", token_type=" + token_type + ", access_token=" + access_token
					+ ", expires_in=" + expires_in + ", state=" + state + ", scope=" + scope + "]";
		} 

}
