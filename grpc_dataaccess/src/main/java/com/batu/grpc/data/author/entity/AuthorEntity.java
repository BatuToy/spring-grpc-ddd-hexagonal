package com.batu.grpc.data.author.entity;

import com.batu.common.domain.valueobject.Gender;
import com.batu.grpc.data.book.entity.BookEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "author")
public class AuthorEntity {

    @Id
    private UUID id;
    private UUID trackingId;
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private ContactEntity contact;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;
}

