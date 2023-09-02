package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessoPostgreSQL {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/Loja";
        String username = "postgres";
        String password = "1234";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Conexão estabelecida com sucesso!");

            // Criar uma declaração
            Statement statement = connection.createStatement();

            // Consulta SQL
            String sql = "SELECT * FROM Produto;";
            ResultSet resultSet = statement.executeQuery(sql);

            // Processar o resultado
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
