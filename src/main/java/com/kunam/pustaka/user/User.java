package com.kunam.pustaka.user;

import com.kunam.pustaka.book.Book;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @Nullable
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;


    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

}