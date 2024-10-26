package com.dm.planetradehub.repository;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class TypeRepositoryTest {

    @Mock
    private TypeRepository typeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}