package com.challenge.longlife.controller;

import com.challenge.longlife.domain.model.BigTree;
import com.challenge.longlife.domain.service.BigTreeService;
import com.challenge.longlife.resource.BigTreeResource;
import com.challenge.longlife.resource.SaveBigTreeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TreesController {

    @Autowired
    private BigTreeService treeService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Trees", description = "Get All Trees by Pages", tags = {"trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Trees returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/trees")
    public Page<BigTreeResource> getAllTrees(Pageable pageable) {
        Page<BigTree> postsPage = treeService.getAllTrees(pageable);
        List<BigTreeResource> resources = postsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Tree By ID", description = "Get Tree by ID", tags = {"trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tree returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/trees/{treeId}")
    public BigTreeResource getTreeById(
            @PathVariable Long treeId) {
        return convertToResource(treeService.getTreeById(treeId));
    }

    @Operation(summary = "Create a Tree", description = "Create a Tree", tags = {"trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tree created", content = @Content(mediaType = "application/json"))

    })
    @PostMapping("/trees")
    public BigTreeResource createTree(@Valid @RequestBody SaveBigTreeResource resource) {
        BigTree tree = convertToEntity(resource);
        return convertToResource(treeService.createTree(tree));
    }

    @Operation(summary = "Update a Tree", description = "Update a Tree", tags = {"trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tree updated", content = @Content(mediaType = "application/json"))

    })
    @PutMapping("/trees/{treeId}")
    public BigTreeResource updateTree(@PathVariable Long treeId, @RequestBody SaveBigTreeResource resource) {
        BigTree tree = convertToEntity(resource);
        return convertToResource(treeService.updateTree(treeId, tree));
    }

    @Operation(summary = "Delete a Tree", description = "Delete a Tree", tags = {"trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tree deleted", content = @Content(mediaType = "application/json"))

    })
    @DeleteMapping("/trees/{treeId}")
    public ResponseEntity<?> deleteTree(@PathVariable Long treeId) {
        return treeService.deleteTree(treeId);
    }

    private BigTree convertToEntity(SaveBigTreeResource resource) {
        return mapper.map(resource, BigTree.class);
    }

    private BigTreeResource convertToResource(BigTree entity) {
        return mapper.map(entity, BigTreeResource.class);
    }
}
