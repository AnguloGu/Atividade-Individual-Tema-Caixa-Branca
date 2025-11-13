
[PLANO.DE.TESTE (2) (1).xlsx](https://github.com/user-attachments/files/23509726/PLANO.DE.TESTE.2.1.xlsx)


    //Versão original do código, a versção comentada e com as alterações está na outra pasta do repositorio
    package login; // N1
    import java.sql.Connection; // N1
    import java.sql.DriverManager; // N1
    import java.sql.ResultSet; // N1
    import java.sql.Statement; // N1

    public class User { // N2

    public Connection conectarBD(){ // N3
        Connection conn = null; // N4
        try{ // N4
            Class.forName("com.mysql.Driver.Manager").newInstance(); // N4
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123"; // N4
            conn = DriverManager.getConnection(url); // N4
        }catch (Exception e) { } // N5
        return conn; // N6
    } // N6

    public String nome=""; // N7
    public boolean result = false; // N7

    public boolean verificarUsuario(String login, String senha){ // N8
        String sql = ""; // N9
        Connection conn = conectarBD(); // N9
        sql = "select nome from usuarios "; // N9
        sql += "where login = '" + login + "'"; // N9
        sql += " and senha = '" + senha + "'"; // N9
        try{ // N10
            Statement st = conn.createStatement(); // N10
            ResultSet rs = st.executeQuery(sql); // N10
            if(rs.next()){ // N11
                result = true; // N12
                nome = rs.getString("nome"); // N12
            } 
        }catch (Exception e) { } // N13
        return result; // N14
    } // N14
    } // N14

NOTAÇÃO DE GRAFO DE FLUXO 


<img width="1920" height="1080" alt="1 (3)" src="https://github.com/user-attachments/assets/c9f0ff0a-fd6d-4f8f-9a1c-2824a3c5fa1b" />



COMPLEXIDADE CICLOMÁTICA
M = E − N + 2P

M = 16 - 14 + 2P

M = 16 - 14 + 2 * 1

M = 2 + 2

M = 4

CAMINHOS BÁSICOS 

Caminho 1 – Execução normal rs.next() verdadeiro

N1 → N2 → N3 → N4 → N6 → N7 → N8 → N9 → N10 → N11 → N12 → N14

Caminho 2 – Execução normal rs.next() falso

N1 → N2 → N3 → N4 → N6 → N7 → N8 → N9 → N10 → N11 → N14

Caminho 3 – Falha na conexão (catch do conectarBD)

N1 → N2 → N3 → N4 → N5 → N6 → N7 → N8 → N9 → N10 → N11 → N14

Caminho 4 – Falha na query (catch do verificarUsuario)

N1 → N2 → N3 → N4 → N6 → N7 → N8 → N9 → N10 → N13 → N14
