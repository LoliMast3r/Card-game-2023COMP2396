public class Money {
    private int amount;

    Money(){
        this.amount = 100;
    }

    public int getMoney(){
        return amount;
    }

    public void WinLose(int winstate,int bet){
        if(winstate == 1) amount += bet;
        else amount -= bet;
    }
}
