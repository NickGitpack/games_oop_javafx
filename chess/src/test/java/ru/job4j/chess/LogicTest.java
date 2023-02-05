package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.C1, Cell.G4);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not way by diagonal from C1 to G4");
    }

    @Test
    public void whenMoveThenNoFiguresOnWay()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack1 = new BishopBlack(Cell.C1);
        BishopBlack bishopBlack2 = new BishopBlack(Cell.E3);
        logic.add(bishopBlack1);
        logic.add(bishopBlack2);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C1, Cell.G5);
        });
        assertThat(exception.getMessage()).isEqualTo("Mistake");

    }
/*
    @Test
    public void whenMove() throws OccupiedCellException, FigureNotFoundException {
        Logic logic = new Logic();
        BishopBlack bishopBlack1 = new BishopBlack(Cell.C1);
        BishopBlack bishopBlack2 = new BishopBlack(Cell.C3);
        logic.add(bishopBlack1);
        logic.add(bishopBlack2);
        logic.move(Cell.C1, Cell.G5);
        assertThat(bishopBlack1.position()).isEqualTo(Cell.C1);
    }

 */
}