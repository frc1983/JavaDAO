package DAO;

import Connection.ConnectionFactory;
import Interfaces.DBQueries;
import Models.DiscountCode;
import Models.ProductCode;
import Utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscountCodeDAO implements DBQueries<DiscountCode> {

    private Connection conn;
    private PreparedStatement statement;

    public List<DiscountCode> buscarTodos() {
        List<DiscountCode> resultados = new ArrayList<>();
        ResultSet resultado = null;
        String sql = "SELECT * FROM DISCOUNT_CODE";
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultado = statement.executeQuery();

            while (resultado.next()) {
                DiscountCode temp = new DiscountCode();
                temp.setDiscountCode(resultado.getString("DISCOUNT_CODE"));
                temp.setRate(resultado.getBigDecimal("RATE"));
                resultados.add(temp);
            }
            return resultados;
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
    public DiscountCode buscarPorID(String code) {
        ResultSet resultado = null;
        DiscountCode retorno = null;
        String sql = "SELECT * FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, code);
            resultado = statement.executeQuery();

            while (resultado.next()) {
                retorno = new DiscountCode();
                retorno.setDiscountCode(resultado.getString("DISCOUNT_CODE"));
                retorno.setRate(resultado.getBigDecimal("RATE"));
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
    public boolean insert(DiscountCode discount) {
        try {
            String sql = "INSERT INTO DISCOUNT_CODE VALUES(?, ?)";

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, discount.getDiscountCode());
            statement.setBigDecimal(2, discount.getRate());
            
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
}
