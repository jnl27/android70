package pieces;
import chess.*;

/**
 * The Rook class is an extension of the ChessPiece class and creates a Rook Piece.
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */
public class Rook extends ChessPiece{
	/**
	 * name of the piece, "R" for Rook used to display on the ChessBoard in conjunction with color
	 */
	String pieceName="R";
	/**
	 * the Rook's color (0 white 1 black)
	 */
	int pieceColor=0;
	/**
	 * the amount travelled vertically in the last move of the Rook
	 */
	int previousChange=0;

	/**
	 * Constructor with 1 color argument
	 * @param color the color of the Rook
	 */
	public Rook(int color) {
		this.setColor(color);
		this.pieceColor=color;
	}

	/**
	 * Constructor with 2 arguments
	 *
	 * @param color the color of the Rook
	 * @param prevChange the amount travelled vertically in the last move of the Rook
	 */
	public Rook(int color, int prevChange){
		this.setColor(color);
		this.pieceColor=color;
		this.previousChange = prevChange;
	}
	public int getPreviousChange() {
		return previousChange;
	}

	@Override
	public String getPieceName() {
		
		if (pieceColor==0) {
			this.pieceName="wR";
		}
		else {
			this.pieceName="bR";
		}
		return this.pieceName;
	}

	@Override
	public boolean validMove(Board board, Spot startPosition, Spot endPosition) {

		int yChange=Math.abs(endPosition.getYCoordinate()-startPosition.getYCoordinate());
		int xChange=Math.abs(endPosition.getXCoordinate()-startPosition.getXCoordinate());

		if ((xChange == 0 && yChange != 0) || (xChange != 0 && yChange == 0)){
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

		if ((xChange == 0 && yChange != 0) || (xChange != 0 && yChange == 0)){
			previousChange = yChange;
			return true;
		}
		return false;
		
	}


}