package test;

import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMathematicsTest {
    //Tester le premier cas ou la matrice est de dimension 2x2
    @Test
    void determinant() throws NoSquareException {
       double[][] data = { {1,2},{2,1} };
       Matrix matrix = new Matrix(data);
       assertEquals(new MatrixMathematics().determinant(matrix),-3);
    }
    //Tester le deuxieme cas ou la matrice est de dimension 1x1
    @Test
    void determinant2() throws NoSquareException {
        double[][] data = {{1}};
        Matrix matrix = new Matrix(data);
        assertEquals(new MatrixMathematics().determinant(matrix),1);

    }
    //Tester le troisieme cas ou la matrice n'est pas carree
    @Test
    void determinant3() throws NoSquareException{
        double[][] data = {{1,2,4},{3,2,1},{2,4,5}};
        Matrix matrix = new Matrix(data);
        assertEquals(new MatrixMathematics().determinant(matrix),12);
    }
    //Tester le quatrieme cas ou la matrice est de dimension > 2x2
    @Test
    void testExpectedException() {
        NoSquareException thrown = Assertions.assertThrows(NoSquareException.class, () -> {
            double[][] data = {{1,2,4},{1,2,4}};
            Matrix matrix = new Matrix(data);
            assertEquals(new MatrixMathematics().determinant(matrix),12);
        });
    }
}