package com.challenge.longlife.service;

import com.challenge.longlife.domain.model.GreenLeaf;
import com.challenge.longlife.domain.repository.BigTreeRepository;
import com.challenge.longlife.domain.repository.GreenLeafRepository;
import com.challenge.longlife.domain.service.GreenLeafService;
import com.challenge.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreenLeafServiceImpl implements GreenLeafService {

    @Autowired
    private GreenLeafRepository leafRepository;

    @Autowired
    private BigTreeRepository treeRepository;

    @Override
    public Page<GreenLeaf> getAllLeavesByTreeId(Long treeId, Pageable pageable) {
        return treeRepository.findById(treeId)
                .map(tree -> {
                    List<GreenLeaf> leaves = tree.getGreenLeaves();
                    int leavesCount = leaves.size();
                    return new PageImpl<>(leaves, pageable, leavesCount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tree", "Id", treeId));
    }

    @Override
    public GreenLeaf getLeafById(Long treeId, Long leafId) {
        return leafRepository.findById(leafId)
                .orElseThrow(() -> new ResourceNotFoundException("Leaf", "Id", leafId));
    }

    @Override
    public GreenLeaf createLeaf(Long treeId, GreenLeaf leaf) {

        var foundTree = treeRepository.findById(treeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tree", "Id", treeId));

        var foundLeaf = foundTree.getGreenLeaves().stream()
                .filter(leaf1 -> leaf.getTitle().equals(leaf1.getTitle()))
                .findFirst()
                .orElse(null);

        if (foundLeaf == null) {
            leaf.setBigTree(foundTree);
            foundTree.getGreenLeaves().add(leaf);
            return leafRepository.save(leaf);
        }
        else{
            throw new ResourceNotFoundException("Already exists leaf with same title");
        }
    }

    @Override
    public GreenLeaf updateLeaf(Long treeId, Long leafId, GreenLeaf leafDetails) {
        var foundTree = treeRepository.findById(treeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tree", "Id", treeId));

        return leafRepository.findById(leafId)
                .map(leaf -> {
                    leaf.setTitle(leafDetails.getTitle());
                    leaf.setScenario(leafDetails.getScenario());
                    leaf.setTip(leafDetails.getTip());
                    return leafRepository.save(leaf);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Leaf", "Id", leafId));
    }

    @Override
    public ResponseEntity<?> deleteLeaf(Long treeId, Long leafId) {
        var foundTree = treeRepository.findById(treeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tree", "Id", treeId));

        var foundLeaf = foundTree.getGreenLeaves().stream()
                .filter(leaf -> leafId.equals(leaf.getId()))
                .findFirst()
                .orElse(null);

        foundTree.getGreenLeaves().remove(foundLeaf);

        return leafRepository.findById(leafId)
                .map(leaf -> {
                    leafRepository.delete(leaf);
                    return (ResponseEntity.ok().build());
                })
                .orElseThrow(() -> new ResourceNotFoundException("Leaf", "Id", leafId));
    }
}
