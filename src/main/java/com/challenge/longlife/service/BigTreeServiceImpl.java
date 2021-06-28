package com.challenge.longlife.service;

import com.challenge.longlife.domain.model.BigTree;
import com.challenge.longlife.domain.repository.BigTreeRepository;
import com.challenge.longlife.domain.service.BigTreeService;
import com.challenge.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class BigTreeServiceImpl implements BigTreeService {

    @Autowired
    private BigTreeRepository treeRepository;

    @Override
    public Page<BigTree> getAllTrees(Pageable pageable) {
        return treeRepository.findAll(pageable);
    }

    @Override
    public BigTree getTreeById(Long treeId) {
        return treeRepository.findById(treeId).orElseThrow(
                () -> new ResourceNotFoundException("Tree", "Id", treeId)
        );
    }

    @Override
    public BigTree createTree(BigTree tree) {

        var date = new Date();
        long diffInMillies = Math.abs(date.getTime() - tree.getBornAt().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if(diff < 18250)
            throw new ResourceNotFoundException("Age must be bigger than 50");
        else
            return treeRepository.save(tree);
    }

    @Override
    public BigTree updateTree(Long treeId, BigTree treeDetails) {
        BigTree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tree", "Id", treeId));
        tree.setUsername(treeDetails.getUsername());
        tree.setEmail(treeDetails.getEmail());
        tree.setFirstName(treeDetails.getFirstName());
        tree.setLastName(treeDetails.getLastName());
        tree.setGender(treeDetails.getGender());
        tree.setBornAt(treeDetails.getBornAt());
        return treeRepository.save(tree);
    }

    @Override
    public ResponseEntity<?> deleteTree(Long treeId) {
        BigTree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tree", "Id", treeId));
        treeRepository.delete(tree);
        return ResponseEntity.ok().build();
    }
}
