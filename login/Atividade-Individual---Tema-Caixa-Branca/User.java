package login;

// Imports usados pelo JDBC para conectar, executar SQL e ler resultados.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

 // Classe responsável por abrir conexão com o MySQL e validar login.
public class User {

    // Abre uma conexão com o banco.
    public Connection conectarBD(){
        Connection conn = null; // no início nulo; se algo falhar, retorna null.

        try{
            // no seu código original: "com.mysql.Driver.Manager" (causa ClassNotFoundException).
            // eu troquei para o driver moderno do Connector/J:
            Class.forName("com.mysql.cj.jdbc.Driver");

            // mantive a mesma URL/base do seu exemplo, só usei a forma certa no getConnection.
            // (professor, aqui estão usuário e senha na URL, como no seu código.)
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // aqui efetivamente abro a conexão
            conn = DriverManager.getConnection(url);

        } catch (Exception e) {
            // no seu original o catch estava vazio;
            // mas seria interessante logar o erro para facilitar o diagnóstico.
            // ex.: System.err.println("Falha ao conectar: " + e.getMessage());
        }
        return conn;
    }
    public String nome = "";        // guarda o nome retornado pela consulta
    public boolean result = false;  // começa falso; só vira true se a consulta achar o usuário

    //Verifica se existe um usuário com o login/senha informados.
    public boolean verificarUsuario(String login, String senha){
        String sql = "";                 
        Connection conn = conectarBD(); 

        // no seu código original a SQL era concatenada; mantive igual para preservar a lógica.
        sql = "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "'";

        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // RAMIFICAÇÃO: se existe pelo menos um registro, o login é válido.
            if(rs.next()){
                // ao encontrar, marca result=true e lê o nome
                result = true;
                nome = rs.getString("nome");
            }
            // no seu código o else não era necessário, porque result já começa false.
            // mantive assim para não alterar a contagem de nós do fluxo.

            // fechar rs e st aqui evitaria vazamento.
            // rs.close(); st.close();
            // e também fechar a conexão ao final (conn.close()).

        } catch (Exception e) {
            // no seu original este catch também estava vazio; mantive.
        }

        // retorna true quando o rs.next() entrou; caso contrário permanece false.
        return result;
    }
}


    // Criei essa Main para testar se o código funcionava
    //public static void main(String[] args) {
    //    User user = new User();

    //    //if (user.conectarBD() != null) {
    //        System.out.println(" Conexão bem-sucedida com o banco!");
    //    } else {
    //        System.out.println(" Erro ao conectar ao banco!");
    //    }

    //    boolean loginValido = user.verificarUsuario("teste", "1234");
    //    System.out.println("Usuário válido? " + loginValido);
    //} 
 // fim da classe


