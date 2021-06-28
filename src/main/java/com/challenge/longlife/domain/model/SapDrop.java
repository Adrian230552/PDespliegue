package com.challenge.longlife.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "drops")
public class SapDrop extends  AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tree_id", nullable = false)
    @JsonIgnore
    private BigTree bigTree;

    public SapDrop() {
    }

    public SapDrop(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public SapDrop setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SapDrop setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public SapDrop setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SapDrop setContent(String content) {
        this.content = content;
        return this;
    }

    public BigTree getBigTree() {
        return bigTree;
    }

    public SapDrop setBigTree(BigTree bigTree) {
        this.bigTree = bigTree;
        return this;
    }
}
