package org.sber.www.docophila.ru.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DOCUMENT")
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String filename;


    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private byte[] file;

    @NotNull
    private Boolean inprogress;

}