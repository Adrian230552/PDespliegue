package com.challenge.longlife;

import com.challenge.longlife.domain.model.BigTree;
import com.challenge.longlife.domain.repository.BigTreeRepository;
import com.challenge.longlife.domain.service.BigTreeService;
import com.challenge.longlife.exception.ResourceNotFoundException;
import com.challenge.longlife.service.BigTreeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BigTreeServiceImplTests {

    @MockBean
    private BigTreeRepository treeRepository;
    @Autowired
    private BigTreeService treeService;

    @TestConfiguration
    static class BigTreeServiceImplTestConfiguration {
        @Bean
        public BigTreeService postService() {
            return new BigTreeServiceImpl();
        }
    }

    @Test
    @DisplayName("when createTree With Invalid Age Then Returns Exception")
    public void whenCreateTreeWithInvalidAgeThenReturnsException() {

        // Arrange
        Date date = new Date();
        BigTree tree = new BigTree();
        tree.setId(1L);
        tree.setGender("Male");
        tree.setUsername("marthaa");
        tree.setLastName("Smith");
        tree.setEmail("a@a.com");
        tree.setFirstName("Martha");
        tree.setBornAt(date);
        tree.setCreatedAt(date);
        tree.setUpdatedAt(date);

        when(treeRepository.save(tree))
                .thenReturn(tree);

        String expectedMessage = "Age must be bigger than 50";

        // Act
        Throwable exception = catchThrowable(() -> {
            BigTree foundTree = treeService.createTree(tree);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }


}
