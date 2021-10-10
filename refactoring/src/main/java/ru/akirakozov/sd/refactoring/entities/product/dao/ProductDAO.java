package ru.akirakozov.sd.refactoring.entities.product.dao;

import ru.akirakozov.sd.refactoring.DB.SQLDatabase;
import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO {
    private ResultSet executeQuery(String query) {
        try (Statement statement = SQLDatabase.getConnection().createStatement()) {
            return statement.executeQuery(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<ProductDTO> mapToDTO(ResultSet resultSet) throws SQLException {
        ArrayList<ProductDTO> resultList = new ArrayList<>();
        while (resultSet.next()) {
            resultList.add(new ProductDTO(
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            ));
        }
        return resultList;
    }

    public ArrayList<ProductDTO> selectAll() throws SQLException {
        String query = "SELECT * FROM PRODUCT";
        ResultSet resultSet = executeQuery(query);
        return mapToDTO(resultSet);
    }

    public ProductDTO getProductMaxPrice() throws SQLException {
        String query = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
        ResultSet resultSet = executeQuery(query);
        ArrayList<ProductDTO> listDTO = mapToDTO(resultSet);
        if (listDTO.isEmpty()) {
            return null;
        }
        return listDTO.get(0);
    }

    public ProductDTO getProductMinPrice() throws SQLException {
        String query = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
        ResultSet resultSet = executeQuery(query);
        ArrayList<ProductDTO> listDTO = mapToDTO(resultSet);
        if (listDTO.isEmpty()) {
            return null;
        }
        return listDTO.get(0);
    }

    public int getSum() throws SQLException {
        String query = "SELECT SUM(price) AS total FROM PRODUCT";
        ResultSet resultSet = executeQuery(query);
        return resultSet.getInt("total");
    }

    public int getCount() throws SQLException {
        String query = "SELECT COUNT(*) AS counted FROM PRODUCT";
        ResultSet resultSet = executeQuery(query);
        return resultSet.getInt("counted");
    }

    public void insert(ProductDTO productDTO) {
        try (Statement statement = SQLDatabase.getConnection().createStatement()) {
            String name = productDTO.getName();
            int price = productDTO.getPrice();
            String query = "INSERT INTO PRODUCT" +
                           "(NAME, PRICE) VALUES" +
                           "(\"" + name + "\"," + price + ")";
            statement.executeUpdate(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
