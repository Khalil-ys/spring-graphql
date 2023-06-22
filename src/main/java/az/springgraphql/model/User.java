package az.springgraphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "istifadeci")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User extends BaseEntity{

    String name;
    String mail;

    @Enumerated(EnumType.STRING)
    Role role;
}
