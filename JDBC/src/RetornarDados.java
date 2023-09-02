import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetornarDados {
    public static void main(String[] args) {
        try (Connection connection = Conexao.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Select data from table
            String selectDataQuery = "SELECT * FROM Produto;";
            ResultSet resultSet = statement.executeQuery(selectDataQuery);
            
            // Process the result set
            while (resultSet.next()) {
                int codProduto = resultSet.getInt("codProduto");
                String produto = resultSet.getString("produto");
                System.out.println("CodProduto: " + codProduto + ", Produto: " + produto);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
