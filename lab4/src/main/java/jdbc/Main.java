package jdbc;

import Crud.CRUDInterface;
import Crud.SqlCRUD;
import Entities.CarEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       // CRUDInterface crud = new SqlCRUD();
        Connection connection = new Connect().getCon();

        List<CarEntity> list = new ArrayList<>();

    //JDBC connection
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM lab4schema.cars;");) {
            while (rs.next()) {
                list.add(new CarEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("---JDBC---");
        for (CarEntity car : list) {
            System.out.println(car);
        }
    }
}
