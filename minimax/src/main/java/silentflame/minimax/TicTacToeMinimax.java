package silentflame.minimax;

import io.vavr.Tuple2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class TicTacToeMinimax {
  static final String FREE = " ";
  private static final int GRID_SIZE = 3;

  /**
   * The grid
   */
  private final String[][] grid;

  private Player currentPlayer;
  private int turn = 0;

  public TicTacToeMinimax() {
    this.grid = new String[GRID_SIZE][GRID_SIZE];
    newGame();
  }

  public void newGame() {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        grid[i][j] = FREE;
      }
    }
    // X start to play
    currentPlayer = Player.HUMAN;
    turn = 0;
  }

//  public boolean isOver() {
//    return hasWon(PLAYER_O) || hasWon(PLAYER_X) || turn == 9;
//  }

  private boolean hasWon(Player player) {
    return
      (grid[0][1].equals(player.getSign()) && grid[0][2].equals(player.getSign()) && grid[0][0].equals(player.getSign()))
        ||
        (grid[1][1].equals(player.getSign()) && grid[1][2].equals(player.getSign()) && grid[1][0].equals(player.getSign()))
        ||
        (grid[2][1].equals(player.getSign()) && grid[2][2].equals(player.getSign()) && grid[2][0].equals(player.getSign()))
        ||
        (grid[1][0].equals(player.getSign()) && grid[2][0].equals(player.getSign()) && grid[0][0].equals(player.getSign()))
        ||
        (grid[1][1].equals(player.getSign()) && grid[2][1].equals(player.getSign()) && grid[0][1].equals(player.getSign()))
        ||
        (grid[1][2].equals(player.getSign()) && grid[2][2].equals(player.getSign()) && grid[0][2].equals(player.getSign()))
        ||
        (grid[1][1].equals(player.getSign()) && grid[2][2].equals(player.getSign()) && grid[0][0].equals(player.getSign()))
        ||
        (grid[1][1].equals(player.getSign()) && grid[2][0].equals(player.getSign()) && grid[0][2].equals(player.getSign()));
  }

  public void makeMove(TicTacToeMove move) {
    grid[move.getX()][move.getY()] = currentPlayer.getSign();
    turn++;
    switchPlayer();
  }

  public void unmakeMove(TicTacToeMove move) {
    grid[move.getX()][move.getY()] = FREE;
    turn--;
    switchPlayer();
  }

  public List<TicTacToeMove> getPossibleMoves() {
    List<TicTacToeMove> moves = new LinkedList<>();
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        if (grid[i][j] == FREE) {
          moves.add(new TicTacToeMove(i, j));
        }
      }
    }
    // moves can be sorted to optimize alpha-beta pruning
    // {1,1} is always the best move when available
    return moves;
  }


  public Tuple2<Optional<TicTacToeMove>, Integer> minimax() {
//    int eval = 0;
    Optional<TicTacToeMove> empty = Optional.empty();


    //new Tuple2<Optional<TicTacToeMove>, Double>(,6);
    if (hasWon(Player.AI)) {
      // 2 for the win
      return new Tuple2<>(empty, 2);
      //return 2;
    } else if (hasWon(Player.HUMAN)) {
      // -2 for loosing
      return new Tuple2<>(empty, -2);
    } else if (grid[1][1].equals(Player.AI.getSign())) {
      // 1 for {1,1}
      return new Tuple2<>(empty, 1);
    } else if (grid[1][1].equals(Player.HUMAN.getSign())) {
      // -1 for opponent {1,1}
      return new Tuple2<>(empty, -1);
    } else if (turn == 9) {
      //is draw
      return new Tuple2<>(empty, 0);
    }

    List<TicTacToeMove> moves = getPossibleMoves();

    List<Tuple2<TicTacToeMove, Integer>> moves1 = new LinkedList<>();

    for (TicTacToeMove move : moves) {
      makeMove(move);
      int eval = minimax()._2();
      moves1.add(new Tuple2<>(move, eval));
//      move.setEvaluation(eval);
      unmakeMove(move);
    }


    final TicTacToeMove[] bestMove = new TicTacToeMove[1];
    final int[] score = new int[1];
    if (currentPlayer == Player.AI) {
      moves1.stream().filter(m -> m._2() == 2).findFirst().ifPresent(t -> {
        bestMove[0] = t._1();
        score[0] = t._2();
      });
    }

    if (currentPlayer == Player.HUMAN) {

      moves1.stream().filter(m -> m._2() == -2).findFirst().ifPresent(t -> {
        bestMove[0] = t._1();
        score[0] = t._2();
      });
    }
    return new Tuple2<>(Optional.of(bestMove[0]), score[0]);
  }


//  public double maxEvaluateValue() {
//    // evaluate return either -2, -1, 0, 1 or 2
//    return 3;
//  }

  public void switchPlayer() {
    currentPlayer = currentPlayer == Player.AI ? Player.HUMAN : Player.AI;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(grid[0][0]);
    sb.append(grid[1][0]);
    sb.append(grid[2][0]);
    sb.append("\n");
    sb.append(grid[0][1]);
    sb.append(grid[1][1]);
    sb.append(grid[2][1]);
    sb.append("\n");
    sb.append(grid[0][2]);
    sb.append(grid[1][2]);
    sb.append(grid[2][2]);
    sb.append("\n");
    return sb.toString();
  }

}