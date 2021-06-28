package com.challenge.longlife.domain.service;

import com.challenge.longlife.domain.model.BigTree;
import com.challenge.longlife.domain.model.GreenLeaf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GreenLeafService {
    Page<GreenLeaf> getAllLeavesByTreeId(Long treeId, Pageable pageable);
    GreenLeaf getLeafById(Long treeId, Long leafId);
    GreenLeaf createLeaf(Long treeId, GreenLeaf leaf);
    GreenLeaf updateLeaf(Long treeId, Long leafId, GreenLeaf leafDetails);
    ResponseEntity<?> deleteLeaf(Long treeId, Long leafId);
}
