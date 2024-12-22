package com.batu.grpc.data.author.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "address")
public class AddressEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private Integer postalCode;

    @OneToOne(mappedBy = "address")
    private AuthorEntity author;
}
