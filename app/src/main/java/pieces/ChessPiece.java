package pieces;
import chess.Board;
import chess.Spot;

/**
 * ChessPiece is the abstract class for the Chess game and it specifies the functionality of every Chess piece.
 *
 * @author Param Thakker
 * @author Jonathan Lu
 */
public abstract class ChessPiece {
	/**
	 * the color of the piece, represented by an int (0 for white, 1 for black)
	 */
	int color=0; //0 for white, 1 for black
	/**
	 * boolean first checks if a piece has made its first move yet
	 */
	boolean first=true;
	/**
	 * int previousChange keeps track of the # of vertical spaces the piece moved in its last turn
	 */
	int previousChange;

	
	/**
	 * setColor method is used to assign a color to the current ChessPiece
	 *
	 * @param col    The color to set to as an int
	 */
	
	public void setColor(int col) {
		this.color = col;
	}

	/**
	 * setFirst method is used to assign a boolean value to determine whether or not a ChessPiece has made its first move
	 * @param bool true or false for its first move
	 */
	public void setFirst(boolean bool) {first = bool;}
	/**
	 * getColor method is used to get the color of a ChessPiece
	 *
	 * @return    The color of the ChessPiece as an int
	 */
	public int getColor() {
		return this.color;
	}
	

	/**
	 * getPieceName is used to return the piece abbreviation (wN or bN here)
	 *
	 *  @return  The piece abbreviation
	 */
	
	public abstract String getPieceName();

	/**
	 * validMove method is used to check if a piece is attempting a valid move
	 *
	 * @param board          The chessboard
	 * @param startPosition  The position(Spot) where the piece is currently located
	 * @param endPosition    The position(Spot) where the piece is attempting to move
	 * @return               True if the move is valid, false otherwise
	 */
	public abstract boolean validMove(Board board, Spot startPosition, Spot endPosition);

	/**
	 * validMoveWithoutCheck method is used to check if the inputted move is legal without putting/keeping the King in check
	 *
	 * @param board          The chessboard
	 * @param startPosition  The position(Spot) where the piece is currently located
	 * @param endPosition    The position(Spot) where the piece is attempting to move
	 * @return               True if the move is valid, false otherwise
	 */
	public abstract boolean validMoveWithoutCheck(Board board, Spot startPosition, Spot endPosition);

	/**
	 * getPreviousChange method is used to retrieve the previous amount of vertical spaces the ChessPiece last moved.
	 *
	 * @return # of vertical tiles the ChessPiece moved in its previous turn
	 */
	public abstract int getPreviousChange();
}
