package Entity;

// parte mafe
import java.lang.reflect.Constructor;

// parte mafe
public abstract class Sala {

    private int id;
    private String nome;
    private int capacidade;
    private String predio;
    private int andar;
    private int numero;
    // parte mafe
    // private String tipo; // Campo removido pois agora é definido via polimorfismo
    private boolean status;
    private String horarioDisp;

   /*Construtor null*/
    public Sala() {
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // parte mafe
    // O método setTipo foi removido e getTipo agora é abstrato
    public abstract String getTipo();

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHorarioDisp() {
        return horarioDisp;
    }

    public void setHorarioDisp(String horarioDisp) {
        this.horarioDisp = horarioDisp;
    }
      
    // parte mafe
    public static class SalaBuilder{
        private Sala salaInstance;
        
        public SalaBuilder(Class<? extends Sala>salaClass){
            try{
                Constructor<? extends Sala>ctor = salaClass.getDeclaredConstructor();
                ctor.setAccessible(true);
                this.salaInstance = ctor.newInstance();
            } catch (Exception e){
                throw new RuntimeException("nao foi possivel verificar a classe instanciada");
            }
        }
        
        public SalaBuilder comNome (String nome){
          salaInstance.setNome(nome);
          return this;
        }
        
        public SalaBuilder comCapacidade (int capacidade){
            salaInstance.setCapacidade(capacidade);
            return this;
        }
        
        public SalaBuilder comPredio (String predio){
            salaInstance.setPredio(predio);
            return this;
        }
        
        public SalaBuilder comAndar (int andar){
            salaInstance.setAndar(andar);
            return this;
        }
        
        public SalaBuilder comNumero (int numero){
            salaInstance.setNumero(numero);
            return this;
        }
        
        // O builder de tipo foi removido pois o tipo é definido pela classe instanciada
        
        public SalaBuilder comStatus (boolean status){
            salaInstance.setStatus(status);
            return this;
        }
        
        public SalaBuilder comHoriarioDisp (String horarioDisp){
            salaInstance.setHorarioDisp(horarioDisp);
            return this;
        }
                
        public Sala constroi(){
            return salaInstance;
        }
    }
}