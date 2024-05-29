public class Play {
    public static void main(String[] args){
        Table table=new Table(2);
        table.start();
        while(true){
            table.play();
        }
    }
}