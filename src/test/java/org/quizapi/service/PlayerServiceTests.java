package org.quizapi.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quizapi.domain.player.Player;
import org.quizapi.domain.player.PlayerDAO;
import org.quizapi.dto.DeleteDto;
import org.quizapi.dto.GetByIdDto;
import org.quizapi.dto.InsertDto;
import org.quizapi.dto.UpdateDto;
import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class DeleteTests {

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerService ps;

    @Test
    void testDeleteByIdSuccessfully() throws NotFoundIDException {
        int id = 1;
        doNothing().when(playerDAO).delete((long)id);
        assertDoesNotThrow(() -> ps.deleteById(new DeleteDto(id)));
        verify(playerDAO, times(1)).delete((long)id);
    }

    @Test
    void testDeleteByIdNotFound() throws NotFoundIDException {
        int id = 1;
        doThrow(new NotFoundIDException()).when(playerDAO).delete((long)id);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> ps.deleteById(new DeleteDto(id)));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testDeleteByIdInternalServerError() throws NotFoundIDException {
        int id = 1;
        doThrow(new RuntimeException()).when(playerDAO).delete((long)id);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> ps.deleteById(new DeleteDto(id)));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }
}

@ExtendWith(MockitoExtension.class)
class InsertTests {

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerService ps;

    @Test
    void testInsertSucessfully() throws ThisNameAlreadyExistsException, NotFoundIDException {
        InsertDto player = new InsertDto("Coralina", 93);
        doNothing().when(playerDAO).insert(player);
        assertDoesNotThrow(() -> ps.insert(player));
        verify(playerDAO, times(1)).insert(player);
    }

    @Test
    void testInsertThrowsThisNameAlreadyExistsException() throws ThisNameAlreadyExistsException, NotFoundIDException {
        InsertDto player = new InsertDto("Aristoldo", 93);
        doThrow(new ThisNameAlreadyExistsException()).when(playerDAO).insert(player);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> ps.insert(player));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}


@ExtendWith(MockitoExtension.class)
class UpdateTests {

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerService ps;

    private final PlayerService psNotMocked = new PlayerService();

    @Test
    void testUpdateSucessfullyWhenDataIsAccurate() throws NotFoundIDException {
        int score = 79;
        int id = 4;
        UpdateDto player = new UpdateDto((long) id, score);
        doNothing().when(playerDAO).update(player);
        assertDoesNotThrow(() -> ps.updateById(player));
        verify(playerDAO, times(1)).update(player);
    }

    @Test
    void testUpdateThrowsNotFoundException() throws NotFoundIDException {
        int score = 79;
        int id = 1;
        UpdateDto player = new UpdateDto((long) id, score);
        doThrow(new NotFoundIDException()).when(playerDAO).update(player);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                ps.updateById(player));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testUpdateThrowsBadRequestErrorBecauseIdIsZero() {
        UpdateDto player = new UpdateDto(0L, 62);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                ps.updateById(player));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateThrowsBadRequestErrorBecauseScoreIsZero() {
        UpdateDto player = new UpdateDto(1L, 0);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                ps.updateById(player));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateWhenNewScoreIsHigher() throws NotFoundIDException {
        int score = 79;
        int id = 4;
        UpdateDto player = new UpdateDto((long) id, score);
        doNothing().when(playerDAO).update(player);
        assertDoesNotThrow(() -> ps.updateById(player));
        verify(playerDAO, times(1)).update(player);
    }

    @Test
    void testDontUpdateWhenNewScoreIsntHigher() {
        int score = 61;
        int id = 14;
        assertNotEquals(psNotMocked.getById(new GetByIdDto(id)).getScore(), score);
    }

    @Test
    void testDontUpdateWhenNewScoreIsEqual() {
        UpdateDto player = new UpdateDto(14L, 62);
        ps.updateById(player);
    }
}


@ExtendWith(MockitoExtension.class)
class ToListTests {

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerService ps;

    @Test
    void testToListSucessfully() throws NotFoundIDException {
        Player p1 = new Player();
        Player p2 = new Player();
        when(playerDAO.toList()).thenReturn(Arrays.asList(p1, p2));
        List<Player> players = ps.toList();
        assertEquals(2, players.size());
        assertEquals(p1, players.get(0));
        assertEquals(p2, players.get(1));

    }

    @Test
    public void testToListNotFound() throws NotFoundIDException {
        when(playerDAO.toList()).thenThrow(new NotFoundIDException());
        assertThrows(
                ResponseStatusException.class, ps::toList,
                "Expected toList() to throw, but it didn't");
    }
}


@ExtendWith(MockitoExtension.class)
class GetByIdTests{

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerService ps;

    @Test
    public void testGetByIdSuccessfully() throws Exception {
        int id = 1;
        when(playerDAO.searchById((long)id)).thenReturn(new Player());
        assertDoesNotThrow(() -> ps.getById(new GetByIdDto(id)));
        verify(playerDAO, times(1)).searchById((long)id);
    }

    @Test
    public void testGetByIdThrowsNotFoundException() throws Exception {
        int id = 1;
        doThrow(new NotFoundIDException()).when(playerDAO).searchById((long)id);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                ps.getById(new GetByIdDto(id)));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testGetByIdThrowsSimpleException() throws Exception {
        int id = 1;
        doThrow(new Exception()).when(playerDAO).searchById((long)id);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                ps.getById(new GetByIdDto(id)));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }

}
