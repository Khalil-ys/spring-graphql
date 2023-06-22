package az.springgraphql.controller;

import az.springgraphql.model.Role;
import az.springgraphql.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@AutoConfigureTestDatabase
@AutoConfigureGraphQlTester
@RequiredArgsConstructor
class UserControllerTest {

    private final GraphQlTester graphQlTester;

    @BeforeEach
    void setUp() {
        createUser(new User("Ali", "ali@mail.com", Role.USER));
        createUser(new User("Veli", "veli@mail.com", Role.ADMIN));
    }

    @Test
    void when_getAllUsers_should_return_userList() {
        // language=graphql
        String query = """
                query {
                getAllUsers {
                id
                name
                role
                created
                updated             
                   }
                }
                """;
        graphQlTester.document(query)
                .execute()
                .path("getAllUsers")
                .entityList(User.class)
                .hasSize(2);
    }

    @Test
    void createUser_should_createNewUserAndReturnUser() {

        String mutation = """
                mutation {
                createUser(userRequest: {name: "ALi",mail: "mail",role: ADMIN}) {
                  id
                  name
                  role
                  created
                  updated
                 }
                }
                """;
        graphQlTester.document(mutation)
                .execute()
                .path("createUser").entity(User.class);
    }

    void createUser(User user) {
        String mutation = """
                mutation {
                createUser(userRequest: {name: "%s",mail: "%s",role: %s}) {
                  id
                  name
                  role
                  created
                  updated
                 }
                }
                """
                .formatted(user.getName(), user.getMail(), user.getRole());
        graphQlTester.document(mutation).execute().path("getAllUsers");
    }

}