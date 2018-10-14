package silentflame.minimax;

public class TicTacToeMove {
  /**
   * The player owning the move
   */
//  private int player;

  /**
   * x coordinate of the move
   */
  private int x;
  /**
   * y coordinate of the move
   */
  private int y;

  public TicTacToeMove(int x, int y) {
    this.x = x;
    this.y = y;
//    this.player = player;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

//  public int getPlayer() {
//    return player;
//  }

//  public void setPlayer(int player) {
//    this.player = player;
//  }

//  public String toString() {
//    return (player == TicTacToeMinimax.PLAYER_O ? "O" : "X") + " (" + x + ";" + y + ")";
//  }
}
