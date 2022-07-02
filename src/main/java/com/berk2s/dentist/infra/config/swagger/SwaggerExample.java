package com.berk2s.dentist.infra.config.swagger;

import org.springframework.context.annotation.Profile;

@Profile({"test", "local"})
public final class SwaggerExample {
    public static final String LOGIN_SUCCESSFUL = "{\"access_token\":\"eyJraWQiOiIxNjU2NjE4MTE1IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJiODI2OWU0Yi03MWExLTQ2ZGUtOGVmOS05NTgxMWU2OWNiMDAiLCJhdWQiOiJodHRwOlwvXC9kZW50aXN0YXBwLmNvbSIsIm5iZiI6MTY1NjYxODExNywicm9sZXMiOlsiU1VYV1BFYk9LYSJdLCJpc3MiOiJodHRwOlwvXC9kZW50aXN0YXBwLmNvbSIsImV4cCI6MTY1NjYxODQxNywiaWF0IjoxNjU2NjE4MTE3LCJqdGkiOiI5NmNiODU5Zi0xZWIyLTRjMmUtODdjYy0wNGFlZDkwMzU2OTAifQ.bypGFmnLQDNrTCknwKVnJ8DMgdaWN1wmuGmPYNhM0oP2NmpwgReQpFe3U5D7NHsomuiTADfY4QxgttLkhL3hmlOe6odHdvxOjxnUDkT616KfA3OANE9IFF-44yeZ65OCZBxNtGnsPQ5Zf7I0eZfqJoViFvzSQz57MQg3BkisO-1_a-n2xwQGcNyTRmGFS2wmAiUr2bvkxfCwtq_F4QnH-Ms7OnNTUjG59BvpVky9AqUw2LJzACucUXZPgaQC-VK9BYl6BuOzA0bLISSXGTkuPX_SeS5z7iL-OdthvI3bdmLK8Mj_HCMWVxypKzREF2DbXBh-PJCzWDRQbSocGuQMnQ\",\"refresh_token\":\"hDxMbfJCoEgvzVRYiqPvQTVyYArwaqrebUhSHkYacsWXAjcD\",\"expires_in\":41}\n";
    public static final String INSUFFICIENT_AUTHORITY_ERROR = "{\"error\":\"invalid_grant\",\"error_description\":\"Insufficient authority\",\"code\":\"authentication.insufficientAuthority\",\"details\":[]}";
    public static final String INVALID_CREDENTIALS = "{\"error\":\"invalid_grant\",\"error_description\":\"Invalid credentials\",\"code\":\"authentication.invalidCredentials\",\"details\":[]}";
    public static final String INVALID_LOGIN_REQUEST_BODY = "{\"error\":\"invalid_request\",\"error_description\":\"Invalid request\",\"code\":\"request.invalid\",\"details\":[\"Username must not be empty\",\"Scopes must not be empty\",\"Password must not be empty\"]}";
    public static final String INVALID_USERNAME_ERROR = "{\"error\":\"invalid_grant\",\"error_description\":\"Invalid credentials\",\"code\":\"authentication.invalidCredentials\",\"details\":[]}";

    public static final String CREATED_ROLE_RESPONSE = "{\"id\":1,\"roleName\":\"iCQvrhKd\",\"roleDescription\":\"IETDSSJwlQzBYcEJFzvh\",\"authorities\":[\"SMRZP\"],\"createdAt\":\"2022-07-02T17:31:32.950595\",\"lastModifiedAt\":\"2022-07-02T17:31:32.950595\"}\n";
    public static final String INVALID_CREATE_ROLE_REQUEST_ERROR = "{\"error\":\"invalid_request\",\"error_description\":\"Invalid request\",\"code\":\"request.invalid\",\"details\":[\"createRole.roleName.empty\",\"createRole.roleDescription.empty\"]}\n";
    public static final String SIZE_CREATE_ROLE_REQUEST_ERROR = "{\"error\":\"invalid_request\",\"error_description\":\"Invalid request\",\"code\":\"request.invalid\",\"details\":[\"createRole.roleName.notInRange\",\"createRole.roleDescription.notInRange\"]}\n";
    public static final String ROLE_NAME_ALREADY_TAKEN_ERROR = "{\"error\":\"invalid_request\",\"error_description\":\"Role name already taken\",\"code\":\"createRole.roleName.exists\",\"details\":[]}";

    private SwaggerExample(){}
}
