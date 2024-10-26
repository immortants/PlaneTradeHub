package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserByEmail_WhenFound() {
        // Given
        String email = "test@example.com";
        User expectedUser = new User();

        when(userRepository.getUserByEmail(email))
                .thenReturn(expectedUser);

        // When
        User user = userRepository.getUserByEmail(email);

        // Then
        assertNotNull(user);
        assertEquals(expectedUser, user);
        verify(userRepository, times(1)).getUserByEmail(email);
    }

    @Test
    void getUserByEmail_WhenNotFound() {
        // Given
        String email = "nonexistent@example.com";

        when(userRepository.getUserByEmail(email))
                .thenReturn(null);

        // When
        User user = userRepository.getUserByEmail(email);

        // Then
        assertNull(user);
        verify(userRepository, times(1)).getUserByEmail(email);
    }

    @Test
    void deleteUserByEmail() {
        // Given
        String email = "test@example.com";

        // When
        userRepository.deleteUserByEmail(email);

        // Then
        verify(userRepository, times(1)).deleteUserByEmail(email);
    }
}