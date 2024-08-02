package com.kunam.pustaka.category;

import com.kunam.pustaka.book.Book;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
        Integer id;

        @Column(unique = true, nullable = false)
        String name;

        @Column(nullable = true)
        String description;

        @Column(name = "created_at")
        LocalDateTime createdAt;

        @Column(name = "updated_at")
        LocalDateTime updatedAt;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Book> books;
}
