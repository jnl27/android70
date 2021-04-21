package pieces;
import chess.Board;
import chess.Spot;

/**
 * The Knight class is an extension of the ChessPiece class and creates a Knight Piece.
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */

public class Knight extends ChessPiece {
	/**
	 * "N" the name used to display a Knight on the ChessBoard in conjunction w/ color
	 */
	String pieceName="N";
	/**
	 * the Knight's color (0 white 1 black)
	 */
	int pieceColor=0;
	/**
	 * the amount travelled vertically in the last move of the Knight
	 */
	int previousChange = 0;
	/**
	 * Constructor with 1 argument
	 *
	 * @param color the color of the Knight
	 */
	public Knight(int color) {
		this.setColor(color);
		this.pieceColor=color;
	}
	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the Knight
	 * @param previousChange the amount travelled vertically in the last move of the Knight
	 */
	public Knight(int color, int previousChange) {
		this.setColor(color);
		this.pieceColor=color;
		this.previousChange = previousChange;
	}
	public int getPreviousChange() {
		return previousChange;
	}

	
	@Override
	public String getPieceName() {
		if (pieceColor==0) {
			this.pieceName="wN";
		}
		else {
			this.pieceName="bN";
		}
		return this.pieceName;
	}

	@Override
	public boolean validMove(Board board, Spot startPosition, Spot endPosition) {
		
		//L shape
		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange == 2 && yChange == 1) || (xChange == 1 && yChange == 2)){
			previousChange = yChange;
			return true;
		}
		return false;
		
	}
	@Override
	public boolean validMoveWithoutCheck(Board board, Spot startPosition, Spot endPosition) {
		if (!endPosition.isEmpty() && endPosition.getPiece().getColor()==this.getColor()) {
			return false;
		}

		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange == 2 && yChange == 1) || (xChange == 1 && yChange == 2)){
			previousChange = yChange;
			return true;
		}
        return false;
		
	}

}