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
    private String city;
    private String street;
    private Integer postalCode;

    @OneToOne(mappedBy = "address")
    private AuthorEntity author;
}
