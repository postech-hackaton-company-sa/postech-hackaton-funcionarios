package com.postechhackaton.funcionarios.infra.gateways;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.business.exceptions.CriacaoException;
import com.postechhackaton.funcionarios.domain.gateways.CredentialGateway;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class CredentialGatewayImpl implements CredentialGateway {

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm.master}")
    private String realm;

    @Value("${keycloak.realm.hackaton}")
    private String realmHackaton;

    @Value("${keycloak.client_id}")
    private String clientId;

    @Value("${keycloak.admin_username}")
    private String adminUsername;

    @Value("${keycloak.admin_password}")
    private String adminPassword;

    private final RestTemplate restTemplate;

    public CredentialGatewayImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void cadastrar(FuncionarioEntity funcionario) {
        String accessToken = getToken();

        createUser(funcionario, accessToken);
    }

    private String getToken() {
        String tokenUrl = keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", clientId);
        requestBody.add("username", adminUsername);
        requestBody.add("password", adminPassword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<AccessTokenResponse> responseEntity = restTemplate.postForEntity(tokenUrl, requestEntity, AccessTokenResponse.class);
        AccessTokenResponse response = responseEntity.getBody();
        if (response != null) {
            return response.getAccessToken();
        } else {
            throw new RuntimeException("Failed to obtain access token from Keycloak");
        }
    }

    private void createUser(FuncionarioEntity funcionario, String accessToken) {
        String createUserUrl = keycloakUrl + "/admin/realms/" + realmHackaton + "/users";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", funcionario.getUsername());
        requestBody.put("email", funcionario.getEmail());
        requestBody.put("enabled", true);

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", funcionario.getCpf());
        credentials.put("temporary", false);
        requestBody.put("credentials", new Object[]{credentials});

        requestBody.put("realmRoles", funcionario.getRoles());

        String authorizationHeader = "Bearer " + accessToken;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(createUserUrl, requestEntity, Void.class);

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new CriacaoException("Failed to create user in Keycloak. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Getter
    @Setter
    public static class AccessTokenResponse {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;
    }
}
