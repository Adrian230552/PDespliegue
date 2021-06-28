package com.challenge.longlife.domain.service;

import com.challenge.longlife.domain.model.BigTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BigTreeService {
    Page<BigTree> getAllTrees(Pageable pageable);
    BigTree getTreeById(Long treeId);
    BigTree createTree(BigTree tree);
    BigTree updateTree(Long treeId, BigTree treeDetails);
    ResponseEntity<?> deleteTree(Long treeId);
}
