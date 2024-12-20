package com.batu.grpc.data.book.entity;

import com.batu.common.domain.valueobject.BookStatus;
import com.batu.grpc.data.author.entity.AuthorEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "book")
public class BookEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BookStatus bookStatus;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer pageSize;

    @Column(unique = true, nullable = false)
    private String skuCode;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Nullable
    private AuthorEntity author;

}
