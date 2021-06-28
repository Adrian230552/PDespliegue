package com.challenge.longlife.controller;

import com.challenge.longlife.domain.model.GreenLeaf;
import com.challenge.longlife.domain.service.GreenLeafService;
import com.challenge.longlife.resource.GreenLeafResource;
import com.challenge.longlife.resource.SaveGreenLeafResource;
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
public class TreeLeavesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GreenLeafService leafService;

    @Operation(summary = "Get Tree Leaves", description = "Get All Tree Leaves by ID and Pages", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Tree Leaves returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/trees/{treeId}/leaves")
    public Page<GreenLeafResource> getAllLeavesByTreeId(@PathVariable Long treeId, Pageable pageable) {
        Page<GreenLeaf> leavesPage = leafService.getAllLeavesByTreeId(treeId, pageable);
        List<GreenLeafResource> resources = leavesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Leaf By ID", description = "Get Leaf by ID", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/trees/{treeId}/leaves/{leafId}")
    public GreenLeafResource getLeafById(
            @PathVariable Long treeId,
            @PathVariable Long leafId) {
        return convertToResource(leafService.getLeafById(treeId, leafId));
    }

    @Operation(summary = "Create a Leaf", description = "Create a Leaf", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf created", content = @Content(mediaType = "application/json"))

    })
    @PostMapping("/trees/{treeId}/leaves")
    public GreenLeafResource createLeaf(@PathVariable Long treeId, @Valid @RequestBody SaveGreenLeafResource resource) {
        GreenLeaf leaf = convertToEntity(resource);
        return convertToResource(leafService.createLeaf(treeId, leaf));
    }

    @Operation(summary = "Update a Leaf", description = "Update a Leaf", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf updated", content = @Content(mediaType = "application/json"))

    })
    @PutMapping("/trees/{treeId}/leaves/{leafId}")
    public GreenLeafResource updateLeaf(@PathVariable Long treeId, @PathVariable Long leafId, @RequestBody SaveGreenLeafResource resource) {
        GreenLeaf leaf = convertToEntity(resource);
        return convertToResource(leafService.updateLeaf(treeId, leafId, leaf));
    }

    @Operation(summary = "Delete a Leaf", description = "Delete a Leaf", tags = {"tree-leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leaf deleted", content = @Content(mediaType = "application/json"))

    })
    @DeleteMapping("/trees/{treeId}/leaves/{leafId}")
    public ResponseEntity<?> deleteLeaf(@PathVariable Long treeId, @PathVariable Long leafId) {
        return leafService.deleteLeaf(treeId, leafId);
    }

    private GreenLeaf convertToEntity(SaveGreenLeafResource resource) {
        return mapper.map(resource, GreenLeaf.class);
    }

    private GreenLeafResource convertToResource(GreenLeaf entity) {
        return mapper.map(entity, GreenLeafResource.class);
    }
}
