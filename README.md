[PLANO.DE.TESTE.1.xlsx](https://github.com/user-attachments/files/23400111/PLANO.DE.TESTE.1.xlsx)
package login; // N1

    import java.sql.Connection; // N1
    import java.sql.DriverManager; // N1
    import java.sql.ResultSet; // N1
    import java.sql.Statement; // N1

    public class User { // N2
    public Connection conectarBD(){ // N3
        Connection conn = null; // N4
        try{ // N5
            Class.forName("com.mysql.Driver.Manager").newInstance(); // N5
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123"; // N5
            conn = DriverManager.getConnection(url); // N5
        }catch (Exception e) { } // N6
        return conn; // N7
    } // N7

    public String nome=""; // N8
    public boolean result = false; // N9

    public boolean verificarUsuario(String login, String senha){ // N10
        String sql = ""; // N11
        Connection conn = conectarBD(); // N12
        sql = "select nome from usuarios "; // N13
        sql += "where login = '" + login + "'"; // N13
        sql += " and senha = '" + senha + "'"; // N13
        try{ // N14
            Statement st = conn.createStatement(); // N15
            ResultSet rs = st.executeQuery(sql); // N16
            if(rs.next()){ // N17
                result = true; // N18
                nome = rs.getString("nome"); // N18
            } else { // N19
                result = false; // N19
            }
        }catch (Exception e) { } // N20
        return result; // N21
    } // N21
    } // N22
NOTAÇÃO DE GRAFO DE FLUXO 
<img width="1920" height="1080" alt="1" src="https://github.com/user-attachments/assets/e633663d-93e3-40d9-bd5e-df4d06d452e3" />
COMPLEXIDADE CICLOMÁTICA
M = E − N + 2P

M = 22 - 22 + 2P

M = 22 - 22 + 2 * 1

M = 0 + 2

M = 2

CAMINHOS BÁSICOS 

Quando o if(rs.next()) é verdadeiro (usuário encontrado)

N1 → N2 → N3 → N4 → N5 → N6 → N7 → N8 → N9 → N10 → 
N11 → N12 → N13 → N14 → N15 → N16 → N17 → N18 → N20 → N21 → N22


Quando o if(rs.next()) é falso (usuário não encontrado)

N1 → N2 → N3 → N4 → N5 → N6 → N7 → N8 → N9 → N10 → 
N11 → N12 → N13 → N14 → N15 → N16 → N17 → N19 → N20 → N21 → N22
