package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@XmlRootElement(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private Date birth;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JacksonXmlElementWrapper(localName = "phones")
//    @JacksonXmlProperty(localName = "phone")
    private List<Phone> phones;
}
