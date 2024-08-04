package com.kunam.pustaka.category;

import com.kunam.pustaka.book.Book;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@EqualsAndHashCode
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;

        @Column(unique = true, nullable = false)
        String name;

        @Column(nullable = true)
        String description;

        @CreationTimestamp
        @Column(name = "created_at")
        LocalDateTime createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        LocalDate updatedAt;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Book> books;
}
