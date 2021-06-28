package com.challenge.longlife.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "leafs")
public class GreenLeaf extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String scenario;

    @NotNull
    private String tip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tree_id", nullable = false)
    @JsonIgnore
    private BigTree bigTree;

    public GreenLeaf() {
    }

    public GreenLeaf( @NotNull String title, @NotNull String scenario, @NotNull String tip) {
        this.title = title;
        this.scenario = scenario;
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public GreenLeaf setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GreenLeaf setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getScenario() {
        return scenario;
    }

    public GreenLeaf setScenario(String scenario) {
        this.scenario = scenario;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public GreenLeaf setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public BigTree getBigTree() {
        return bigTree;
    }

    public GreenLeaf setBigTree(BigTree bigTree) {
        this.bigTree = bigTree;
        return this;
    }

}
