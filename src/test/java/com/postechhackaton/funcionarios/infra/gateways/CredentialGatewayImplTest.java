package com.postechhackaton.funcionarios.infra.gateways;

import com.postechhackaton.funcionarios.business.entities.FuncionarioEntity;
import com.postechhackaton.funcionarios.business.exceptions.CriacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialGatewayImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CredentialGatewayImpl credentialGateway;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(credentialGateway, "keycloakUrl", "http://localhost:1111");
        ReflectionTestUtils.setField(credentialGateway, "realm", "realm");
        ReflectionTestUtils.setField(credentialGateway, "realmHackaton", "realmHackaton");
        ReflectionTestUtils.setField(credentialGateway, "clientId", "clientId");
        ReflectionTestUtils.setField(credentialGateway, "adminUsername", "adminUsername");
        ReflectionTestUtils.setField(credentialGateway, "adminPassword", "adminPassword");
        ReflectionTestUtils.setField(credentialGateway, "restTemplate", restTemplate);
    }
    @Test
    void cadastrar_ShouldCreateUserSuccessfully() {

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setUsername("user1");
        funcionario.setEmail("user1@example.com");
        funcionario.setCpf("123456789");
        funcionario.setRoles(Collections.singletonList("role1"));

        when(restTemplate.postForEntity(anyString(), any(), eq(CredentialGatewayImpl.AccessTokenResponse.class)))
                .thenReturn(new ResponseEntity<>(createToken(), HttpStatus.OK));
        when(restTemplate.postForEntity(anyString(), any(), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        credentialGateway.cadastrar(funcionario);

        verify(restTemplate, times(1)).postForEntity(anyString(), any(), eq(CredentialGatewayImpl.AccessTokenResponse.class));
        verify(restTemplate, times(1)).postForEntity(anyString(), any(), eq(Void.class));
    }

    @Test
    void cadastrar_ShouldThrowCriacaoException_WhenCreationFails() {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setUsername("user1");
        funcionario.setEmail("user1@example.com");
        funcionario.setCpf("123456789");
        funcionario.setRoles(Collections.singletonList("role1"));

        when(restTemplate.postForEntity(anyString(), any(), eq(CredentialGatewayImpl.AccessTokenResponse.class)))
                .thenReturn(new ResponseEntity<>(createToken(), HttpStatus.OK));
        when(restTemplate.postForEntity(anyString(), any(), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(CriacaoException.class, () -> credentialGateway.cadastrar(funcionario));
    }

    private CredentialGatewayImpl.AccessTokenResponse createToken() {
        CredentialGatewayImpl.AccessTokenResponse token = new CredentialGatewayImpl.AccessTokenResponse();
        token.setAccessToken("1234");
        return token;
    }

}
