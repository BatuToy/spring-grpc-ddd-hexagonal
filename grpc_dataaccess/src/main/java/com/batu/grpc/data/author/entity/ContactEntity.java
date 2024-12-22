package com.batu.grpc.data.author.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "contact")
public class ContactEntity {

    @Id
    private UUID id;
    private String phoneNumber;
    private String email;

    @OneToOne(mappedBy = "contact")
    private AuthorEntity author;
}
