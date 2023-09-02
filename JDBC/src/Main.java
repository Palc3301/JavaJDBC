import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = Conexao.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Create Table
            String createTableQuery = "CREATE TABLE Produto ("
                    + "codProduto int PRIMARY KEY,"
                    + "produto varchar(50));";
            statement.executeUpdate(createTableQuery);
            
            // Inserts
            String insertDataQuery1 = "INSERT INTO Produto (codProduto, produto) VALUES (1, 'Arroz')";
            String insertDataQuery2 = "INSERT INTO Produto (codProduto, produto) VALUES (2, 'Feijão')";
            String insertDataQuery3 = "INSERT INTO Produto (codProduto, produto) VALUES (3, 'Macarrão')";
            statement.executeUpdate(insertDataQuery1);
            statement.executeUpdate(insertDataQuery2);
            statement.executeUpdate(insertDataQuery3);
            
            System.out.println("Tabela e dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
