package MineSweeper;

public class Main {
    public static void main(String[] args) {

        String s =  "*...\n..*.\n....";

        MineSweeper mineSweeper = new MineSweeperImpl();

        mineSweeper.setMineField(s);

        System.out.println(mineSweeper.getHintField());

    }
}
