package silentflame.minimax;

import lombok.Getter;

public enum Player {
  AI("X"), HUMAN("O");
  @Getter
  private final String sign;

   Player(String sign) {
    this.sign = sign;
  }
}