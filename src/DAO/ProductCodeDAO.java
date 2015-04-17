package DAO;

import Connection.ConnectionFactory;
import Interfaces.DBQueries;
import Models.DiscountCode;
import Models.ProductCode;
import Utils.DBHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCodeDAO implements DBQueries<ProductCode> {

    private Connection conn;
    private PreparedStatement statement;

    public List<ProductCode> buscarTodos() {
        List<ProductCode> objetos = new ArrayList<>();
        String sql = "select * from PRODUCT_CODE";
        ResultSet resultado = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultado = statement.executeQuery();

            while (resultado.next()) {
                ProductCode pd = new ProductCode();
                pd.setProdCode(resultado.getString("PROD_CODE"));
                pd.setDiscountCode(resultado.getString("DISCOUNT_CODE").charAt(0));
                pd.setDescription(resultado.getString("DESCRIPTION"));
                objetos.add(pd);
            }
            return objetos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBHelper.close(conn);
            DBHelper.close(statement);
            DBHelper.close(resultado);
        }
        return objetos;
    }

    @Override
    public ProductCode buscarPorID(String code) {
        ResultSet resultado = null;
        ProductCode retorno = null;
        String sql = "SELECT * FROM PRODUCT_CODE WHERE PROD_CODE = ?";

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, String.valueOf(code));
            resultado = statement.executeQuery();

            while (resultado.next()) {
                retorno = new ProductCode();
                retorno.setProdCode(resultado.getString("PROD_CODE"));
                retorno.setDiscountCode(resultado.getString("DISCOUNT_CODE").charAt(0));
                retorno.setDescription(resultado.getString("DESCRIPTION"));
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBHelper.close(conn);
            DBHelper.close(statement);
            DBHelper.close(resultado);
        }
    }

    @Override
    public boolean insert(ProductCode entity) {
        try {
            String sql = "INSERT INTO PRODUCT_CODE VALUES(?, ?, ?)";

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, entity.getProdCode());
            statement.setString(2, String.valueOf(entity.getDiscountCode()));
            statement.setString(3, entity.getDescription());
            
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBHelper.close(conn);
            DBHelper.close(statement);
        }

        return false;
    }
    
    public List<ProductCode> buscarPorDesconto(String discountCode) {
        ResultSet resultado = null;
        List<ProductCode> retorno = new ArrayList<>();
        String sql = "select * from PRODUCT_CODE "
                + "inner join DISCOUNT_CODE on PRODUCT_CODE.DISCOUNT_CODE = DISCOUNT_CODE.DISCOUNT_CODE "
                + "where DISCOUNT_CODE.DISCOUNT_CODE = ?";

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, String.valueOf(discountCode));
            resultado = statement.executeQuery();

            while (resultado.next()) {
                ProductCode novo = new ProductCode();
                novo.setProdCode(resultado.getString("PROD_CODE"));
                novo.setDiscountCode(resultado.getString("DISCOUNT_CODE").charAt(0));
                novo.setDescription(resultado.getString("DESCRIPTION"));
                
                DiscountCode discount = new DiscountCode(resultado.getString("DISCOUNT_CODE"), resultado.getBigDecimal("RATE"));
                novo.setDiscountEntity(discount);
                
                retorno.add(novo);
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBHelper.close(conn);
            DBHelper.close(statement);
            DBHelper.close(resultado);
        }
    }
}
