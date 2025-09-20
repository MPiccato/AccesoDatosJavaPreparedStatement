import java.sql.*;


public class Cuenta {

    private static final String SQL_CREATE_TABLA = "DROP TABLE IF EXISTS CUENTAS;" +
            "CREATE TABLE CUENTAS (" +
            "ID INT PRIMARY KEY," +
            "NUMERO_CUENTA INT NOT NULL," +
            "TITULAR VARCHAR(100) NOT NULL," +
            "SALDO NUMERIC(10,2) NOT NULL" +
            ")";

    private static final String SQL_INSERT = "INSERT INTO CUENTAS VALUES (?,?,?,?);";

    private static final String SQL_SELECT = "SELECT * FROM CUENTAS;";

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = getConnection();

            //Creo la table vacía con los campos necesarios

            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLA);

            // Insertar valores a la tabla con PreparedStatement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);

            //Carga de valores
            //Primera cuenta
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,12334);
            preparedStatement.setString(3,"Martin");
            preparedStatement.setDouble(4,1940.33);

            preparedStatement.execute();

            ResultSet rs = statement.executeQuery(SQL_SELECT);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) +
                        " - Num Cuenta: " + rs.getInt(2) +
                        " - Titular: " + rs.getString(3) +
                        " - Saldo: $" + rs.getDouble(4) + "----"
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/ConsultasYTransacciones", "sa", "sa");
    }



}
