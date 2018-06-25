package org.sber.www.docophila.ru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "DOCUMENT")
public class Document {
    //@Transient
    //private static final Long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_seq_gen")
    //@SequenceGenerator(name = "document_seq_gen", sequenceName = "seq_document_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "filename")
    private String filename;

    public Document(@NotNull @NotEmpty String filename, byte[] file, Boolean inprogress) {
        this.filename = filename;
        this.file = file;
        this.inprogress = inprogress;
    }

    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;

    @Column(name = "file")
    private byte[] file;

    @Column(name = "inprogress")
    private Boolean inprogress;

}