import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    public Matrix matrix ;
    Double determinant;
    @Given("I have a square matrix")
    public void iHaveASquareMatrixNumberOfRowsColumns(DataTable table) {
        List<List<Double>> rows = table.asLists(double.class);
        double [][] finalTable = new double[3][3] ;
        int i=0,j=0;
        for (List<Double> columns : rows) {
            for(Double currentValue : columns){
                finalTable[i][j] = currentValue.doubleValue();
                j++;
            }
            j=0;
            i++;
        }
        this.matrix = new Matrix(finalTable) ;
    }

    @When("I calculate determinant")
    public void iCalculateDeterminant() throws NoSquareException {
        this.determinant =  MatrixMathematics.determinant(matrix);
    }

    @Then("I should have {double}")
    public void iShouldHave(double arg0) {
        assertEquals(arg0,this.determinant);
    }
}




