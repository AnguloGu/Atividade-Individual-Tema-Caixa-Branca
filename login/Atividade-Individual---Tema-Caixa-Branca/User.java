package login; // Aqui eu defino o pacote onde essa classe está

// Importa as bibliotecas necessárias pra conectar no banco
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Classe usada pra conectar no banco e verificar o login de um usuário
public class User {

    // Faz a conexão com o banco de dados
    public Connection conectarBD(){
        Connection conn = null; // Cria uma variável pra guardar a conexão
        try{
            // Aqui é onde o driver do MySQL é iniciado.
            // professor você tinha colocado "com.mysql.Driver.Manager", mas isso dá erro.
            // Então eu troquei pra "com.mysql.cj.jdbc.Driver", que é o jeito certo e atual de conectar.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Cria a URL da conexão com o banco (com usuário e senha)
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Abre a conexão com o banco usando a URL acima
            conn = DriverManager.getConnection(url);
        }catch (Exception e) {
            // Se der algum erro (tipo driver faltando ou banco fora do ar),
            // ele cai aqui. Não tem mensagem, mas daria pra mostrar uma.
        }
        // Retorna a conexão (ou null se algo deu errado)
        return conn;
    }

    // Guarda o nome do usuário que foi encontrado no banco
    public String nome = "";

    // Indica se o login foi validado com sucesso
    public boolean result = false;

    // Verifica se o usuário e a senha existem no banco
    public boolean verificarUsuario(String login, String senha){
        String sql = ""; // Cria a string pra montar o comando SQL
        Connection conn = conectarBD(); // Chama o método que faz a conexão

        // Monta o comando SQL pra buscar o nome do usuário com base no login e senha
        sql = "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "'";

        try{
            // Cria um objeto pra executar o comando SQL
            Statement st = conn.createStatement();

            // Executa o comando e guarda o resultado
            ResultSet rs = st.executeQuery(sql);

            // Se a consulta retornou algo, o usuário existe
            if(rs.next()){
                // Marca que o login deu certo e pega o nome do usuário
                result = true;
                nome = rs.getString("nome");
            }
        }catch (Exception e) {
            // Se der erro ao rodar o SQL, ele cai aqui
            // Também sem mensagem, mas poderia ter
        }

        // Retorna true se o login foi encontrado, senão false
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
} // fim da classe

