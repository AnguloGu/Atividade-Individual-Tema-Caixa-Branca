package login; // Define o pacote onde esta classe está localizada

// Importa as bibliotecas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//conecta ao banco e verificar o login de um usuário
public class User {

    //realiza a conexão com o banco de dados
    public Connection conectarBD(){
        Connection conn = null; // Cria uma variável de conexão inicialmente nula
        try{
            // Inicializa o driver JDBC do MySQL.
            // Professor no seu código inicial usava "com.mysql.Driver.Manager", 
            // mas esse nome não existe. O correto é "com.mysql.cj.jdbc.Driver",
            // que é a forma atual de iniciar o driver e permitir conexões então ja deixei alterado.
            Class.forName("com.mysql.cj.jdbc.Driver");  

            // Cria a URL de conexão com o banco.
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Abre a conexão com o banco de dados usando a URL acima
            conn = DriverManager.getConnection(url);
        }catch (Exception e) {
            // Caso ocorra algum erro (como driver ausente ou banco inacessível),
            // o erro é capturado aqui. O tratamento está vazio, mas poderia exibir uma mensagem.
        }
        // Retorna o objeto de conexão (pode ser null se algo der errado)
        return conn;
    }

    // Variável para guardar o nome do usuário encontrado no banco
    public String nome = "";

    // Variável que indica se o login foi validado com sucesso
    public boolean result = false;

    //Método que verifica se o usuário e senha existem no banco
    public boolean verificarUsuario(String login, String senha){
        String sql = ""; // Variável para armazenar a consulta SQL
        Connection conn = conectarBD(); // Chama o método de conexão

        //Monta a consulta SQL que vai buscar o nome do usuário com base no login e senha informados
        sql = "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "'";

        try{
            //Permite executar comandos SQL no banco
            Statement st = conn.createStatement();

            //Executa a consulta e armazena o resultado
            ResultSet rs = st.executeQuery(sql);

            //Verifica se a consulta retornou algum registro (usuário encontrado)
            if(rs.next()){
                //Se encontrou, marca o resultado como verdadeiro e guarda o nome do usuário
                result = true;
                nome = rs.getString("nome");
            }
        }catch (Exception e) {
            //Captura qualquer erro durante a execução do SQL
            //Aqui também não tem mensagem
        }

        // Retorna true se o login foi encontrado, ou false se não existir
        return result;
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
