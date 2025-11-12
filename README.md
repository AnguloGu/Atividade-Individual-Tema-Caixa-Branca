[PLANO.DE.TESTE.1.xlsx](https://github.com/user-attachments/files/23400111/PLANO.DE.TESTE.1.xlsx)

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
            } else { // N13
                result = false; // N13
            }
        }catch (Exception e) { } // N14
        return result; // N14
    } // N14
    } // N14

NOTAÇÃO DE GRAFO DE FLUXO 
<img width="1920" height="1080" alt="1 (1)" src="https://github.com/user-attachments/assets/55900786-0608-43dc-a91d-3cb95a3ee4d0" />

COMPLEXIDADE CICLOMÁTICA
M = E − N + 2P

M = 16 - 14 + 2P

M = 16 - 14 + 2 * 1

M = 2 + 2

M = 4

CAMINHOS BÁSICOS 

Caminho 1 – Execução normal (if verdadeiro)

1 → 2 → 3 → 4 → 6 → 7 → 8 → 9 → 10 → 11 → 12 → 14

Caminho 2 – Execução normal (if falso)

1 → 2 → 3 → 4 → 6 → 7 → 8 → 9 → 10 → 11 → 13 → 14

Caminho 3 – Falha na conexão (catch do conectarBD)

1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 10 → 11 → 13 → 14

Caminho 4 – Falha na query (catch do verificarUsuario)

1 → 2 → 3 → 4 → 6 → 7 → 8 → 9 → 10 → 13 → 14
